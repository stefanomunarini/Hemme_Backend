


package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Document;
import java.util.ArrayList;

public interface DocumentDao {
    
    public Document getDocument(int document_id);
    public void newDocument(Document document);
    public void editDocument(Document document);
    public void deleteDocument(int document_id);
    public ArrayList<Document> getDiary(int user_id);
    
}
