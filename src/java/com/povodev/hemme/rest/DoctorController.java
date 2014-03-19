/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Doctor;
import com.povodev.hemme.jdbcdao.DoctorJdbcDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Controller for Doctor
 * @author smunarini.stage
 */
@Controller
public class DoctorController {
    
    @RequestMapping("/getDoctor")
    public @ResponseBody Doctor getDoctor(
        @RequestParam(value="doctor_id", required=true) int doctor_id) {
        DoctorJdbcDao doctor = new DoctorJdbcDao();
        return doctor.getDoctor(doctor_id);
    }
    
    @RequestMapping("/newDoctor")
    public @ResponseBody void newDoctor(
        @RequestParam(value="user_id", required=true) int user_id) {
        DoctorJdbcDao doctor = new DoctorJdbcDao();
        doctor.newDoctor(user_id);
    }
    
}