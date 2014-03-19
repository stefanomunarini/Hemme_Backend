/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Patient;

/**
 *
 * @author gbonadiman.stage
 */
public interface PatientDao {
    public Patient getPatient(int user_id);
    public void newPatient (int user_id);

}
