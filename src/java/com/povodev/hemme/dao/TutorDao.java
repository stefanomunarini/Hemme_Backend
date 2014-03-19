/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Tutor;

/**
 *
 * @author gbonadiman.stage
 */
public interface TutorDao {
    
    public Tutor getTutor(int user_id);
    public void newTutor (int user_id);

    
}
