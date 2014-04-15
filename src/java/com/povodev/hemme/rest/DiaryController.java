
package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Diary;
import com.povodev.hemme.bean.Document;
import com.povodev.hemme.jdbcdao.DiaryJdbcDao;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author gbonadiman.stage
 */
@Controller
public class DiaryController {

    @Autowired
    private DiaryJdbcDao diaryJdcDao;
    
    @Autowired
    ServletRequest sr;
    
}
