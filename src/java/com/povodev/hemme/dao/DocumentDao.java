
package com.povodev.hemme.dao;

import com.povodev.hemme.bean.Document;
import java.io.File;
import java.util.ArrayList;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentDao {
    
    public Document getDocument(int document_id);
    public boolean insertDocument(String file,String note);
    public boolean editDocument(Document document);
    public boolean deleteDocument(int document_id);
    public ArrayList<Document> getDiary(int user_id);
    public boolean uploadDocument(File file);

    
}
