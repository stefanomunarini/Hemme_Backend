
package com.povodev.hemme.rest;

import com.povodev.hemme.bean.Diary;
import com.povodev.hemme.jdbcdao.DiaryJdbcDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gbonadiman.stage
 */
@Controller
public class DiaryController {

    
    @RequestMapping("/newDiary")
    public @ResponseBody Diary newDiary(
            @RequestParam(value="user_id", required=true) int user_id) {
        DiaryJdbcDao diary = new DiaryJdbcDao();
        return diary.getDiary(user_id);
    }
    
    
}
