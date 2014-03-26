/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Document;
import com.povodev.hemme.dao.DocumentDao;
import com.povodev.hemme.rowmapper.DocumentMapper;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class DocumentJdbcDao implements DocumentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Return document from its Id
     * @param document_id
     * @return 
     */
    @Override
    public Document getDocument(int document_id) {
        
        Document document = new Document();
        String sql = "SELECT * FROM DOCUMENT WHERE ID = ?";
       
        try{
            document = (Document) this.jdbcTemplate.queryForObject(
                sql, new Object[] { document_id }, 
                new BeanPropertyRowMapper(Document.class));
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao:: fail to GET DOCUMENT, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
        return document;
    }

    
    
    /**
     * Insert new document
     * @param document 
     */
    @Override
    public boolean newDocument(Document document) {
        
        try{
            this.jdbcTemplate.update(
                "insert into DOCUMENT (date,file) values (?, ?)", 
                new Object[] {document.getDate(),document.getFile()});
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao::fail to CREATE NEW DOCUMENT, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
        return true;
    }

    /**
     * Edit input document
     * @param document 
     */
    @Override
    public boolean editDocument(Document document) {
        try{
            this.jdbcTemplate.update(
                "update DOCUMENT set id = ?,date = ?, file = ?  where id = ?", 
                new Object[] {document.getId(), document.getDate(), document.getFile(),document.getId()});
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao:: edit Document FAIL, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
        return true;
    }

    /**
     * Delete document from id_document
     * @param document_id
     * @return 
     */
    @Override
    public boolean deleteDocument(int document_id) {
    
        
        String deleteStatement1 = "DELETE FROM DIARY WHERE document_id = ?";
        String deleteStatement2 = "DELETE FROM DOCUMENT WHERE id = ?";
        
        try{
            this.jdbcTemplate.update(deleteStatement1, document_id);
            this.jdbcTemplate.update(deleteStatement2, document_id);
        }catch (RuntimeException runtimeException){
            System.err.println("***NagiosHostDao::DELETE DOCUMENT FAILED, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
        return true;
    }

    /**
     * Return diary of IdUser
     * @param user_id
     * @return 
     */
    @Override
    public ArrayList<Document> getDiary(int user_id) {

    	String sql = "SELECT * FROM document NATURAL JOIN diary WHERE user_id = ?";
	try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,user_id);
            return DocumentMapper.getDiaryMap(rows);
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao::create diary FAIL, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
    }
    
}
