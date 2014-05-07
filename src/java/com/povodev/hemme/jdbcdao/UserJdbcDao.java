package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.User;
import com.povodev.hemme.dao.UserDao;
import com.povodev.hemme.rowmapper.UserMapper;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
    public boolean registration(User user) {
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
    
    @Override
    public User login(String email,String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,email,password);
            return UserMapper.checkUser(rows);
        }catch (DataAccessException runtimeException){
            log.error("***Dao::check user into database FAIL, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
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

}
