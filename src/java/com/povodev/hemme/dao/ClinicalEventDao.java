package com.povodev.hemme.dao;

import com.povodev.hemme.bean.ClinicalEvent;

/**
 * Interfaccia Dao contenente la dichiarazione dei metodi
 * @author Povodev
 */
public interface ClinicalEventDao {
    public ClinicalEvent getClinicalEvent(int clinicalEvent_id);
    public boolean newClinicalEvent(ClinicalEvent clinicalEvent, int user_id);
    public boolean modifyClinicalEvent(ClinicalEvent clinicalEvent);
}
