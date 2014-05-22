
package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Result;
import com.povodev.hemme.dao.ResultDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * Classe JdbcDao che implementa il corpo di tutte le funzioni dichiarate nell'interfaccia
 * @author Babol
 */
public class ResultJdbcDao implements ResultDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private TestJdbcDao testJdbcDao;
    
    static org.apache.log4j.Logger log = Logger.getLogger(UserJdbcDao.class);

    /**
     * Recupero risultato di un singolo test
     * @param result_id
     * @return 
     */
    @Override
    public Result getResult(int result_id) {
        
        Result result = new Result();
        String sql = "SELECT * FROM Result WHERE ID = ?";
        
        try{
            result = (Result) this.jdbcTemplate.queryForObject(
                sql, new Object[] { result_id }, 
                new BeanPropertyRowMapper(Result.class));
        }catch (DataAccessException runtimeException){
            log.error("***Dao:: fail to GET DOCUMENT, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }    
        return result;
    }

    /**
     * Inserisco nuovo risultato
     * @param result
     * @param user_id
     * @return 
     */
    @Override
    public boolean insertResult(final Result result,int user_id) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result_generated_key;
          
        final String query = "INSERT into Result (grade,time,date) values (?,?,?)";
        try {
            jdbcTemplate.update(new PreparedStatementCreator(){
                @Override
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{ 
                    PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1,result.getGrade()); 
                    preparedStatement.setInt(2, result.getTime());
                    preparedStatement.setTimestamp(3, result.getDate());
                    return preparedStatement; 
                }
            }, keyHolder);
        } catch (DataAccessException runtimeException){
            log.error("***Dao::fail to CREATE NEW result, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
        
        result_generated_key = keyHolder.getKey().intValue();
        testJdbcDao.newTest(user_id, result_generated_key);
        return true;
    }

    
}
