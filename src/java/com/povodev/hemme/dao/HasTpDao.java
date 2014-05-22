package com.povodev.hemme.dao;

import com.povodev.hemme.bean.User;
import java.util.ArrayList;

/**
 * Interfaccia Dao contenente la dichiarazione dei metodi
 * @author Povodev
 */
public interface HasTpDao {       
    public ArrayList<User> patientList(int tutor_id);
}
