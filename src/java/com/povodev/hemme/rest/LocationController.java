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
 * Classe controller per filtrare correttamente le richieste in input
 * @author Povodev
 */@Controller
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
