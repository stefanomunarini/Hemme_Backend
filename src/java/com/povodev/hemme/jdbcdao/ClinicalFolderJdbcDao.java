package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.dao.ClinicalFolderDao;
import com.povodev.hemme.rowmapper.ClinicalFolderMapper;
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
public class ClinicalFolderJdbcDao implements ClinicalFolderDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    static org.apache.log4j.Logger log = Logger.getLogger(UserJdbcDao.class);

    /**
     * Preso in input restituisco la sua cartella clinica
     * @param user_id
     * @return 
     */
    @Override
    public ArrayList<ClinicalEvent> getClinicalFolder(int user_id) {
        
        List<Map<String, Object>> rows;
        
        String sql = "SELECT * FROM ClinicalFolder NATURAL JOIN ClinicalEvent JOIN User ON author = User.id WHERE user_id = ? ORDER BY date DESC";
	try{
            rows = this.jdbcTemplate.queryForList(sql, user_id);
        }catch (DataAccessException dataAccessException){
            log.error("***DAO :: getClinicalFolder FAIL, DataAccessException occurred, message follows.");
            log.error(dataAccessException);
            throw dataAccessException;
        }
        
        return ClinicalFolderMapper.getClinicalFolderMap(rows);
    }
    
    /**
     * Inserisco relazione utente-cartella clinica
     * @param user_id
     * @param clinicalEvent_id 
     */
    @Override
    public void newClinicalFolder(int user_id, int clinicalEvent_id) {
        try{
            this.jdbcTemplate.update(
                "INSERT INTO ClinicalFolder (user_id, clinicalevent_id) values (?, ?)", 
                new Object[] {user_id, clinicalEvent_id});
        }catch (DataAccessException runtimeException){
            log.error("***Dao::failed to CREATE NEW CLINICALFOLDER, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
    }
    
}
