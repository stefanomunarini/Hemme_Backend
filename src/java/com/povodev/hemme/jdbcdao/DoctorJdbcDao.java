/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Doctor;
import com.povodev.hemme.dao.DoctorDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcDao implementation for Doctor
 * @author smunarini.stage
 */
public class DoctorJdbcDao implements DoctorDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Doctor getDoctor(int doctor_id) {
        System.err.println(jdbcTemplate==null);
        Doctor doctor = null;
        String sql = "SELECT * FROM doctor WHERE user_id = ?";
        try{
            doctor = (Doctor) this.jdbcTemplate.queryForObject(
                sql, new Object[] { doctor_id }, 
                new BeanPropertyRowMapper(Doctor.class));
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao:: fail to GET DOCUMENT, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
        return doctor;
    }

    @Override
    public void newDoctor(int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Doctor> getAllDoctors(int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
