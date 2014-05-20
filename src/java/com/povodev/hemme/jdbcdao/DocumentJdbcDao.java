
package com.povodev.hemme.jdbcdao;

import com.povodev.hemme.bean.Document;
import com.povodev.hemme.dao.DocumentDao;
import com.povodev.hemme.rowmapper.DocumentMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.apache.commons.logging. Log;
import org.apache.commons.logging. LogFactory;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.web.multipart.MultipartFile;


public class DocumentJdbcDao implements DocumentDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;
    
    @Autowired
    ServletRequest sr;
    
    static Log log = LogFactory.getLog(DocumentJdbcDao.class.getName());
            
    /**
     * Return document from its Id
     * @param document_id
     * @return 
     */
    @Override
    public Document getDocument(int document_id) {        
        Document document = new Document();
        String sql = "SELECT * FROM Document WHERE ID = ?";
        try{
            document = (Document) this.jdbcTemplate.queryForObject(
                sql, new Object[] { document_id }, 
                new BeanPropertyRowMapper(Document.class));
        }catch (DataAccessException runtimeException){
            log.error("***Dao:: fail to GET DOCUMENT, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
        return document;
    }

    
    /**
     * Insert new document in
     * @param user_id 
     * @return  
     */
    @Override
    public boolean insertDocument(final MultipartFile file,final String note, final int user_id,final String dirName) {
        int tmp = countDocument(this.jdbcTemplate);
        String fileName = "";
        KeyHolder holder = new GeneratedKeyHolder();
        final String query = "insert into Document (file,note) values (?,?)";
        int document_generated_key = 0;
        
        //    Read/Write uploaded file
        if(file != null && !file.isEmpty()){
            InputStream inputStream = null;  
            OutputStream outputStream = null;  
            fileName = tmp + file.getOriginalFilename(); 
            try {  
                inputStream = file.getInputStream();
                
                boolean success = (new File(dirName+ "/" + user_id)).mkdir();
                File newFile = new File(dirName + "/" + user_id + "/" + fileName);  
                if(!newFile.exists()){
                    newFile.createNewFile();                      
                }
   
                outputStream = new FileOutputStream(newFile);  
                int read = 0;  
                byte[] bytes = new byte[1024];  
                while ((read = inputStream.read(bytes)) != -1) {  
                    outputStream.write(bytes, 0, read);  
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {  
                log.error("***Dao:: fail to WRITE FILE SYS, RuntimeException occurred, message follows.");
                e.printStackTrace();  
            }      
        }
        
        
        String real = fileName;
        final String nameFileSave = real;
        // Insert into document table
        try{
            this.jdbcTemplate.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{ 
                PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                if(file!=null){
                    preparedStatement.setString(1,nameFileSave); 
                }else{
                    preparedStatement.setString(1,""); 
                }
                preparedStatement.setString(2,note); 
                return preparedStatement; 
            } 
        },holder);
            
        document_generated_key = holder .getKey().intValue();    
        // Insert into diary table
            this.jdbcTemplate.update(
                "insert into Diary (user_id,document_id) values (?, ?)", 
                new Object[] {user_id,document_generated_key});
        }catch (DataAccessException runtimeException){
            log.error("***Dao::fail to INSERT INTO DIARY A DOCUMENT, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }    
        return true;
    }

    
    public int countDocument(JdbcTemplate jdbcTemplate){
        int res = 0;
        String sql = "SELECT COUNT(*) FROM Document";
        res = (int) jdbcTemplate.queryForObject(sql,Integer.class);
        return res;
    }
    
    
    @Override
    public boolean uploadDocument(File file) {            
        if (file!=null) {
            String fileName = file.getName();
            FileOutputStream fOS = null;
            File fOUT = new File("/",fileName);
            try (FileInputStream fIS = new FileInputStream(file)) {
                fOS = new FileOutputStream(fOUT);
                while (fIS.available()>0)
                    fOS.write(fIS.read());
            } catch (FileNotFoundException ex) {
                log.debug(DocumentJdbcDao.class.getName());
            } catch (IOException ex) {
                log.debug(DocumentJdbcDao.class.getName());
            }
            try {
                fOS.close();
            } catch (IOException ex) {
                log.debug(DocumentJdbcDao.class.getName());
            }
        }
        return true;
    }
    
    
    
    /**
     * Edit input document
     * @param document 
     * @return  
     */
    @Override
    public boolean editDocument(Document document) {
        try{
            this.jdbcTemplate.update(
                "update Document set id = ?,date = ?, file = ?  where id = ?", 
                new Object[] {document.getId(), document.getDate(), document.getFile(),document.getId()});
        }catch (DataAccessException runtimeException){
            log.error("***Dao:: edit Document FAIL, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }    
        return true;
    }

    
    /**
     * Delete document from id_document
     * @param document_id
     * @return 
     */
    @Override
    public boolean deleteDocument(int document_id) {
        String deleteStatement1 = "DELETE FROM Diary WHERE document_id = ?";
        String deleteStatement2 = "DELETE FROM Document WHERE id = ?";
        try{
            this.jdbcTemplate.update(deleteStatement1, document_id);
            this.jdbcTemplate.update(deleteStatement2, document_id);
        }catch (DataAccessException runtimeException){
            log.error("***NagiosHostDao::DELETE DOCUMENT FAILED, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }    
        return true;
    }

    /**
     * Return diary of IdUser
     * @param user_id
     * @return 
     */
    @Override
    public ArrayList<Document> getDiary(int user_id) {
        
        String dirName = sr.getServletContext().getRealPath("Resources/"+user_id+"/");
        String sql = "SELECT * FROM Document NATURAL JOIN Diary WHERE user_id = ?";
	try{
            List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql,user_id);
            return DocumentMapper.getDiaryMap(rows,dirName,user_id);
        }catch (DataAccessException runtimeException){
            log.error("***Dao::create diary FAIL, RuntimeException occurred, message follows.");
            log.error(runtimeException);
            throw runtimeException;
        }
    }

}
