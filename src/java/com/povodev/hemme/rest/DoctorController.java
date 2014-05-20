package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Doctor;
import com.povodev.hemme.bean.User;
import com.povodev.hemme.jdbcdao.DoctorJdbcDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Controller for Doctor
 */
@Controller
public class DoctorController {
    
    @Autowired
    private DoctorJdbcDao doctorJdbcDao;
    
    @RequestMapping("/getDoctor")
    public @ResponseBody Doctor getDoctor(
        @RequestParam(value="doctor_id", required=true) int doctor_id) {
        return doctorJdbcDao.getDoctor(doctor_id);
    }
    
    @RequestMapping("/newDoctor")
    public @ResponseBody void newDoctor(
        @RequestParam(value="user_id", required=true) int user_id) {

        doctorJdbcDao.newDoctor(user_id);
    }
    
    
    @RequestMapping("/getListDoctor")
    public @ResponseBody ArrayList<User> getListDoctor() {
        return doctorJdbcDao.getAllDoctors();
    }
    
    
}
