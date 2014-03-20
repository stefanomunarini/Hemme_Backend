/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Doctor;
import java.util.ArrayList;


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
    
    /**
     * Retrive the list of doctors of user_id
     * @param user_id
     * @return 
     */
    public ArrayList<Doctor> getAllDoctors(int user_id);

}
