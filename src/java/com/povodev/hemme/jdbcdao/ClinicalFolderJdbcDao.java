/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.bean.ClinicalFolder;
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
        ArrayList<ClinicalEvent> clinicalFolder;
        
        String sql = "SELECT * FROM clinicalevent";
	try{
            rows = this.jdbcTemplate.queryForList(sql);
        }catch (DataAccessException dataAccessException){
            System.err.println("***DAO :: getClinicalFolder FAIL, DataAccessException occurred, message follows.");
            System.err.println(dataAccessException);
            throw dataAccessException;
        }
        
        clinicalFolder = ClinicalFolderMapper.getClinicalFolderMap(rows);
        
        return clinicalFolder;
    }
    
    @Override
    public void newClinicalFolder(ClinicalFolder clinicalFolder) {
        try{
            this.jdbcTemplate.update(
                "INSERT INTO clinicalFolder (user_id, clinicalevent_id) values (?, ?)", 
                new Object[] {clinicalFolder.getUser_id(),clinicalFolder.getClinicalEvent_id()});
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao::failed to CREATE NEW CLINICALFOLDER, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }
    }
    
}
