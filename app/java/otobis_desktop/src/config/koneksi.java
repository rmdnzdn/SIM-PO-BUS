/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author zidun
 */
public class koneksi {
    private static Connection cn;
    
    public static Connection getKoneksi(){
        if (cn == null) {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/db_otobis_dun","root","");
            } catch (Exception e) {
                e.printStackTrace();  
            }
        }
        return cn;
    }
}
