/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Result;
import com.povodev.hemme.jdbcdao.TestJdbcDao;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gbonadiman.stage
 */
@Controller
public class TestController {
    
    @RequestMapping("/getTest")
    public @ResponseBody ArrayList<Result> getTest(
        @RequestParam(value="user_id", required=true) int user_id) {
        TestJdbcDao test = new TestJdbcDao();
        return test.getTest(user_id);
    }
    
    @RequestMapping("/newTest")
    public @ResponseBody void newTest(
            @RequestParam(value="user_id", required=true) int user_id) {
        TestJdbcDao test = new TestJdbcDao();
        test.newTest(user_id);
    }
    
}
