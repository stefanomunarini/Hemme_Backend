package com.povodev.hemme.dao;

import com.povodev.hemme.bean.PatientDoctorItem;
import com.povodev.hemme.bean.User;
import java.util.ArrayList;

/**
 * Interfaccia Dao contenente la dichiarazione dei metodi
 * @author Povodev
 */
public interface HasDpDao {

    public boolean newHasDp(int patient_id, int doctor_id);
    public ArrayList<User> patientList(int doctor_id);    
    public ArrayList<PatientDoctorItem> getPatientDoctorList(int tutor_id);

}
