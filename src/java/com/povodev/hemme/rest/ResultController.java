/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Result;
import com.povodev.hemme.jdbcdao.ResultJdbcDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gbonadiman.stage
 */
public class ResultController {
 
    @RequestMapping("/getResult")
    public @ResponseBody Result getResult(
        @RequestParam(value="user_id", required=true) int user_id) {
        ResultJdbcDao result = new ResultJdbcDao();
        return result.getResult(user_id);
    }
    
    @RequestMapping("/newResult")
    public @ResponseBody Result newResult(
            @RequestParam(value="user_id", required=true) int user_id) {
        ResultJdbcDao result = new ResultJdbcDao();
        return result.getResult(user_id);
    }
    
}
