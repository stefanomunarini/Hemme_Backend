package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Result;
import com.povodev.hemme.dao.TestDao;
import com.povodev.hemme.rowmapper.TestMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Classe JdbcDao che implementa il corpo di tutte le funzioni dichiarate nell'interfaccia
 * @author Babol
 */
public class TestJdbcDao implements TestDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static org.apache.log4j.Logger log = Logger.getLogger(UserJdbcDao.class);
        
    /**
     * Inserimento nuovo test
     * @param user_id
     * @param result_id 
     */
    @Override
    public void newTest(int user_id, int result_id) {
        try{
            this.jdbcTemplate.update(
                "INSERT INTO Test (user_id, result_id) values (?, ?)", 
                new Object[] {user_id, result_id});
        }catch (DataAccessException runtimeException){
            log.error("***Dao::failed to CREATE NEW TEST, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
    }

    /**
     * Recupero i test di un paziente
     * @param user_id
     * @return 
     */
    @Override
    public ArrayList<Result> getTest(int user_id) {
        List<Map<String, Object>> rows;
        
        String sql = "SELECT * FROM Test NATURAL JOIN Result WHERE user_id = ? ORDER BY date DESC";
	try{
            rows = this.jdbcTemplate.queryForList(sql, user_id);
        }catch (DataAccessException dataAccessException){
            log.error("***DAO :: getTest FAIL, DataAccessException occurred, message follows.");
            log.error(dataAccessException);
            throw dataAccessException;
        }
        return TestMapper.getTestMap(rows);
    }
    
}
