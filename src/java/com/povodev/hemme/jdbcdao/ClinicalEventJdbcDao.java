/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.dao.ClinicalEventDao;
import java.util.ArrayList;

/**
 *
 * @author smunarini.stage
 */
public class ClinicalEventJdbcDao implements ClinicalEventDao{

    @Override
    public ClinicalEvent getClinicalEvent(int user_id, int clinicalEvent_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ClinicalEvent> getClinicalEvents(int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void newClinicalEvent(int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
