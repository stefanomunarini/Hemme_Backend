/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Result;


/**
 *
 * @author gbonadiman.stage
 */
public interface ResultDao {
    public Result getResult(int user_id);
    public void newResult (int user_id);
}
