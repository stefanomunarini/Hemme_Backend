/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Document;
import com.povodev.hemme.jdbcdao.DocumentJdbcDao;
import java.util.ArrayList;
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
        @RequestParam(value="document_id", required=true) int document_id) {
        DocumentJdbcDao document = new DocumentJdbcDao();
        return document.getDocument(document_id);
    }
    
    @RequestMapping("/newDocument")
    public @ResponseBody boolean newDocument(
            @RequestParam(value="new_document", required=true) Document new_document) {
        DocumentJdbcDao document = new DocumentJdbcDao();
        return document.newDocument(new_document);
    }
 
    @RequestMapping("/editDocument")
    public @ResponseBody boolean editDocument(
            @RequestParam(value="document_edit", required=true) Document document_edit) {
        DocumentJdbcDao document = new DocumentJdbcDao();
        return document.editDocument(document_edit);
    }
    
    @RequestMapping("/deleteDocument")
    public @ResponseBody boolean editDocument(
            @RequestParam(value="document_id", required=true) int document_id) {
        DocumentJdbcDao document = new DocumentJdbcDao();
        return document.deleteDocument(document_id);
    }
    
    @RequestMapping("/getDiary")
    public @ResponseBody ArrayList<Document> getDiary(
            @RequestParam(value="user_id", required=true) int user_id) {
        DocumentJdbcDao document = new DocumentJdbcDao();
        return document.getDiary(user_id);
    }
    
    
    
    
    
}
