package com.povodev.hemme.bean;

/**
 * Classe utilizzata come bean. Il Bean mappa in tutti i suo argomenti, gli attributi del database della tabella corrispondente
 * @author Povodev
 */
public class Access {
    
    private int id_access;
    private String email;
    private String password;
    private String imei;
    private int role;
    private int status;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei the imei to set
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * @return the id_access
     */
    public int getId_access() {
        return id_access;
    }

    /**
     * @param id_access the id_access to set
     */
    public void setId_access(int id_access) {
        this.id_access = id_access;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    
}
