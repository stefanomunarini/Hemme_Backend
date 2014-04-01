/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Access;
import com.povodev.hemme.bean.User;
import com.povodev.hemme.dao.AccessDao;
import com.povodev.hemme.rowmapper.AccessMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author gbonadiman.stage
 */
public class AccessJdbcDao implements AccessDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public boolean registrationAccess(String email, String password, String imei, int role, int status) {

       String query = "INSERT INTO access (email,password,imei,role,status) values (?, ?, ?, ?, ?)";
        try {
            this.jdbcTemplate.update(
                query, 
                new Object[] {email,password,imei,role,status});
        } catch (DataAccessException runtimeException){
            System.err.println("***Dao::fail to CREATE NEW acess, RuntimeException occurred, message follows.");
            throw runtimeException;
        }
        return true;
    }

    
    
    @Override
    public Access getAccess(int access_id) {

        Access access = new Access();
        String sql = "SELECT * FROM access WHERE ID_ACCESS = ?";
       
        try{
            access = (Access) this.jdbcTemplate.queryForObject(
                sql, new Object[] { access_id }, 
                new BeanPropertyRowMapper(Access.class));
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao:: fail to GET ACCESS, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
    
        return access;
    }

    @Override
    public boolean editAccess(Access access) {
        try{
            this.jdbcTemplate.update(
                "update ACCESS set email = ?, password= ?,imei= ?,role= ?,status= ? where id = ?", 
                new Object[] {access.getEmail(),access.getPassword(),access.getImei(),access.getRole(),access.getStatus()});
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao:: edit Access FAIL, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
        return true;
    }

    
    
    @Override
    public boolean deleteAccess(int access_id) {
        
        String deleteStatement = "DELETE FROM ACCESS WHERE access_id = ?";

        try{
            this.jdbcTemplate.update(deleteStatement, access_id);
        }catch (DataAccessException runtimeException){
            System.err.println("***NagiosHostDao::DELETE ACCESS FAILED, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
        return true;
    }

    @Override
    public ArrayList<Access> getDevicesList(String email, String password) {
    
        String sql = "SELECT * FROM access WHERE email = ? AND password = ?";
        
        try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,email,password);
            return AccessMapper.getDevices(rows);
        }catch (DataAccessException runtimeException){
            System.err.println("***Dao::create diary FAIL, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }    
    }

    
}
