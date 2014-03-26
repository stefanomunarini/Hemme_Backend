package com.povodev.hemme.rest;

import com.povodev.hemme.bean.HasDp;
import com.povodev.hemme.bean.Test;
import com.povodev.hemme.jdbcdao.HasDpJdbcDao;
import com.povodev.hemme.jdbcdao.TestJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private HasDpJdbcDao hasJdbcDao;
    
    @RequestMapping("/newHasDp")
    public @ResponseBody void newHasDp(
            @RequestParam(value="patient_id", required=true) int patient_id,
            @RequestParam(value="doctor_id", required=true) int doctor_id) {
        
        hasJdbcDao.newHasDp(patient_id, doctor_id);
    }
}
