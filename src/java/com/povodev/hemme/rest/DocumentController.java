/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Document;
import com.povodev.hemme.jdbcdao.DocumentJdbcDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gbonadiman.stage
 */
public class DocumentController {
    
    @RequestMapping("/getDocument")
    public @ResponseBody Document getDocument(
        @RequestParam(value="user_id", required=true) int user_id) {
        DocumentJdbcDao document = new DocumentJdbcDao();
        return document.getDocument(user_id);
    }
    
    @RequestMapping("/newDocument")
    public @ResponseBody Document newDocument(
            @RequestParam(value="user_id", required=true) int user_id) {
        DocumentJdbcDao document = new DocumentJdbcDao();
        return document.getDocument(user_id);
    }
    
}
