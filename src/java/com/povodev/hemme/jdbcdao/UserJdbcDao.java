/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.User;
import com.povodev.hemme.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcDao implementation for User
 * @author smunarini.stage
 */
public class UserJdbcDao implements UserDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    

    @Override
    public User getUser(int user_id) {
        
        User user;
        String sql = "SELECT * FROM user WHERE user_id = ?";
       
        try{
            user = (User) this.jdbcTemplate.queryForObject(
                sql,
                new Object[] { (user_id)}, 
                new BeanPropertyRowMapper(User.class));
        }catch (DataAccessException dae){
            
            throw dae;
        }    
        return user;
        
    }

    @Override
    public boolean registration(String imei, String name, String surname, String password, String email, int role) {
    
        String query = "INSERT INTO user (imei, name, surname, password, email, role) values (?, ?, ?, ?, ?, ?)";
        try {
            this.jdbcTemplate.update(
                query, 
                new Object[] {imei, name, surname, password, email, role});
        } catch (DataAccessException dae){
            System.err.println("***Dao::failed to CREATE NEW user, DataAccessException occurred, message follows.");
            System.err.println(dae.getMessage());
            throw dae;
        }
        
        return true;
        
    }
    
    @Override
    public User login(String email,String password) {

        User user;
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
       
        try{
            user = (User) this.jdbcTemplate.queryForObject(
                sql,
                new Object[] { email, password}, 
                new BeanPropertyRowMapper(User.class));
        }catch (DataAccessException dae){
            
            throw dae;
        }    
        return user;
        
    }

    
}
