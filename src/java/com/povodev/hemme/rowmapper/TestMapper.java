package com.povodev.hemme.rowmapper;

import com.povodev.hemme.bean.Result;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
