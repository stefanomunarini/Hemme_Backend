/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.bean;


/**
 *
 * @author Stefano
 */
public class ClinicalFolder {
    
    private int id;
    private int user_id;
    private int clinicalEvent_id;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the clinicalEvent_id
     */
    public int getClinicalEvent_id() {
        return clinicalEvent_id;
    }

    /**
     * @param clinicalEvent_id the clinicalEvent_id to set
     */
    public void setClinicalEvent_id(int clinicalEvent_id) {
        this.clinicalEvent_id = clinicalEvent_id;
    }
}
