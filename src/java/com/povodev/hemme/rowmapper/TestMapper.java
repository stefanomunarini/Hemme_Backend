/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rowmapper;

import com.povodev.hemme.bean.Result;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author smunarini.stage
 */
public class TestMapper {
    
    public static ArrayList<Result> getTestMap (List<Map<String, Object>> rows){
        ArrayList<Result> test = new ArrayList();
        for (Map row : rows) {
                Result result = new Result();
                result.setId((int) (row.get("id")));
                result.setGrade((String) (row.get("grade")));
                result.setTime((int) (row.get("time")));
                result.setDate((Timestamp) (row.get("date")));
                test.add(result);
            }
        return test;
    }
    
}
