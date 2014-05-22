package com.povodev.hemme.bean;

/**
 * Classe utilizzata come bean. Il Bean mappa in tutti i suo argomenti, gli attributi del database della tabella corrispondente
 * @author Povodev
 */
public class LocationCoordinates {
    
    private int id;
    private int user_id;
    private double latitude;
    private double longitude;
    private int radius;

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
     * @return the lat
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param lat the lat to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the lon
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param lon the lon to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

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
    
}
