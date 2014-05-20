package com.povodev.hemme.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.jdbc.core.JdbcTemplate;


public class Encoding_Md5 {
    
    JdbcTemplate jdbcTemplate;

    Encoding_Md5(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public boolean authHash(String salt) throws NoSuchAlgorithmException{
     
        String password = "povodevforhemme"+salt;
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
 
 
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
 

        if(dbin(hexString.toString()))
            return true;
        else
            return false;
        
    }
    
    private boolean dbin(String hex){
    
        String sql = "SELECT COUNT(*) FROM hash_table WHERE hash = '" + hex + "' ";
        int res = 0;
    
        res = this.jdbcTemplate.queryForObject(sql, Integer.class);

        if(res == 1)
            return true;
        else
            return false;
    }
    
}
