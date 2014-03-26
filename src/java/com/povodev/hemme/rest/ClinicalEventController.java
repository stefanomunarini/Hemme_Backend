/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.bean.ClinicalFolder;
import com.povodev.hemme.jdbcdao.ClinicalEventJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Controller for ClinicalEvent
 * @author smunarini.stage
 */
@Controller
public class ClinicalEventController {
    
    @Autowired
    private ClinicalEventJdbcDao clinicalEventJdbcDao;
    
    @RequestMapping("/getClinicalEvent")
    public @ResponseBody ClinicalEvent getClinicalEvent(
            /*@RequestParam(value="user_id", required=true) int user_id,
            @RequestParam(value="clinicalEvent_id", required=true) int clinicalEvent_id*/
            @RequestParam(value="clinicalevent_id", required=true) int clinicalEvent_id) {
        
        return clinicalEventJdbcDao.getClinicalEvent(clinicalEvent_id);
    }
    
    @RequestMapping("/newClinicalEvent")
    public @ResponseBody void newClinicalEvent(
            /*@RequestParam(value="user_id", required=true) int user_id,
            @RequestParam(value="clinicalEvent_id", required=true) int clinicalEvent_id*/
            @RequestParam(value="clinical_event", required=true) ClinicalEvent clinicalEvent,
            @RequestParam(value="user_id", required=true) int user_id) {
        
        clinicalEventJdbcDao.newClinicalEvent(clinicalEvent, user_id);
    }
    
    
}
