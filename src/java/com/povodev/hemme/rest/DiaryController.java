
package com.povodev.hemme.rest;

import com.povodev.hemme.jdbcdao.DiaryJdbcDao;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DiaryController {

    @Autowired
    private DiaryJdbcDao diaryJdcDao;
    
    @Autowired
    ServletRequest sr;
    
}
