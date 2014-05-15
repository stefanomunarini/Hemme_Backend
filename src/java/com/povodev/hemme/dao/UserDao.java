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
    public boolean registration(User user);
    public User login(String email, String password,String imei);
    public String getAuthor(int user_id);
    public String passwordRecovery (String email);
    public boolean addNewLinkTutorPatient(int old_tutor_id,String IMEI);
    
}
