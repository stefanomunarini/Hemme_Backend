package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.User;
import com.povodev.hemme.dao.UserDao;
import com.povodev.hemme.rowmapper.UserMapper;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


/**
 * Classe JdbcDao che implementa il corpo di tutte le funzioni dichiarate nell'interfaccia
 * @author Povodev
 */
public class UserJdbcDao implements UserDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private LocationJdbcDao locationJdbcDao;
    
    static org.apache.log4j.Logger log = Logger.getLogger(UserJdbcDao.class);
    
    /**
     * Preso in input l'id restituisce l'utente associato
     * @param user_id
     * @return 
     */
    @Override
    public User getUser(int user_id) {
        User user;
        String sql = "SELECT * FROM User WHERE id = ?";
        try{
            user = (User) this.jdbcTemplate.queryForObject(
                sql,
                new Object[] { (user_id)}, 
                new BeanPropertyRowMapper(User.class));
        }catch (DataAccessException dae){throw dae;}    
        return user;
    }


    /**
     * Funzione di registrazione 
     * @param user
     * @return 
     */
    @Override
    public boolean registration(final User user) {
        
        if(user.getRole() == 2){
            KeyHolder keyHolder = new GeneratedKeyHolder();
            final String query = "INSERT INTO User (imei, name, surname, password, email, role) values (?, ?, ?, ?, ?, ?)";
            try{
                this.jdbcTemplate.update(new PreparedStatementCreator(){
                    @Override
                    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{ 
                        PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, user.getImei());
                        preparedStatement.setString(2, user.getName());
                        preparedStatement.setString(3, user.getSurname());
                        preparedStatement.setString(4, user.getPassword());
                        preparedStatement.setString(5, user.getEmail());
                        preparedStatement.setInt(6, 2);
                        
                        return preparedStatement; 
                    }
                }, keyHolder);
                int user_id = keyHolder.getKey().intValue();
                
                locationJdbcDao.addNewPatient(user_id);
                
                return true;
            }catch (DataAccessException runtimeException){
                log.error("***Dao::failed to CREATE NEW user, RuntimeException occurred, message follows.");
                log.error(runtimeException);
                throw runtimeException;
            }
        }
        else{
            String query = "INSERT INTO User (imei, name, surname, password, email, role) values (?, ?, ?, ?, ?, ?)";
            try {
                this.jdbcTemplate.update(
                    query, 
                    new Object[] {user.getImei(), user.getName(), user.getSurname(), user.getPassword(), user.getEmail(), user.getRole()});
            } catch (DataAccessException dae){
                log.error("***Dao::failed to CREATE NEW user, DataAccessException occurred, message follows.");
                log.error(dae.getMessage());
                throw dae;
            }
            return true;
        }
    }
    
    /**
     * Funzione di login
     * @param email
     * @param password
     * @param imei
     * @return 
     */
    @Override
    public User login(String email,String password,String imei) {
                
        String sql = "SELECT * FROM User WHERE email = ? AND password = ?";
        try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,email,password);

            User user = UserMapper.checkUser(rows);

            //l'utente non esiste
            if(user ==  null)
                return null;

            //tutor logga con il proprio dispositivo
            if(user.getImei().equals(imei))
                return user;
            
            //tutor logga con dispositivo del paziente
            else {
                User pazienteTmp = new User();
                pazienteTmp.setImei("tmp");
                pazienteTmp.setId(user.getId());
                pazienteTmp.setEmail(user.getEmail());
                pazienteTmp.setName(user.getName());
                pazienteTmp.setPassword(user.getPassword());
                pazienteTmp.setRole(user.getRole());
                pazienteTmp.setSurname(user.getSurname());
                
                return pazienteTmp;
            }
            
        }catch (DataAccessException runtimeException){
            log.error("***Dao::check user into database FAIL, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
    }

    /**
     * Ricerca di un utente all'interno del database attraverso il suo IMEI
     * @param jdbcTemplate
     * @param imei
     * @return 
     */
    private User catchUserFromImei(JdbcTemplate jdbcTemplate,String imei){
        User user;
        String sql = "SELECT * FROM User WHERE imei = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,imei);
        user = UserMapper.checkUser(rows);
        return user;  
    }
    
    /**
     * Ricerca dell'autore per id
     * @param user_id
     * @return 
     */
    @Override
    public String getAuthor(int user_id) {
        User user = getUser(user_id);
        return user.getName() + " " + user.getSurname();
    }

    /**
     * Presi in input i rispettivi ID associo il un tutore a un paziente
     * @param tutore
     * @param paziente
     * @return 
     */
    @Override
    public boolean associaTutorPaziente(int tutore, int paziente){
        String query = "INSERT INTO TP (tutor_id,patient_id) VALUES (?,?)";
        this.jdbcTemplate.update(
            query, 
            new Object[] {tutore,paziente});
        return true;
    }
    
    
    /**
     * Seleziono la password associata all'email passata come parametro
     * @param email
     * @return 
     */
    @Override
    public String passwordRecovery(String email) {        
        String password;
        String sql = "SELECT password FROM User WHERE email = '" + email + "'";
        try{
            password = this.jdbcTemplate.queryForObject(
                    sql,
                    String.class);
        } catch (DataAccessException dae){
            log.error("***Dao::passwordrecovery FAIL, RuntimeException occurred, message follows.");
            log.error(dae);
            throw dae;
        }
        return password;
    }

    /**
     * Quando associo un nuovo dispositivo copio i collegamenti gia presenti nel database
     * Questa funzione si appoggia alla successiva passando la lista del tutore/dottore in questione
     * @param old_tutor_id
     * @param IMEI
     * @return 
     */
    @Override
    public boolean addNewLinkTutorPatient(int old_tutor_id, String IMEI) {
        
        User usr = catchUserFromImei(jdbcTemplate, IMEI);
        int new_tutor_id = usr.getId();
        
        String sql = "SELECT * FROM TP WHERE tutor_id = ?";
	try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,old_tutor_id);
            ArrayList<Integer> pazienti_tutor =  UserMapper.listPatient(rows);
            
            Iterator itr = pazienti_tutor.iterator();
            while(itr.hasNext()){
                int id_paz = (int) itr.next();
                insertConnection(id_paz,new_tutor_id,jdbcTemplate);
            }
            
        }catch (DataAccessException runtimeException){
            log.error("***Dao::create list of patient addNewLinkTutorPatient, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
        return true;
    }
    
    /**
     * Funzione collegata alla precedente. Inserisce la relazione tutor-paziente
     * @param paziente_id
     * @param old_tutor_id
     * @param jdbcTemplate
     * @return 
     */
    public boolean insertConnection(int paziente_id,int old_tutor_id, JdbcTemplate jdbcTemplate){
    
        String query = "INSERT INTO TP (tutor_id,patient_id) values (?, ?)";
        jdbcTemplate.update(
            query, 
            new Object[] {old_tutor_id,paziente_id});
        return true;
    }

    /**
     * Recupero email di un tutore
     * @param patient_id
     * @return 
     */
    @Override
    public String getTutorEmail(int patient_id) {
        String query = "SELECT tutor_id FROM TP where patient_id = " + patient_id;
        int tutor_id;
        try{
            tutor_id = this.jdbcTemplate.queryForObject(
                    query,
                    Integer.class);
        } catch (DataAccessException dae){
            log.error("***Dao::passwordrecovery FAIL, RuntimeException occurred, message follows.");
            log.error(dae);
            throw dae;
        }
        User tutor = getUser(tutor_id);
        return tutor.getEmail();
    }
    

}
