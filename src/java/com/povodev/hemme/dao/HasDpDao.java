/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.HasDp;
import com.povodev.hemme.bean.User;
import java.util.ArrayList;

/**
 *
 * @author gbonadiman.stage
 */
public interface HasDpDao {

    public boolean newHasDp(int patient_id, int doctor_id);
    public ArrayList<User> patientList(int doctor_id);

}
