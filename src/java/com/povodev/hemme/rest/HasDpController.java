package com.povodev.hemme.rest;

import com.povodev.hemme.bean.PatientDoctorItem;
import com.povodev.hemme.bean.User;
import com.povodev.hemme.jdbcdao.HasDpJdbcDao;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HasDpController {
    
    @Autowired
    private HasDpJdbcDao hasDpJdbcDao;
    
    @RequestMapping("/newHasDp")
    public @ResponseBody boolean newHasDp(
            @RequestParam(value="patient_id", required=true) int patient_id,
            @RequestParam(value="doctor_id", required=true) int doctor_id) {        
        return hasDpJdbcDao.newHasDp(patient_id, doctor_id);
    }
    
    @RequestMapping("/patientListDoctor")
    public @ResponseBody ArrayList<User> listHasTp(
            @RequestParam(value="doctor_id", required=true) int doctor_id){
        return hasDpJdbcDao.patientList(doctor_id);
    }
    
    @RequestMapping("/getPatientDoctorList")
    public @ResponseBody ArrayList<PatientDoctorItem> getPatientDoctorList(
            @RequestParam(value="tutor_id", required=true) int tutor_id){
        return hasDpJdbcDao.getPatientDoctorList(tutor_id);
    }
    
    
}
