package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.PatientDoctorItem;
import com.povodev.hemme.bean.User;
import com.povodev.hemme.dao.HasDpDao;
import com.povodev.hemme.rowmapper.HasMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class HasDpJdbcDao implements HasDpDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    static org.apache.log4j.Logger log = Logger.getLogger(UserJdbcDao.class);

    /**
     * Inserisco una nuova connessione tra medico e paziente
     * @param patient_id
     * @param doctor_id
     * @return 
     */
    @Override
    public boolean newHasDp(int patient_id, int doctor_id) {

        String query = "INSERT INTO DP (patient_id, doctor_id) VALUES (?,?)";
        try {
            this.jdbcTemplate.update(
                query, 
                new Object[] {patient_id, doctor_id});
        } catch (DataAccessException dae){
            log.error("catch exception");
            throw dae;
        }
        return true;
    }
    
    /**
     * Restituisco i pazienti associati a un dottore
     * @param doctor_id
     * @return 
     */
    @Override
    public ArrayList<User> patientList(int doctor_id) {

        String sql = "SELECT * FROM DP WHERE doctor_id = ?";
	try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,doctor_id);
            return HasMapper.getListPatient(rows, this.jdbcTemplate);
        }catch (DataAccessException runtimeException){
            log.error("***Dao::create list of patient HasTp, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
    }

    /**
     * Creo e restituisco due liste. 
     * Contengono le relazioni medico-paziente associate ai pazienti di un singolo tutor
     * @param tutor_id
     * @return 
     */
    @Override
    public ArrayList<PatientDoctorItem> getPatientDoctorList(int tutor_id) {
        String query1 = "SELECT * FROM DP JOIN `User` ON patient_id = `User`.id WHERE patient_id IN (SELECT patient_id FROM TP WHERE tutor_id = " + tutor_id + ");";
        String query2 = "SELECT * FROM DP JOIN `User` ON doctor_id = `User`.id WHERE patient_id IN (SELECT patient_id FROM TP WHERE tutor_id = " + tutor_id + ");";
	try{
            List patient_list = this.jdbcTemplate.queryForList(query1);
            List doctor_list = this.jdbcTemplate.queryForList(query2);
            
            return HasMapper.getPatientDoctorList(patient_list, doctor_list);
        }catch (DataAccessException runtimeException){
            log.error("***Dao::create list of patient HasTp, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
    }

}
