package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Doctor;
import com.povodev.hemme.bean.User;
import com.povodev.hemme.dao.DoctorDao;
import com.povodev.hemme.rowmapper.DoctorMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Classe JdbcDao che implementa il corpo di tutte le funzioni dichiarate nell'interfaccia
 * @author Povodev
 */
public class DoctorJdbcDao implements DoctorDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
     
    static org.apache.log4j.Logger log = Logger.getLogger(UserJdbcDao.class);

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Restiutisce un dottore
     * @param doctor_id
     * @return 
     */
    @Override
    public Doctor getDoctor(int doctor_id) {

        Doctor doctor = null;
        String sql = "SELECT * FROM Doctor WHERE user_id = ?";
        try{
            doctor = (Doctor) this.jdbcTemplate.queryForObject(
                sql, new Object[] { doctor_id }, 
                new BeanPropertyRowMapper(Doctor.class));
        }catch (DataAccessException runtimeException){
            log.error("***Dao:: fail to GET DOCUMENT, RuntimeException occurred, message follows.");
            log.equals(runtimeException);
            throw runtimeException;
        }    
        return doctor;
    }

    @Override
    public void newDoctor(int user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Restituisce un arrayList di dottori
     * @return 
     */
    @Override
    public ArrayList<User> getAllDoctors() {

        String sql = "SELECT * FROM User WHERE role = ?";
	try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,1);
            return DoctorMapper.getListDoctor(rows,this.jdbcTemplate);
        }catch (DataAccessException runtimeException){
            log.error("***Dao::create diary FAIL, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
}
    
}
