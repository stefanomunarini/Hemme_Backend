/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Tutor;
import com.povodev.hemme.jdbcdao.TutorJdbcDao;
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
public class TutorController {
    
    @Autowired
    TutorJdbcDao tutorJdbcDao;
    
    /*@RequestMapping("/getTutor")
    public @ResponseBody Tutor getTutor(
        @RequestParam(value="user_id", required=true) int user_id) {
        
        return tutorJdbcDao.getTutor(user_id);
    }
    
    @RequestMapping("/newTutor")
    public @ResponseBody Tutor newTutor(
            @RequestParam(value="user_id", required=true) int user_id) {
        
        return tutorJdbcDao.getTutor(user_id);
    }*/
    
    @RequestMapping("/registerPatientDevice")
    public @ResponseBody boolean newTutor(
            @RequestParam(value="user_id", required=true) int user_id,
            @RequestParam(value="imei", required=true) String imei) {
        
        return tutorJdbcDao.registerPatientDevice(user_id,imei);
    }
}
