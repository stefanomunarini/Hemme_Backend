package com.povodev.hemme.rowmapper;

import com.povodev.hemme.bean.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe mapper che consente di gestire result multipli di query SQL
 * @author Babol
 */
public class UserMapper {
    
    /**
     * Controllo che esista l'utente
     * @param rows
     * @return 
     */
    public static User checkUser(List<Map<String, Object>> rows){
        User user = new User();
        if(!rows.isEmpty()){
            for (Map row : rows) {
                user.setId((int) row.get("id"));
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

    /**
     * Costruisco la lista di pazienti
     * @param rows
     * @return 
     */
    public static ArrayList<Integer> listPatient(List<Map<String, Object>> rows){
        ArrayList<Integer> pazienti = new ArrayList();
        for (Map row : rows) {
            int id_paz = (Integer) row.get("patient_id");
            pazienti.add(id_paz);
        }              
        return pazienti;
    }
}

