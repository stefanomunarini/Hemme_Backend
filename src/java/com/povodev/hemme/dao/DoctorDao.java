/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Doctor;

/**
 * Public interface for DoctorJdbcDao
 * @author smunarini.stage
 */
public interface DoctorDao {
    
    /**
     * Retrieve a doctor
     * @return the doctor associated with the given id
     * @param doctor_id
     */
    public Doctor getDoctor(int doctor_id);
    
    /**
     * Create and insert a new doctor
     * @param user_id
     */
    public void newDoctor(int user_id);
    
}
