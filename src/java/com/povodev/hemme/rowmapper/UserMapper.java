/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rowmapper;

import com.povodev.hemme.bean.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gbonadiman.stage
 */
public class UserMapper {
    
    public static User checkUser(List<Map<String, Object>> rows){
        User user = new User();
        if(!rows.isEmpty()){
            for (Map row : rows) {
                user.setEmail((String) row.get("email"));
                user.setImei((String) row.get("imei"));
                user.setName((String) row.get("name"));
                user.setPassword((String) row.get("password"));
                user.setRole((int) row.get("role"));
                user.setSurname((String) row.get("surname"));
            }              
        }else
            user = null;
        
        return user;
    }

}
