package com.povodev.hemme.rest;

import com.povodev.hemme.jdbcdao.TutorJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Classe controller per filtrare correttamente le richieste in input
 * @author Povodev
 */
@Controller
public class TutorController {
    
    @Autowired
    TutorJdbcDao tutorJdbcDao;
   
    @RequestMapping("/registerPatientDevice")
    public @ResponseBody boolean newTutor(
            @RequestParam(value="user_id", required=true) int user_id,
            @RequestParam(value="imei", required=true) String imei) {
        
        return tutorJdbcDao.registerPatientDevice(user_id,imei);
    }
}
