/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.User;

/**
 * Public interface for UserJdbcDao
 * @author smunarini.stage
 */
public interface UserDao {
    
    public User getUser(int user_id);
    
    public void newUser(String imei, String name, String surname, String password, String email, int role);
    
    
    
}
