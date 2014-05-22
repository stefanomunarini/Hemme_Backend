package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Access;
import com.povodev.hemme.dao.AccessDao;
import com.povodev.hemme.rowmapper.AccessMapper;
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
 * @author Babol
 */
public class AccessJdbcDao implements AccessDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    static org.apache.log4j.Logger log = Logger.getLogger(UserJdbcDao.class);

    /**
     * Inserimento associazione dispositivo
     * @param email
     * @param password
     * @param imei
     * @param role
     * @param status
     * @return 
     */
    @Override
    public boolean registrationAccess(String email, String password, String imei, int role, int status) {

       String query = "INSERT INTO Access (email,password,imei,role,status) values (?, ?, ?, ?, ?)";
        try {
            this.jdbcTemplate.update(
                query, 
                new Object[] {email,password,imei,role,status});
        } catch (DataAccessException runtimeException){
            log.error("***Dao::fail to CREATE NEW acess, RuntimeException occurred, message follows.");
            throw runtimeException;
        }
        return true;
    }

    
    /**
     * Seleziona singolo accesso
     * @param access_id
     * @return 
     */
    @Override
    public Access getAccess(int access_id) {

        Access access = new Access();
        String sql = "SELECT * FROM Access WHERE ID_ACCESS = ?";
       
        try{
            access = (Access) this.jdbcTemplate.queryForObject(
                sql, new Object[] { access_id }, 
                new BeanPropertyRowMapper(Access.class));
        }catch (DataAccessException runtimeException){
            log.error("***Dao:: fail to GET ACCESS, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }    
    
        return access;
    }

    /**
     * modifica di accesso
     * @param access
     * @return 
     */
    @Override
    public boolean editAccess(Access access) {
        try{
            this.jdbcTemplate.update(
                "update Access set email = ?, password= ?,imei= ?,role= ?,status= ? where id = ?", 
                new Object[] {access.getEmail(),access.getPassword(),access.getImei(),access.getRole(),access.getStatus()});
        }catch (DataAccessException runtimeException){
            log.error("***Dao:: edit Access FAIL, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }    
        return true;
    }

    
    /**
     * elimina un record di accesso
     * @param access_id
     * @return 
     */
    @Override
    public boolean deleteAccess(int access_id) {
        
        String deleteStatement = "DELETE FROM Access WHERE access_id = ?";

        try{
            this.jdbcTemplate.update(deleteStatement, access_id);
        }catch (DataAccessException runtimeException){
            log.error("***NagiosHostDao::DELETE ACCESS FAILED, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }    
        return true;
    }

    /**
     * Ritorna la lista di device
     * @param email
     * @param password
     * @return 
     */
    @Override
    public ArrayList<Access> getDevicesList(String email, String password) {
    
        String sql = "SELECT * FROM Access WHERE email = ? AND password = ?";
        
        try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,email,password);
            return AccessMapper.getDevices(rows);
        }catch (DataAccessException runtimeException){
            log.error("***Dao::create diary FAIL, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }    
    }

    
}
