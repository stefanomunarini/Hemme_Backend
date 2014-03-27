
package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Result;
import com.povodev.hemme.dao.ResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ResultJdbcDao implements ResultDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Result getResult(int result_id) {
        
        Result result = new Result();
        String sql = "SELECT * FROM RESULT WHERE ID = ?";
        
        try{
            result = (Result) this.jdbcTemplate.queryForObject(
                sql, new Object[] { result_id }, 
                new BeanPropertyRowMapper(Result.class));
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao:: fail to GET DOCUMENT, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
        return result;
    }

    @Override
    public boolean insertResult(Result result,int user_id) {

        KeyHolder holder = new GeneratedKeyHolder();
        int result_generated_key;
  
        this.jdbcTemplate.update(
                "INSERT into RESULT (grade,time,date) values (?,?,?)", 
                new Object[] {result.getGrade(),result.getTime(),result.getDate()},holder);
        
        result_generated_key = holder.getKey().intValue();

        this.jdbcTemplate.update(
            "INSERT into TEST (user_id,result_id) values (?, ?)", 
            new Object[] {user_id,result_generated_key});
        
        return true;
    }

    
}
