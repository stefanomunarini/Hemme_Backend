package com.povodev.hemme.dao;

import com.povodev.hemme.bean.LocationCoordinates;

/**
 * Interfaccia Dao contenente la dichiarazione dei metodi
 * @author Povodev
 */public interface LocationDao {
    
    public boolean setCoordinates (LocationCoordinates values, int user_id);
    public LocationCoordinates getCoordinates (int user_id);    
    public boolean addNewPatient(int user_id);
    
}
