package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PatientJdbcDao implements PatientDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;    
}
