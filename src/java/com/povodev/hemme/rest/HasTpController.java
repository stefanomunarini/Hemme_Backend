package com.povodev.hemme.rest;

import com.povodev.hemme.bean.User;
import com.povodev.hemme.jdbcdao.HasTpJdbcDao;
import java.util.ArrayList;
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
public class HasTpController {
   
    @Autowired
    private HasTpJdbcDao hasTpJdbcDao;
    
    @RequestMapping("/patientList")
    public @ResponseBody ArrayList<User> newHasTp(
            @RequestParam(value="tutor_id", required=true) int tutor_id){
        return hasTpJdbcDao.patientList(tutor_id);
    }
}
