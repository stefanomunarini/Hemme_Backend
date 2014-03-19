/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Tutor;
import com.povodev.hemme.jdbcdao.TutorJdbcDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gbonadiman.stage
 */
public class TutorController {
        @RequestMapping("/getTutor")
    public @ResponseBody Tutor getTutor(
        @RequestParam(value="user_id", required=true) int user_id) {
        TutorJdbcDao tutor = new TutorJdbcDao();
        return tutor.getTutor(user_id);
    }
    
    @RequestMapping("/newTutor")
    public @ResponseBody Tutor newTutor(
            @RequestParam(value="user_id", required=true) int user_id) {
        TutorJdbcDao tutor = new TutorJdbcDao();
        return tutor.getTutor(user_id);
    }
}
