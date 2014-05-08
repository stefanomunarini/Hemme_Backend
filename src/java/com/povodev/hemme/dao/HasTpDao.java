/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.HasTp;
import com.povodev.hemme.bean.User;
import java.util.ArrayList;

/**
 *
 * @author gbonadiman.stage
 */
public interface HasTpDao {
       
    public ArrayList<User> patientList(int tutor_id);

}
