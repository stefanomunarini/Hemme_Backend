/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.ClinicalFolder;

/**
 *
 * @author smunarini.stage
 */
public interface ClinicalFolderDao {
    
    public ClinicalFolder getClinicalFolder(int user_id);
    
    public void newClinicalFolder(int user_id);
    
}
