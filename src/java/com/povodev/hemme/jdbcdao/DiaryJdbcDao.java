/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Diary;
import com.povodev.hemme.dao.DiaryDao;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author gbonadiman.stage
 */
public class DiaryJdbcDao implements DiaryDao{

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}