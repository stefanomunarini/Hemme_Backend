package com.povodev.hemme.bean;

/**
 * Classe utilizzata come bean. Il Bean mappa in tutti i suo argomenti, gli attributi del database della tabella corrispondente
 * @author Povodev
 */
public class Diary {

    private int id;
    private int user_id;
    private int document_id;

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
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the document_id
     */
    public int getDocument_id() {
        return document_id;
    }

    /**
     * @param document_id the document_id to set
     */
    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }
    
}
