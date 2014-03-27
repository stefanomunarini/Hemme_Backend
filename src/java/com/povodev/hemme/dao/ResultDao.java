
package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Result;


public interface ResultDao {
    
    public Result getResult(int result_id);
    public boolean insertResult(Result result,int user_id);
    
}
