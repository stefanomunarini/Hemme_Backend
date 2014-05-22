package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Access;
import java.util.ArrayList;


/**
 * Interfaccia Dao contenente la dichiarazione dei metodi
 * @author Babol
*/
public interface AccessDao {
    
    public boolean registrationAccess(String email, String password,String imei, int role, int status);
    public Access getAccess(int access_id);
    public boolean editAccess(Access access);
    public boolean deleteAccess(int access_id);
    public ArrayList<Access> getDevicesList(String email, String passwordtutor_id);
    
}
