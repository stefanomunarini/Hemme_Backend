/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rowmapper;

import com.povodev.hemme.bean.ClinicalEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author smunarini.stage
 */
public class ClinicalFolderMapper {
    
    public static ArrayList<ClinicalEvent> getClinicalFolderMap (List<Map<String, Object>> rows){
        ArrayList<ClinicalEvent> clinicalFolder = new ArrayList();
        for (Map row : rows) {
                ClinicalEvent clinicalEvent = new ClinicalEvent();
                clinicalEvent.setId((int) (row.get("id")));
                clinicalEvent.setAuthor((int) (row.get("author")));
                clinicalEvent.setDate((Timestamp) (row.get("date")));
                clinicalEvent.setTherapy((String) (row.get("therapy")));
                clinicalEvent.setNote((String) (row.get("note")));
                clinicalEvent.setAuthor_name((String) row.get("name") + " " + row.get("surname"));
                clinicalFolder.add(clinicalEvent);
            }
        return clinicalFolder;
    }
    
}
