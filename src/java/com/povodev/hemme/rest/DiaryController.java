
package com.povodev.hemme.rest;

import com.povodev.hemme.jdbcdao.DiaryJdbcDao;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Classe controller per filtrare correttamente le richieste in input
 * @author Babol
 */
@Controller
public class DiaryController {

    @Autowired
    private DiaryJdbcDao diaryJdcDao;
    
    @Autowired
    ServletRequest sr;
    
}
