/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.ClinicalFolder;
import com.povodev.hemme.jdbcdao.ClinicalFolderJdbcDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author smunarini.stage
 */
@Controller
public class ClinicalFolderController {
    
    @RequestMapping("/getClinicalFolder")
    public @ResponseBody ClinicalFolder getClinicalFolder(
            @RequestParam(value="user_id", required=true) int user_id) {
        ClinicalFolderJdbcDao clinicalFolder = new ClinicalFolderJdbcDao();
        return clinicalFolder.getClinicalFolder(user_id);
    }
    
    @RequestMapping("/newClinicalFolder")
    public @ResponseBody void newClinicalFolder(
            @RequestParam(value="user_id", required=true) int user_id,
            @RequestParam(value="clinicalEvent_id", required=true) int clinicalEvent_id) {
        ClinicalFolderJdbcDao clinicalFolder = new ClinicalFolderJdbcDao();
        clinicalFolder.newClinicalFolder(user_id,clinicalEvent_id);
    }
    
}
