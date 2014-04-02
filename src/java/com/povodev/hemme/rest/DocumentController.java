/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Document;
import com.povodev.hemme.jdbcdao.DocumentJdbcDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class DocumentController {
    
    
    @Autowired
    private DocumentJdbcDao documentJdbcDao;
    
    static org.apache.log4j.Logger log = Logger.getLogger(DocumentController.class);
    
    @RequestMapping("/getDocument")
    public @ResponseBody Document getDocument(
        @RequestParam(value="document_id", required=true) int document_id) {
        
        log.trace("--------------------PROVA LOG DA UTILIZZARE--------------------");
        log.debug("asdDebug");
        log.info("asdInfo");
        log.warn("asdWarn");
        log.error("asdErrorasddasdsadsTrace");
        log.fatal("asdFatal");
        
        
        return documentJdbcDao.getDocument(document_id);
    }
    
    @RequestMapping(value="/newDocument", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody boolean newDocument(
            @RequestParam(value="user_id", required=true) int user_id,
            @RequestBody Document document){
        return documentJdbcDao.insertDocument(user_id,document);
    }
 
    
    
    
    
    
    
    @RequestMapping("/editDocument")
    public @ResponseBody boolean editDocument(
            @RequestParam(value="document_edit", required=true) Document document_edit) {
        return documentJdbcDao.editDocument(document_edit);
    }
    
    @RequestMapping("/deleteDocument")
    public @ResponseBody boolean editDocument(
            @RequestParam(value="document_id", required=true) int document_id) {
        return documentJdbcDao.deleteDocument(document_id);
    }
    
    @RequestMapping("/getDiary")
    public @ResponseBody ArrayList<Document> getDiary(
            @RequestParam(value="user_id", required=true) int user_id) {
        return documentJdbcDao.getDiary(user_id);
    }
    
    
    
    
    
}
