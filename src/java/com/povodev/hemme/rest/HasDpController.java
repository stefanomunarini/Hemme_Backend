package com.povodev.hemme.rest;

import com.povodev.hemme.bean.HasDp;
import com.povodev.hemme.bean.Test;
import com.povodev.hemme.jdbcdao.HasDpJdbcDao;
import com.povodev.hemme.jdbcdao.TestJdbcDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gbonadiman.stage
 */
@Controller
public class HasDpController {
    
    
    @RequestMapping("/getHasDp")
    public @ResponseBody HasDp getHasDp(
        @RequestParam(value="user_id", required=true) int user_id) {
        HasDpJdbcDao hasDp = new HasDpJdbcDao();
        return hasDp.getHasDp(user_id);
    }
    
    @RequestMapping("/newHasDp")
    public @ResponseBody HasDp newHasDp(
            @RequestParam(value="user_id", required=true) int user_id) {
        HasDpJdbcDao hasDp = new HasDpJdbcDao();
        return hasDp.getHasDp(user_id);
    }
}
