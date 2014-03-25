/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Result;
import java.util.ArrayList;


public interface TestDao {
    
    public void newTest(int user_id);
    public ArrayList<Result> getTest(int user_id);

}
