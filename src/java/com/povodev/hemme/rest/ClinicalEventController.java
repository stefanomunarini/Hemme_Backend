package com.povodev.hemme.rest;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.jdbcdao.ClinicalEventJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Classe controller per filtrare correttamente le richieste in input
 * @author Babol
 */
@Controller
public class ClinicalEventController {
    
    @Autowired
    private ClinicalEventJdbcDao clinicalEventJdbcDao;
    
    @RequestMapping("/getClinicalEvent")
    public @ResponseBody ClinicalEvent getClinicalEvent(
            @RequestParam(value="clinicalevent_id", required=true) int clinicalEvent_id) {
        
        return clinicalEventJdbcDao.getClinicalEvent(clinicalEvent_id);
    }
    
    @RequestMapping(value="/newClinicalEvent", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody boolean newClinicalEvent(
            @RequestParam(value="user_id", required=true) int user_id,
            @RequestBody ClinicalEvent clinicalEvent){
        
        return clinicalEventJdbcDao.newClinicalEvent(clinicalEvent, user_id);
    }
    
    @RequestMapping(value="/modifyClinicalEvent", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody boolean modifyClinicalEvent(
            @RequestBody ClinicalEvent clinicalEvent){
        
        return clinicalEventJdbcDao.modifyClinicalEvent(clinicalEvent);
    }
    
}
