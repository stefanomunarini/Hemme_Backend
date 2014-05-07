/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.bean.ClinicalFolder;


/**
 * Public interface for ClinicalEventJdbcDao
 * @author smunarini.stage
 */
public interface ClinicalEventDao {
    
    /**
     * Retrieve a specific clinical event for a user
     * @param clinicalEvent_id
     * @return the clinical event
     */
    public ClinicalEvent getClinicalEvent(int clinicalEvent_id);
    
    /**
     * Retrieve a list of clinical events for a user
     * @return the clinical event
     * @param user_id
     */
    //public ArrayList<ClinicalEvent> getClinicalEvents(int user_id);
    
    /**
     * Create and insert a new clinical event for a given user
     * @param clinicalEvent
     * @param user_id
     */
    public boolean newClinicalEvent(ClinicalEvent clinicalEvent, int user_id);
    
    /**
     * Modify a clinical event
     * @param clinicalEvent
     */
    public boolean modifyClinicalEvent(ClinicalEvent clinicalEvent, int clinicalEvent_id);
}
