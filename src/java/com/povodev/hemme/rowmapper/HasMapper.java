/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rowmapper;

import com.povodev.hemme.bean.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author gbonadiman.stage
 */
public class HasMapper {
 
    
    public static ArrayList<User> getListPatient(List<Map<String, Object>> rows,JdbcTemplate jdbcTemplate){
        ArrayList<User> pazienti = new ArrayList();
        for (Map row : rows) {
            int id_paz = (Integer) row.get("patient_id");
            User paziente = getUser(id_paz,jdbcTemplate);
            pazienti.add(paziente);
        }              
        return pazienti;
    }

    
    public static User getUser(int user_id,JdbcTemplate jdbcTemplate) {
        User user;
        String sql = "SELECT * FROM user WHERE id = ?";
        try{
            user = (User) jdbcTemplate.queryForObject(
                sql,
                new Object[] { (user_id)}, 
                new BeanPropertyRowMapper(User.class));
        }catch (DataAccessException dae){throw dae;}    
        return user;
    }


}
