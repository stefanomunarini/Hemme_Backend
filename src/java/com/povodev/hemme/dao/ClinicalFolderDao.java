package com.povodev.hemme.dao;

import com.povodev.hemme.bean.ClinicalEvent;
import java.util.ArrayList;


/**
 * Interfaccia Dao contenente la dichiarazione dei metodi
 * @author Povodev
 */
public interface ClinicalFolderDao {
    
    public ArrayList<ClinicalEvent> getClinicalFolder(int user_id);
    public void newClinicalFolder(int user_id, int clinicalEvent_id);

}
