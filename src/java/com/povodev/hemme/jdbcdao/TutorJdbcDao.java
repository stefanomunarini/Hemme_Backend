package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.dao.TutorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * Classe JdbcDao che implementa il corpo di tutte le funzioni dichiarate nell'interfaccia
 * @author Babol
 */
public class TutorJdbcDao implements TutorDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public boolean registerPatientDevice(int user_id, String imei) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
