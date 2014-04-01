/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.User;
import java.util.ArrayList;

/**
 *
 * @author gbonadiman.stage
 */
public interface AccessDao {
    
    public ArrayList<User> getTutorDevice (int tutor_id);
    public boolean confirmConnection(int tutor_id,int doctor_id,int patient_id);
    public boolean deleteConnection(int id_access);
    
}
