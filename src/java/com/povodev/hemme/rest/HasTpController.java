/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.HasTp;
import com.povodev.hemme.jdbcdao.HasTpJdbcDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gbonadiman.stage
 */
@Controller
public class HasTpController {
        
    @RequestMapping("/getHasTp")
    public @ResponseBody HasTp getHasTp(
        @RequestParam(value="user_id", required=true) int user_id) {
        HasTpJdbcDao hasTp = new HasTpJdbcDao();
        return hasTp.getHasTp(user_id);
    }
    
    @RequestMapping("/newHasTp")
    public @ResponseBody HasTp newHasTp(
            @RequestParam(value="user_id", required=true) int user_id) {
        HasTpJdbcDao hasTp = new HasTpJdbcDao();
        return hasTp.getHasTp(user_id);
    }
}
