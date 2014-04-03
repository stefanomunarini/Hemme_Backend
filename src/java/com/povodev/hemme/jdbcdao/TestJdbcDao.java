/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Result;
import com.povodev.hemme.bean.Test;
import com.povodev.hemme.dao.TestDao;
import com.povodev.hemme.rowmapper.TestMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author gbonadiman.stage
 */
public class TestJdbcDao implements TestDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void newTest(int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Result> getTest(int user_id) {
        List<Map<String, Object>> rows;
        
        String sql = "SELECT * FROM test NATURAL JOIN result WHERE user_id = ?";
	try{
            rows = this.jdbcTemplate.queryForList(sql, user_id);
        }catch (DataAccessException dataAccessException){
            System.err.println("***DAO :: getTest FAIL, DataAccessException occurred, message follows.");
            System.err.println(dataAccessException);
            throw dataAccessException;
        }
        
        return TestMapper.getTestMap(rows);
    }
    
}
