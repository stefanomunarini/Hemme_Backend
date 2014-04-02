
package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Document;
import java.util.ArrayList;

public interface DocumentDao {
    
    public Document getDocument(int document_id);
    public boolean insertDocument(int user_id,Document document);
    public boolean editDocument(Document document);
    public boolean deleteDocument(int document_id);
    public ArrayList<Document> getDiary(int user_id);
    
}
