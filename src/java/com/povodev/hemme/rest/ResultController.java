/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Result;
import com.povodev.hemme.jdbcdao.ResultJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gbonadiman.stage
 */



@Controller
public class ResultController {
     
    @Autowired
    private ResultJdbcDao resultJdbcDao;
    
    @RequestMapping("/getResult")
    public @ResponseBody Result getResult(
            @RequestParam(value="result_id", required=true) int result_id) {
        
        return resultJdbcDao.getResult(result_id);
    }
    
    @RequestMapping(value="/insertResult", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody boolean insertResult(
            @RequestBody Result result,
            @RequestParam(value="user_id", required=true) int user_id){
        return resultJdbcDao.insertResult(result,user_id);
    }
    
}
