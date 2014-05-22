package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Result;
import java.util.ArrayList;

/**
 * Interfaccia Dao contenente la dichiarazione dei metodi
 * @author Babol
 */
public interface TestDao {
    
    public void newTest(int user_id, int result_id);
    public ArrayList<Result> getTest(int user_id);

}
