/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.dao.ClinicalFolderDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcDao implementation for ClinicalFolder
 * @author smunarini.stage
 */
public class ClinicalFolderJdbcDao implements ClinicalFolderDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public ArrayList<ClinicalEvent> getClinicalFolder(int user_id) {
        
        System.err.println("Is this null? " + jdbcTemplate==null);
        
        ArrayList<ClinicalEvent> cce = new ArrayList();
        ClinicalEvent ce;
        for (int i=0;i<2;i++){
            String sql = "SELECT * FROM clinicalevent WHERE ID = ?";
            try{
                ce = (ClinicalEvent) jdbcTemplate.queryForObject(
                    sql, new Object[] { user_id }, 
                    new BeanPropertyRowMapper(ClinicalEvent.class));
            }catch (DataAccessException runtimeException){
                System.err.println("***Dao:: fail to GET DOCUMENT, RuntimeException occurred, message follows.");
                System.err.println(runtimeException);
                throw runtimeException;
            }
                        
            ce.setNote("fjfd");
            ce.setTherapy("sdjdj");
            cce.add(ce);
            
        }
        
        return cce;//jdbcTemplate.execute("insert into STUDENT (name) values (?)",user_id );
    }
    
    @Override
    public void newClinicalFolder(int user_id, int clinicalEvent_id) {
        
    }
    
}
