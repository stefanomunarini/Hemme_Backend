/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.HasDp;
import com.povodev.hemme.dao.HasDpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author gbonadiman.stage
 */
public class HasDpJdbcDao implements HasDpDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void newHasDp(int user_id, int doctor_id) {
        
        String query = "INSERT INTO hasdp (patient_id, doctor_id) VALUES (?,?)";
        
        try {
            this.jdbcTemplate.update(
                query, 
                new Object[] {user_id, doctor_id});
        } catch (DataAccessException dae){
            System.err.println("***Dao::fail to CREATE NEW clinicalevent, RuntimeException occurred, message follows.");
            System.err.println(dae);
            throw dae;
        }
        
    }
    
}
