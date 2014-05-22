package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.User;
import com.povodev.hemme.dao.HasTpDao;
import com.povodev.hemme.rowmapper.HasMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Classe JdbcDao che implementa il corpo di tutte le funzioni dichiarate nell'interfaccia
 * @author Babol
 */
public class HasTpJdbcDao implements HasTpDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static org.apache.log4j.Logger log = Logger.getLogger(UserJdbcDao.class);

   /**
    * Restituisco la lista di pazienti di un tutor
    * @param tutor_id
    * @return 
    */
    @Override
    public ArrayList<User> patientList(int tutor_id) {

        String sql = "SELECT * FROM TP WHERE tutor_id = ?";
	try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,tutor_id);
            return HasMapper.getListPatient(rows, this.jdbcTemplate);
        }catch (DataAccessException runtimeException){
            log.error("***Dao::create list of patient HasTp, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
    }
}
