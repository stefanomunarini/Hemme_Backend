/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.ClinicalEvent;
import com.povodev.hemme.bean.LocationCoordinates;
import com.povodev.hemme.dao.LocationDao;
import static com.povodev.hemme.jdbcdao.ClinicalEventJdbcDao.log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 *
 * @author Stefano
 */
public class LocationJdbcDao implements LocationDao {
    
    private final static String LATITUDE = "Latitude";
    private final static String LONGITUDE = "Latitude";
    private final static String RADIUS = "Latitude";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean setCoordinates(final LocationCoordinates values, final int user_id) {
        final String query = "UPDATE Location SET latitude = ?, longitude = ?, radius = ? WHERE user_id = ?";
        try {
            jdbcTemplate.update(new PreparedStatementCreator(){
                @Override
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{ 
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setDouble(1, values.getLatitude());
                    preparedStatement.setDouble(2, values.getLongitude());
                    preparedStatement.setInt(3, values.getRadius());
                    preparedStatement.setInt(4, user_id);

                    return preparedStatement; 
                }
            }); 
        } catch (DataAccessException runtimeException){
            System.err.println("***Dao::fail to CREATE NEW clinicalevent, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }
        
        return true;
    }

    @Override
    public LocationCoordinates getCoordinates(int user_id) {
                
        LocationCoordinates locationCoordinates;
        String sql = "SELECT * FROM Location WHERE user_id = ?";
	try{
            locationCoordinates = (LocationCoordinates) this.jdbcTemplate.queryForObject(
                sql, new Object[] { (user_id)}, 
                new BeanPropertyRowMapper(LocationCoordinates.class));
        }catch (DataAccessException runtimeException){
            log.error("***Dao:: fail to GET COORDINATES, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
        
        System.err.println("Radius: "+ locationCoordinates.getRadius());        
        System.err.println("Lat " + locationCoordinates.getLatitude()+ "    " + locationCoordinates.getLongitude());
        
        return locationCoordinates;
    }

    @Override
    public boolean addNewPatient(final int user_id) {
        final String query = "INSERT INTO Location (user_id, latitude, longitude, radius) VALUES (?,0,0,0)";
        try {
            jdbcTemplate.update(new PreparedStatementCreator(){
                @Override
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{ 
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setInt(1,user_id);

                    return preparedStatement; 
                }
            }); 
        } catch (DataAccessException runtimeException){
            System.err.println("***Dao::fail to CREATE NEW patient in location table, RuntimeException occurred, message follows.");
            System.err.println(runtimeException);
            throw runtimeException;
        }
        return true;
    }
    
}
