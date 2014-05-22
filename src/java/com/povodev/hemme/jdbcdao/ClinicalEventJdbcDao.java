package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.dao.ClinicalEventDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * Classe JdbcDao che implementa il corpo di tutte le funzioni dichiarate nell'interfaccia
 * @author Babol
 */
public class ClinicalEventJdbcDao implements ClinicalEventDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ClinicalFolderJdbcDao clinicalFolderJdbcDao;
    
    static org.apache.log4j.Logger log = Logger.getLogger(UserJdbcDao.class);

    /**
     * Restituisco il singolo evento clinico
     * @param clinicalEvent_id
     * @return 
     */
    @Override
    public ClinicalEvent getClinicalEvent(int clinicalEvent_id) {
        
        ClinicalEvent clinicalEvent;
        String sql = "SELECT * FROM ClinicalEvent WHERE id = ?";
       
        try{
            clinicalEvent = (ClinicalEvent) this.jdbcTemplate.queryForObject(
                sql, new Object[] { (clinicalEvent_id)}, 
                new BeanPropertyRowMapper(ClinicalEvent.class));
        }catch (DataAccessException runtimeException){
            log.error("***Dao:: fail to GET DOCUMENT, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }    
        return clinicalEvent;
    }

    /**
     * Inserimento nuovo evento clinico
     * @param clinicalEvent
     * @param user_id
     * @return 
     */
    @Override
    public boolean newClinicalEvent(final ClinicalEvent clinicalEvent, int user_id) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String query = "INSERT INTO ClinicalEvent (author,therapy,note) values (?, ?, ?)";
        try {
            jdbcTemplate.update(new PreparedStatementCreator(){
                @Override
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{ 
                    PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(1,clinicalEvent.getAuthor()); 
                    preparedStatement.setString(2, clinicalEvent.getTherapy());
                    preparedStatement.setString(3, clinicalEvent.getNote());

                    return preparedStatement; 
                }
            }, keyHolder); 
        } catch (DataAccessException runtimeException){
            log.error("***Dao::fail to CREATE NEW clinicalevent, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
        
        int clinicalEvent_id = keyHolder.getKey().intValue();
        clinicalFolderJdbcDao.newClinicalFolder(user_id,clinicalEvent_id);
        
        return true;
    }
   
    /**
     * Modifica del singolo evento clinico
     * @param clinicalEvent
     * @return 
     */
    @Override
    public boolean modifyClinicalEvent(final ClinicalEvent clinicalEvent) {
        final String query = "UPDATE ClinicalEvent SET author=?, therapy=?, note=? WHERE id = ?";
        try{
            this.jdbcTemplate.update(new PreparedStatementCreator(){
                @Override
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{ 
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setInt(1, clinicalEvent.getAuthor()); 
                    preparedStatement.setString(2, clinicalEvent.getTherapy());
                    preparedStatement.setString(3, clinicalEvent.getNote());
                    preparedStatement.setInt(4, clinicalEvent.getId());
                    
                    return preparedStatement; 
                }
            });
                
        } catch (DataAccessException runtimeException){
            log.error("***Dao::failed to UPDATE CLINICALEVENT, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
        return true;
    }
}
