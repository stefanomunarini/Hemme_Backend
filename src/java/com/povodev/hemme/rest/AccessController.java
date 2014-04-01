/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Access;
import com.povodev.hemme.jdbcdao.AccessJdbcDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gbonadiman.stage
 */
@Controller
public class AccessController {
    
        
    @Autowired
    private AccessJdbcDao accessJdbcDao;
    
    @RequestMapping("/registrationAccess")
    public @ResponseBody boolean registrationAccess(
            @RequestParam(value="email", required=true) String email,
            @RequestParam(value="password", required=true) String password,
            @RequestParam(value="imei", required=true) String imei,
            @RequestParam(value="role", required=true) int role,
            @RequestParam(value="status", required=true) int status){
        
        return accessJdbcDao.registrationAccess(email, password, imei, role, status);
    }
    
    
    @RequestMapping("/getAccess")
    public @ResponseBody Access registrationAccess(
            @RequestParam(value="access_id", required=true) int access_id){
        return accessJdbcDao.getAccess(access_id);
    }
    
     @RequestMapping("/editAccess")
    public @ResponseBody  boolean editAccess(
            @RequestParam(value="access", required=true) Access access){
        return accessJdbcDao.editAccess(access);
    }
    
     @RequestMapping("/deleteAccess")
    public @ResponseBody boolean deleteAccess(
            @RequestParam(value="access_id", required=true) int access_id){
        return accessJdbcDao.deleteAccess(access_id);
    }
    
     @RequestMapping("/getDevicesList")
    public @ResponseBody ArrayList<Access> getDevicesList(
            @RequestParam(value="email", required=true) String email,
            @RequestParam(value="password", required=true) String password){
        return accessJdbcDao.getDevicesList(email, password);
    }
    
    
    
}
