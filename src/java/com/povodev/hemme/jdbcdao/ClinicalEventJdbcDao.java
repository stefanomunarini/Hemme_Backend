/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.dao.ClinicalEventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * JdbcDao implementation for ClinicalEvent
 * @author smunarini.stage
 */
public class ClinicalEventJdbcDao implements ClinicalEventDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ClinicalFolderJdbcDao clinicalFolderJdbcDao;
    
    @Override
    public ClinicalEvent getClinicalEvent(int clinicalEvent_id) {
        
        ClinicalEvent clinicalEvent;
        String sql = "SELECT * FROM clinicalevent WHERE clinicalevent_id = ?";
       
        try{
            clinicalEvent = (ClinicalEvent) this.jdbcTemplate.queryForObject(
                sql, new Object[] { (clinicalEvent_id)}, 
                new BeanPropertyRowMapper(ClinicalEvent.class));
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao:: fail to GET DOCUMENT, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
        return clinicalEvent;
    }

    @Override
    public void newClinicalEvent(ClinicalEvent clinicalEvent, int user_id) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO clinicalevent (author,date,therapy,note) values (?, ?, ?, ?)";
        try {
            this.jdbcTemplate.update(
                query, 
                new Object[] {clinicalEvent.getAuthor(), clinicalEvent.getDate(), clinicalEvent.getTherapy(), clinicalEvent.getNote()},
                keyHolder);
        } catch (DataAccessException runtimeException){
            System.err.println("***Dao::fail to CREATE NEW clinicalevent, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }
        
        int clinicalEvent_id = (int) keyHolder.getKey();
        clinicalFolderJdbcDao.newClinicalFolder(user_id,clinicalEvent_id);
    }
}
