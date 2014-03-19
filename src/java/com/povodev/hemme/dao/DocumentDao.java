


package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Document;

public interface DocumentDao {
    
    public Document getDocument(int user_id);
    public void newDocument(int user_id);

}
