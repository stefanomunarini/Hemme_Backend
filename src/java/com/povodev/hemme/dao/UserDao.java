package com.povodev.hemme.dao;

import com.povodev.hemme.bean.User;

/**
 * Interfaccia Dao contenente la dichiarazione dei metodi
 * @author Povodev
 */
public interface UserDao {
    
    public User getUser(int user_id);
    public boolean registration(User user);
    public User login(String email, String password,String imei);
    public String getAuthor(int user_id);
    public String passwordRecovery (String email);
    public boolean addNewLinkTutorPatient(int old_tutor_id,String IMEI);
    public boolean associaTutorPaziente(int tutore, int paziente);
    public String getTutorEmail(int patient_id);
}
