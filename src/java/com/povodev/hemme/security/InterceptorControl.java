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
 
public class InterceptorControl implements HandlerInterceptor  {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
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
            System.err.println(key + "---" + value);
	}
        
        //controllo che la stringa passata come HAS(salt) corrispona alla HAS("povodevforhemmeABC")
        if(salt.equals("21bd8aee973b8457476af0bac8b65b2a")){
            System.err.println("Hash CORRETTA");
            salt = "ABC";
            System.err.println("__risultato hash nel database__"+en.authHash(salt));
            return true;
        }else{
            System.err.println("Hash NON CORRETTA");
            return false;
        }
        
        //Stampa tutti i valori presenti nello header della richiesta
        //System.err.println("----" + map);
    }
    
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println("Post-handle");
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("After completion handle");
    }
}