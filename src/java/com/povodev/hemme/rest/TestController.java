package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Result;
import com.povodev.hemme.jdbcdao.TestJdbcDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Classe controller per filtrare correttamente le richieste in input
 * @author Babol
 */
@Controller
public class TestController {
    
    @Autowired
    TestJdbcDao testJdbcDao;
    
    @RequestMapping("/getTest")
    public @ResponseBody ArrayList<Result> getTest(
        @RequestParam(value="user_id", required=true) int user_id) {
        
        return testJdbcDao.getTest(user_id);
    }
    
    @RequestMapping("/newTest")
    public @ResponseBody void newTest(
            @RequestParam(value="user_id", required=true) int user_id,
            @RequestParam(value="result_id", required=true) int result_id) {
            testJdbcDao.newTest(user_id, result_id);
    }
    
}
