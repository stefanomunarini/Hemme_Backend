/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rowmapper;

import com.povodev.hemme.bean.Document;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gbonadiman.stage
 */
public class DocumentMapper {
    
    public static ArrayList<Document> getDiaryMap(List<Map<String, Object>> rows){
        ArrayList<Document> diario = new ArrayList();

        for (Map row : rows) {
            Document document = new Document();
            Timestamp time = (Timestamp) row.get("date");
            document.setDate( (Date) toDate(time));
            document.setFile((String)row.get("file"));
            diario.add(document);
        }              
        return diario;
    }
            
            
    /**
     * Convert TIMESTAMP to java.sql.Date
     * @param timestamp
     * @return 
     */
    public static java.util.Date toDate(java.sql.Timestamp timestamp) {
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return new java.sql.Date(milliseconds);
    }
}
