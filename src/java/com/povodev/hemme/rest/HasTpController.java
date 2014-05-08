/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.HasTp;
import com.povodev.hemme.bean.User;
import com.povodev.hemme.jdbcdao.HasTpJdbcDao;
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
public class HasTpController {
        

    @Autowired
    private HasTpJdbcDao hasTpJdbcDao;
    
    @RequestMapping("/patientList")
    public @ResponseBody ArrayList<User> newHasTp(
            @RequestParam(value="tutor_id", required=true) int tutor_id){
        return hasTpJdbcDao.patientList(tutor_id);
    }
}
