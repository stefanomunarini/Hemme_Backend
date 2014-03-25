/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.dao.ClinicalFolderDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcDao implementation for ClinicalFolder
 * @author smunarini.stage
 */
public class ClinicalFolderJdbcDao implements ClinicalFolderDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public ArrayList<ClinicalEvent> getClinicalFolder(int user_id) {
                
        ArrayList<ClinicalEvent> clinicalFolder = new ArrayList();
        String sql = "SELECT * FROM clinicalevent";
	try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
            for (Map row : rows) {
                    ClinicalEvent clinicalEvent = new ClinicalEvent();
                    clinicalEvent.setAuthor((int) (row.get("author")));
                    clinicalEvent.setNote((String) (row.get("note")));
                    clinicalFolder.add(clinicalEvent);
            }        
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao::create diary FAIL, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
        
        return clinicalFolder;//jdbcTemplate.execute("insert into STUDENT (name) values (?)",user_id );
    }
    
    @Override
    public void newClinicalFolder(int user_id, int clinicalEvent_id) {
        
    }
    
}
