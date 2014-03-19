/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.ClinicalFolder;
import com.povodev.hemme.dao.ClinicalFolderDao;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author smunarini.stage
 */
public class ClinicalFolderJdbcDao implements ClinicalFolderDao{

    @Override
    public ClinicalFolder getClinicalFolder(int user_id) {
        return null;//jdbcTemplate.execute("insert into STUDENT (name) values (?)",user_id );
    }
    
    
    
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void newClinicalFolder(int user_id) {
        
    }
    
}
