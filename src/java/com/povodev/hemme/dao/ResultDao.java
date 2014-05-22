package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Result;

/**
 * Interfaccia Dao contenente la dichiarazione dei metodi
 * @author Babol
 */
public interface ResultDao {
    
    public Result getResult(int result_id);
    public boolean insertResult(Result result,int user_id);
    
}
