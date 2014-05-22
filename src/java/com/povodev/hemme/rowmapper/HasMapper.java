package com.povodev.hemme.rowmapper;

import com.povodev.hemme.bean.PatientDoctorItem;
import com.povodev.hemme.bean.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
        String sql = "SELECT * FROM User WHERE id = ?";
        try{
            user = (User) jdbcTemplate.queryForObject(
                sql,
                new Object[] { (user_id)}, 
                new BeanPropertyRowMapper(User.class));
        }catch (DataAccessException dae){throw dae;}    
        return user;
    }
    
    public static ArrayList<PatientDoctorItem> getPatientDoctorList(List<Map<String, Object>> patient_list, List<Map<String, Object>> doctor_list){
        ArrayList<PatientDoctorItem> result = new ArrayList();
        
        for (int i=0; i<patient_list.size(); i++) {
            PatientDoctorItem item = new PatientDoctorItem();
            
            item.setPatient_name( (String) patient_list.get(i).get("name"));
            item.setPatient_surname( (String) patient_list.get(i).get("surname"));
            item.setDoctor_name( (String)doctor_list.get(i).get("name"));
            item.setDoctor_surname((String)doctor_list.get(i).get("surname"));
            
            result.add(item);
        }
        return result;
    }


}
