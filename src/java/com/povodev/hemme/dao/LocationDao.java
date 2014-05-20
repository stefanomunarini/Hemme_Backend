/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.LocationCoordinates;

/**
 *
 * @author Stefano
 */
public interface LocationDao {
    
    public boolean setCoordinates (LocationCoordinates values, int user_id);
    public LocationCoordinates getCoordinates (int user_id);
    
    public boolean addNewPatient(int user_id);
    
}
