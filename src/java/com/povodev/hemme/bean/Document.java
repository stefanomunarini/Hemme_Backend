package com.povodev.hemme.bean;

import java.io.File;
import java.sql.Date;

/**
 *
 * @author gbonadiman.stage
 */
public class Document {
 
    private int id;
    private Date date;
    private String file;
    private File uploaded;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return the uploaded
     */
    public File getUploaded() {
        return uploaded;
    }

    /**
     * @param uploaded the uploaded to set
     */
    public void setUploaded(File uploaded) {
        this.uploaded = uploaded;
    }
}
