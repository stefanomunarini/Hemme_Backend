/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.User;
import com.povodev.hemme.jdbcdao.UserJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Controller for User
 * @author smunarini.stage
 */
@Controller
public class UserController {

    @Autowired
    private UserJdbcDao userJdbcDao;
        
    @RequestMapping("/getUser")
    public @ResponseBody User getUser(
            @RequestParam(value="user_id", required=true) int user_id){
        return userJdbcDao.getUser(user_id);
    }
    
    
    @RequestMapping("/registration")
    public @ResponseBody User registration(
            @RequestParam(value="imei", required=true) String imei,
            @RequestParam(value="name", required=true) String name,
            @RequestParam(value="surname", required=true) String surname,
            @RequestParam(value="password", required=true) String password,
            @RequestParam(value="email", required=true) String email,
            @RequestParam(value="role", required=true) int role){
        
        if (userJdbcDao.registration(imei, name, surname, password, email, role)){
            return userJdbcDao.login(email, password);
        } else {
            return null;
        }
    }
    
    @RequestMapping("/login")
    public @ResponseBody User login(
            @RequestParam(value="email", required=true) String email,
            @RequestParam(value="password", required=true) String password){

        return userJdbcDao.login(email, password);
    }
}
