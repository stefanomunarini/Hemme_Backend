/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Patient;
import com.povodev.hemme.jdbcdao.PatientJdbcDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gbonadiman.stage
 */
@Controller
public class PatientController {
    
    @RequestMapping("/getPatient")
    public @ResponseBody Patient getPatient(
        @RequestParam(value="user_id", required=true) int user_id) {
        PatientJdbcDao patient = new PatientJdbcDao();
        return patient.getPatient(user_id);
    }
    
    @RequestMapping("/newPatient")
    public @ResponseBody Patient newPatient(
            @RequestParam(value="user_id", required=true) int user_id) {
        PatientJdbcDao patient = new PatientJdbcDao();
        return patient.getPatient(user_id);
    }

}
