/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.dao.ClinicalFolderDao;
import com.povodev.hemme.rowmapper.ClinicalFolderMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        
        List<Map<String, Object>> rows;
        
        String sql = "SELECT * FROM clinicalfolder NATURAL JOIN clinicalevent WHERE user_id = ? ORDER BY date DESC";
	try{
            rows = this.jdbcTemplate.queryForList(sql, user_id);
        }catch (DataAccessException dataAccessException){
            System.err.println("***DAO :: getClinicalFolder FAIL, DataAccessException occurred, message follows.");
            System.err.println(dataAccessException);
            throw dataAccessException;
        }
        
        return ClinicalFolderMapper.getClinicalFolderMap(rows);
    }
    
    @Override
    public void newClinicalFolder(int user_id, int clinicalEvent_id) {
        try{
            this.jdbcTemplate.update(
                "INSERT INTO clinicalFolder (user_id, clinicalevent_id) values (?, ?)", 
                new Object[] {user_id, clinicalEvent_id});
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao::failed to CREATE NEW CLINICALFOLDER, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }
    }
    
}
