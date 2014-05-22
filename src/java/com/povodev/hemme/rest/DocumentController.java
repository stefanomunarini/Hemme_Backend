package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Document;
import com.povodev.hemme.jdbcdao.DocumentJdbcDao;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * Classe controller per filtrare correttamente le richieste in input
 * @author Babol
 */
@Controller
public class DocumentController{
    
    
    @Autowired
    private DocumentJdbcDao documentJdbcDao;
    
    @Autowired
    ServletContext sc;

    @Autowired
    ServletRequest sr;

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

    
    @RequestMapping(value="/uploadDocument", method=RequestMethod.POST)
    public @ResponseBody boolean documentUpload(
            @RequestHeader(value="salt") String salt,
            @RequestParam("file") MultipartFile file,
            @RequestParam("idu") int user_id,
            @RequestParam("nota") String nota) throws IOException{
        
        //String dirName = sr.getServletContext().getRealPath("Resources/");
        String dirName = "http://172.24.200.12:8080/data/hemme/context";
        
        File file1 = new File(dirName);
        if (!file1.exists()){
            file1.mkdir();
        }
        return documentJdbcDao.insertDocument(file,nota,user_id,dirName);        
    }
    
    @RequestMapping(value="/uploadDocumentWithoutFile")
    public @ResponseBody boolean documentUpload(
            @RequestHeader(value="salt") String salt,
            @RequestParam("idu") int user_id,
            @RequestParam("nota") String nota) throws IOException{

        return documentJdbcDao.insertDocument(null,nota,user_id,"");        

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
