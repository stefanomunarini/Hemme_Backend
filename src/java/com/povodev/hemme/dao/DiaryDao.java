/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Diary;

/**
 *
 * @author gbonadiman.stage
 */
public interface DiaryDao{
    
    public Diary getDiary(int user_id);
    public void newDiary(int user_id);
    
}
