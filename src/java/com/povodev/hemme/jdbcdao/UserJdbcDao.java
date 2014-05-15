package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.User;
import com.povodev.hemme.dao.UserDao;
import com.povodev.hemme.rowmapper.HasMapper;
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
 * JdbcDao implementation for User
 */
public class UserJdbcDao implements UserDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    static org.apache.log4j.Logger log = Logger.getLogger(UserJdbcDao.class);
    
    @Override
    public User getUser(int user_id) {
        User user;
        String sql = "SELECT * FROM user WHERE id = ?";
        try{
            user = (User) this.jdbcTemplate.queryForObject(
                sql,
                new Object[] { (user_id)}, 
                new BeanPropertyRowMapper(User.class));
        }catch (DataAccessException dae){throw dae;}    
        return user;
    }

    
    @Override
    public boolean registration(final User user) {
        
        if(user.getRole() == 2){
            final String query = "INSERT INTO user (imei, name, surname, password, email, role) values (?, ?, ?, ?, ?, ?)";
            try{
                this.jdbcTemplate.update(new PreparedStatementCreator(){
                    @Override
                    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{ 
                        PreparedStatement preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setString(1, user.getImei()); 
                        preparedStatement.setString(2, user.getName()); 
                        preparedStatement.setString(3, user.getSurname());
                        preparedStatement.setString(4, user.getPassword());
                        preparedStatement.setString(5, user.getEmail());
                        preparedStatement.setInt(6, 2); 
                        return preparedStatement; 
                    }
                });
                return true;
            }catch (DataAccessException runtimeException){
                System.err.println("***Dao::failed to UPDATE CLINICALEVENT, RuntimeException occurred, message follows.");
                System.err.println(runtimeException);
                throw runtimeException;
            }
        }
        else{
            String query = "INSERT INTO user (imei, name, surname, password, email, role) values (?, ?, ?, ?, ?, ?)";
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
    
    @Override
    public User login(String email,String password,String imei) {
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,email,password);
            //return UserMapper.checkUser(rows);
            JdbcTemplate jdbcTemplate2 = this.jdbcTemplate;
            User user = UserMapper.checkUser(rows);

            //l'utente non esiste
            if(user ==  null)
                return null;

            //tutor logga con il proprio dispositivo
            if(user.getImei().equals(imei))
                return user;
            
            //tutor logga con dispositivo del paziente
            else{
                User pazienteTmp = new User();
                pazienteTmp.setImei("tmp");
                pazienteTmp.setId(user.getId());
                pazienteTmp.setEmail(user.getEmail());
                pazienteTmp.setName(user.getName());
                pazienteTmp.setPassword(user.getPassword());
                pazienteTmp.setRole(user.getRole());
                pazienteTmp.setSurname(user.getSurname());
/*
                User tutore = user;

                String query = "INSERT INTO user (imei, name, surname, role) values (?, ?, ?, ?)";
                try {
                    jdbcTemplate2.update(
                        query, 
                        new Object[] {imei,"tmp","tmp",2});
                } catch (DataAccessException dae){
                    log.error("***Dao::failed to CREATE NEW user form login function, DataAccessException occurred, message follows.");
                    log.error(dae.getMessage());
                    throw dae;
                }
                
                User paziente = catchUserFromImei(jdbcTemplate, imei);
                
                associaTutorPaziente(tutore, paziente.getId(),this.jdbcTemplate);
  */      
                
                return pazienteTmp;
                /*                User paziente = user;
                user = catchUserFromImei(this.jdbcTemplate,imei);
                User tutore = user;
                associaTutorPaziente(tutore,paziente,this.jdbcTemplate);
                return paziente;
 */           }
            
        }catch (DataAccessException runtimeException){
            log.error("***Dao::check user into database FAIL, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
    }

    private User catchUserFromImei(JdbcTemplate jdbcTemplate,String imei){
        User user;
        String sql = "SELECT * FROM user WHERE imei = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,imei);
        user = UserMapper.checkUser(rows);
        return user;  
    }
    
    private boolean associaTutorPaziente(User tutore, int paziente, JdbcTemplate jdbcTemplate){
        
        System.err.println("associo  tutor "+ tutore.getId() + " paziente  = " + paziente);
        
        String query = "INSERT INTO tp (tutor_id,patient_id) values (?, ?)";
        jdbcTemplate.update(
            query, 
            new Object[] {tutore.getId(),paziente});
        return true;
    }
    
    
    @Override
    public String getAuthor(int user_id) {
        User user = getUser(user_id);
        return user.getName() + " " + user.getSurname();
    }

    
    @Override
    public String passwordRecovery(String email) {        
        String password;
        String sql = "SELECT password FROM user WHERE email = '" + email + "'";
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

    @Override
    public boolean addNewLinkTutorPatient(int old_tutor_id,String IMEI) {

        
        User usr = catchUserFromImei(jdbcTemplate, IMEI);
        int new_tutor_id = usr.getId();
        
        String sql = "SELECT * FROM tp WHERE tutor_id = ?";
	try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,old_tutor_id);
            ArrayList<Integer> pazienti_tutor =  UserMapper.listPatient(rows);
            
            Iterator itr = pazienti_tutor.iterator();
            while(itr.hasNext()){
                int id_paz = (int) itr.next();
                insertConnection(id_paz,new_tutor_id,jdbcTemplate);
            }
            
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao::create list of patient addNewLinkTutorPatient, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }
        return true;
    }
    
    public boolean insertConnection(int paziente_id,int old_tutor_id, JdbcTemplate jdbcTemplate){
    
        System.err.println("INSERT CONNECTION " + paziente_id);
        String query = "INSERT INTO tp (tutor_id,patient_id) values (?, ?)";
        jdbcTemplate.update(
            query, 
            new Object[] {old_tutor_id,paziente_id});
        return true;
    }
    

}
