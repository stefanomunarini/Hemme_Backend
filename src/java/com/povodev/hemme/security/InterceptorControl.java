package com.povodev.hemme.security;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
 
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 

/**
 * Classe per filtrare le chiamate al server. Controllo della password contenuta nello header della richiesta
 * @author Babol
 */
public class InterceptorControl implements HandlerInterceptor  {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Funzioni da eseguire prima di continuare la richiesta
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception 
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        
        Encoding_Md5 en = new Encoding_Md5(this.jdbcTemplate);
        String salt = "";
        Map<String, String> map = new HashMap<String, String>();
 
	Enumeration headerNames = request.getHeaderNames();
	while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
            if(key.equals("salt")){
                salt = value;
            }
	}
        
        //controllo che la stringa passata come HAS(salt) corrispona alla HAS("povodevforhemmeABC")
        if(salt.equals("21bd8aee973b8457476af0bac8b65b2a")){
            System.err.println("Hash CORRETTA");
            salt = "ABC";
            return true;
        }else{
            System.err.println("Hash NON CORRETTA");
            return false;
        }
    }
    
    
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}