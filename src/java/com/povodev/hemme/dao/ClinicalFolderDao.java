/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.bean.ClinicalFolder;
import java.util.ArrayList;

/**
 * Public interface for ClinicalFolderJdbcDao
 * @author smunarini.stage
 */
public interface ClinicalFolderDao {
    
    /**
     * Retrieve a clinical folder for a user
     * @return the clinical folder
     * @param user_id
     */
    public ArrayList<ClinicalEvent> getClinicalFolder(int user_id);
    
    /**
     * Create and insert a new clinical folder for a given user
     * @param user_id
     * @param clinicalEvent_id
     */
    public void newClinicalFolder(int user_id, int clinicalEvent_id);
    
    
}
