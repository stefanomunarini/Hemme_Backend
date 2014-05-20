package com.povodev.hemme.rest;

import com.povodev.hemme.bean.User;
import com.povodev.hemme.jdbcdao.UserJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Controller for User
 */
@Controller
public class UserController {

    @Autowired
    private UserJdbcDao userJdbcDao;
        
    @RequestMapping("/getUser")
    public @ResponseBody User getUser(
            @RequestParam(value="user_id", required=true) int user_id){
        return userJdbcDao.getUser(user_id);
    }
    
    @RequestMapping("/getAuthor")
    public @ResponseBody String getAuthor(
            @RequestParam(value="user_id", required=true) int user_id){
        return userJdbcDao.getAuthor(user_id);
    }
    
    
    @RequestMapping(value="/registration", method = RequestMethod.POST)
    public @ResponseBody User registration(HttpEntity<User> requestEntity){

        User user  = requestEntity.getBody();

        if (userJdbcDao.registration(user)) {
            return userJdbcDao.login(user.getEmail(), user.getPassword(),user.getImei());
        } else {
            return null;
        }
    }
    
     @RequestMapping("/association")
      public @ResponseBody boolean association(
        @RequestParam(value="paziente_id", required=true) int patient_id,
        @RequestParam(value="tutor_id", required=true) int tutor_id){

          return userJdbcDao.associaTutorPaziente(tutor_id, patient_id);
    }

    
    @RequestMapping("/login")
    public @ResponseBody User login(
            @RequestParam(value="email", required=true) String email,
            @RequestParam(value="password", required=true) String password,
            @RequestParam(value="imei", required=true) String imei){
        return userJdbcDao.login(email, password,imei);
    }
    
    @RequestMapping("/passwordRecovery")
    public @ResponseBody String passwordRecovery(
            @RequestParam(value="email", required=true) String email){

        return userJdbcDao.passwordRecovery(email);
    }

    @RequestMapping("/addNewLinkTutorPatient")
    public @ResponseBody boolean addNewLinkTutorPatient(
            @RequestParam(value="old_tutor_id", required=true) int old_tutor_id,
            @RequestParam(value="IMEI", required=true) String IMEI){

        return userJdbcDao.addNewLinkTutorPatient(old_tutor_id,IMEI);
    }
    
    @RequestMapping("/getTutorEmail")
    public @ResponseBody String getTutorEmail(
            @RequestParam(value="patient_id", required=true) int patient_id){

        return userJdbcDao.getTutorEmail(patient_id);
    }

}
