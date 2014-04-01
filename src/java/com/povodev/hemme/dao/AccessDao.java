/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Access;
import com.povodev.hemme.bean.User;
import java.util.ArrayList;

/**
 *
 * @author gbonadiman.stage
 */
public interface AccessDao {
    
    /**
     * Chiamare questo metodo durante il login
     * @param email
     * @param password
     * @param imei
     * @param role
     * @return 
     */
    public boolean registrationAccess(String email, String password,String imei, int role, int status);
    
    /**
     * Utilizzato per vedere se l'utente esiste in Access
     * @param email
     * @param password
     * @return 
     */
    public Access getAccess(int access_id);
    
    /**
     * Per poter modificare un access
     * @param id_access
     * @return 
     */
    public boolean editAccess(Access access);
    
    /**
     * Delete Access data
     * @param access_id
     * @return 
     */
    public boolean deleteAccess(int access_id);
    
    /**
     * Restituisce la lista di Access con status da definire
     * @return 
     */
    public ArrayList<Access> getDevicesList(String email, String passwordtutor_id);
    
}
