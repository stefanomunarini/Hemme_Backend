package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Doctor;
import com.povodev.hemme.bean.User;
import java.util.ArrayList;
/**
 * Interfaccia Dao contenente la dichiarazione dei metodi
 * @author Babol
 */
public interface DoctorDao {
    
    public Doctor getDoctor(int doctor_id);
    public void newDoctor(int user_id);
    public ArrayList<User> getAllDoctors();
    
}
