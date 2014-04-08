/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Document;
import com.povodev.hemme.jdbcdao.DocumentJdbcDao;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;



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
    
//    @RequestMapping(value="/newDocument", method = {RequestMethod.GET,RequestMethod.POST})
//    public @ResponseBody boolean newDocument(
//            @RequestParam(value="user_id", required=true) int user_id,
//            @RequestBody Document document){
//        return documentJdbcDao.insertDocument(user_id,document);
//    }
 
    
    
    @RequestMapping(value="/uploadDocument", method=RequestMethod.POST)
    public @ResponseBody boolean documentUpload(
            @RequestParam("file") MultipartFile file) throws IOException{
        
        if(!file.isEmpty()){
            
            InputStream inputStream = null;  
            OutputStream outputStream = null;  
  
            String fileName = file.getOriginalFilename();  
            System.err.println("original File Name = " + fileName);
  
            try {  
                inputStream = file.getInputStream();
                
                File newFile = new File("C:/Users/smunarini.stage/Desktop/" + fileName);  
                if (!newFile.exists()) {  
                    newFile.createNewFile();  
                }  
        
                outputStream = new FileOutputStream(newFile);  
                int read = 0;  
                byte[] bytes = new byte[1024];  
  
                while ((read = inputStream.read(bytes)) != -1) {  
                    outputStream.write(bytes, 0, read);  
                }
                
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }      
            inputStream.close();
            outputStream.close();
            return true;
        }else{
            return false;
        }
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
