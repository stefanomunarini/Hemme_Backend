/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.HasTp;

/**
 *
 * @author gbonadiman.stage
 */
public interface HasTpDao {
       
    public HasTp getHasTp(int user_id);
    public void newHasTp(int user_id);

}
