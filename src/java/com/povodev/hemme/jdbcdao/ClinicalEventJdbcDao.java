/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.bean.ClinicalFolder;
import com.povodev.hemme.dao.ClinicalEventDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

/**
 * JdbcDao implementation for ClinicalEvent
 * @author smunarini.stage
 */
@ContextConfiguration(locations={"classpath:WEB-INF/applicationContext.xml"})
public class ClinicalEventJdbcDao implements ClinicalEventDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public ClinicalEvent getClinicalEvent(ClinicalFolder clinicalFolder) {
        
        ClinicalEvent clinicalEvent;
        String sql = "SELECT * FROM clinicalevent WHERE user_id = ? AND clinicalevent_id = ?";
       
        try{
            clinicalEvent = (ClinicalEvent) this.jdbcTemplate.queryForObject(
                sql, new Object[] {clinicalFolder.getUser_id(), clinicalFolder.getClinicalEvent_id()}, 
                new BeanPropertyRowMapper(ClinicalEvent.class));
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao:: fail to GET DOCUMENT, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
        return clinicalEvent;
    }

    @Override
    public void newClinicalEvent(ClinicalEvent clinicalEvent) {
        try{
            this.jdbcTemplate.update(
                "INSERT INTO clinicalevent (author,date,therapy,note) values (?, ?, ?, ?)", 
                new Object[] {clinicalEvent.getAuthor(), clinicalEvent.getDate(), clinicalEvent.getTherapy(), clinicalEvent.getNote()});
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao::fail to CREATE NEW DOCUMENT, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
    }
}