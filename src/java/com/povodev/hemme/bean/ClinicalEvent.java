/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.povodev.hemme.bean;

import java.sql.Timestamp;

/**
 *
 * @author smunarini.stage
 */
public class ClinicalEvent {
    
    private int id;
    private int author;
    private Timestamp date;
    private String therapy;
    private String note;
    private String author_name;

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
     * @return the therapy
     */
    public String getTherapy() {
        return therapy;
    }

    /**
     * @param therapy the therapy to set
     */
    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the author
     */
    public int getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(int author) {
        this.author = author;
    }

    /**
     * @return the date
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * @return the author_name
     */
    public String getAuthor_name() {
        return author_name;
    }

    /**
     * @param author_name the author_name to set
     */
    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
    
}
