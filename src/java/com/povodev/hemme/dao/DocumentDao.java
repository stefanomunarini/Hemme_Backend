


package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Document;

public interface DocumentDao {
    
    public Document getDocument(int document_id);
    public void newDocument(int user_id);
    public void editDocument(Document document);
    public void deleteDocument(int document_id);
    
}
