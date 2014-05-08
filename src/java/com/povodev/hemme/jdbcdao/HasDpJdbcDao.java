/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.User;
import com.povodev.hemme.dao.HasDpDao;
import com.povodev.hemme.rowmapper.HasMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public void newHasDp(int patient_id, int doctor_id) {
        
        String query = "INSERT INTO hasdp (patient_id, doctor_id) VALUES (?,?)";
        try {
            this.jdbcTemplate.update(
                query, 
                new Object[] {patient_id, doctor_id});
        } catch (DataAccessException dae){
            throw dae;
        }
    }
    
    @Override
    public ArrayList<User> patientList(int doctor_id) {

        String sql = "SELECT * FROM dp WHERE doctor_id = ?";
	try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,doctor_id);
            return HasMapper.getListPatient(rows, this.jdbcTemplate);
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao::create list of patient HasTp, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }
    
    
    }

    
}
