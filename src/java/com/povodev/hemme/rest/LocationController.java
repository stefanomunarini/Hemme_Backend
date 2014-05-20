/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.LocationCoordinates;
import com.povodev.hemme.jdbcdao.LocationJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Stefano
 */
@Controller
public class LocationController {
    
    @Autowired
    private LocationJdbcDao locationJdbcDao;
    
    @RequestMapping("/getCoordinates")
    public @ResponseBody LocationCoordinates getCoordinates(
            @RequestParam(value="user_id", required=true) int user_id) {
        
        return locationJdbcDao.getCoordinates(user_id);
    }
    
    @RequestMapping(value="/setCoordinates", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody boolean setCoordinates(
            @RequestParam(value="user_id", required=true) int user_id,
            @RequestBody LocationCoordinates locationCoordinates){
                
        return locationJdbcDao.setCoordinates(locationCoordinates, user_id);
    }
}
