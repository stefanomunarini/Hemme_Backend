package com.povodev.hemme.rowmapper;

import com.povodev.hemme.bean.ClinicalEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClinicalFolderMapper {
    
    public static ArrayList<ClinicalEvent> getClinicalFolderMap (List<Map<String, Object>> rows){
        ArrayList<ClinicalEvent> clinicalFolder = new ArrayList();
        for (Map row : rows) {
                ClinicalEvent clinicalEvent = new ClinicalEvent();
                clinicalEvent.setId((int) (row.get("clinicalevent_id")));
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
