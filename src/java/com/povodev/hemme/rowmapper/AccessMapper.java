package com.povodev.hemme.rowmapper;

import com.povodev.hemme.bean.Access;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe mapper che consente di gestire result multipli di query SQL
 * @author Babol
 */
public class AccessMapper {
   
    /**
     * Funzionalità di memorizzazione di device 
     * @param rows
     * @return 
     */
    public static ArrayList<Access> getDevices(List<Map<String, Object>> rows){
        ArrayList<Access> devices = new ArrayList();

        for (Map row : rows) {
            Access access = new Access();
            access.setId_access((int) row.get("access_id"));
            access.setEmail((String)row.get("email"));
            access.setPassword((String)row.get("password"));
            access.setImei((String)row.get("imei"));
            access.setRole((int) row.get("role"));
            access.setStatus((int) row.get("status"));
            
            devices.add(access);
        }              
        return devices;
    }
}
