package com.povodev.hemme.rowmapper;

import com.povodev.hemme.bean.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Classe mapper che consente di gestire result multipli di query SQL
 * @author Babol
 */
public class DoctorMapper {
    
    /**
     * Crea la lista di pazienti associati a un dottore
     * @param rows
     * @param jdbcTemplate
     * @return 
     */
    public static ArrayList<User> getListDoctor(List<Map<String, Object>> rows,JdbcTemplate jdbcTemplate){
        ArrayList<User> dottori = new ArrayList();
        for (Map row : rows) {
            int id_medico = (Integer) row.get("id");
            User paziente = getUser(id_medico,jdbcTemplate);
            dottori.add(paziente);
        }              
        return dottori;
    }
        
        
    /**
     * Funzione di supporto per creare un utente dal database
     * @param user_id
     * @param jdbcTemplate
     * @return 
     */
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
            
}
