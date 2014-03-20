/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.bean;

/**
 *
 * @author gbonadiman.stage
 */
public class HasTp {
    
    private int id;
    private int patient_id;
    private int tutor_id;

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
     * @return the patient_id
     */
    public int getPatient_id() {
        return patient_id;
    }

    /**
     * @param patient_id the patient_id to set
     */
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    /**
     * @return the tutor_id
     */
    public int getTutor_id() {
        return tutor_id;
    }

    /**
     * @param tutor_id the tutor_id to set
     */
    public void setTutor_id(int tutor_id) {
        this.tutor_id = tutor_id;
    }
    
}
