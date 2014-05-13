/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Doctor;
import com.povodev.hemme.bean.User;
import com.povodev.hemme.dao.DoctorDao;
import com.povodev.hemme.rowmapper.DoctorMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public ArrayList<User> getAllDoctors() {

        String sql = "SELECT * FROM user WHERE role = ?";
	try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,1);
            return DoctorMapper.getListDoctor(rows,this.jdbcTemplate);
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao::create diary FAIL, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }
}
    
}
