/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.jdbcdao.ClinicalEventJdbcDao;
import java.util.ArrayList;
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
    
    @RequestMapping("/getClinicalEvent")
    public @ResponseBody ClinicalEvent getClinicalEvent(
            @RequestParam(value="user_id", required=true) int user_id,
            @RequestParam(value="clinicalEvent_id", required=true) int clinicalEvent_id) {
        ClinicalEventJdbcDao clinicalEvent = new ClinicalEventJdbcDao();
        return clinicalEvent.getClinicalEvent(user_id, clinicalEvent_id);
    }
    
    
}
