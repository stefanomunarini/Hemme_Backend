/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.HasTp;
import com.povodev.hemme.dao.HasTpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author gbonadiman.stage
 */
public class HasTpJdbcDao implements HasTpDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void newHasTp(int tutor_id, int patient_id) {
        
        String query = "INSERT INTO hastp (tutor_id, patient_id) VALUES (?,?)";
        try {
            this.jdbcTemplate.update(
                query, 
                new Object[] {tutor_id, patient_id});
        } catch (DataAccessException dae){
            throw dae;
        }
    }
}
