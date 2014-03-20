/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.User;
import com.povodev.hemme.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcDao implementation for User
 * @author smunarini.stage
 */
public class UserJdbcDao implements UserDao{
    
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUser(int user_id) {
        System.err.println("JDBC TEMPLATE   " + JdbcTemplate);
        throw new UnsupportedOperationException
        ("Not supported yet. ooooooooooooooooooooooooooooooooo"); 
        
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registration(String imei, String name, String surname, String password, String email, int role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean login(String email,String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
