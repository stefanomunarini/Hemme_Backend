package com.povodev.hemme.rest;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.jdbcdao.ClinicalFolderJdbcDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Controller for ClinicalFolder
 */
@Controller
public class ClinicalFolderController {
    
    @Autowired
    private ClinicalFolderJdbcDao clinicalFolderJdbcDao;
    
    @RequestMapping("/getClinicalFolder")
    public @ResponseBody ArrayList<ClinicalEvent> getClinicalFolder(
            @RequestParam(value="user_id", required=true) int user_id) {
        return clinicalFolderJdbcDao.getClinicalFolder(user_id);
    }
}
