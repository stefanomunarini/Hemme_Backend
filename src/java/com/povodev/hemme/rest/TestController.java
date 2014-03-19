/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Test;
import com.povodev.hemme.jdbcdao.TestJdbcDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gbonadiman.stage
 */
public class TestController {
    
    @RequestMapping("/getTest")
    public @ResponseBody Test getTest(
        @RequestParam(value="user_id", required=true) int user_id) {
        TestJdbcDao test = new TestJdbcDao();
        return test.getTest(user_id);
    }
    
    @RequestMapping("/newTest")
    public @ResponseBody Test newTest(
            @RequestParam(value="user_id", required=true) int user_id) {
        TestJdbcDao test = new TestJdbcDao();
        return test.getTest(user_id);
    }
    
}
