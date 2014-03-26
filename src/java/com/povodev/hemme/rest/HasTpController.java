/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.HasTp;
import com.povodev.hemme.jdbcdao.HasTpJdbcDao;
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
    
    @RequestMapping("/newHasTp")
    public @ResponseBody void newHasTp(
            @RequestParam(value="tutor_id", required=true) int tutor_id,
            @RequestParam(value="patient_id", required=true) int patient_id) {
        
        hasTpJdbcDao.newHasTp(tutor_id, patient_id);
    }
}
