/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Test;


public interface TestDao {
       
    public Test getTest(int user_id);
    public void newTest(int user_id);

}