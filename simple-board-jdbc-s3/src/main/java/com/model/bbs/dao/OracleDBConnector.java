package com.model.bbs.dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
 
import org.springframework.stereotype.Repository;
 
@Repository
public class OracleDBConnector {
    Connection conn;
    
    public Connection getConnection() {
        try {
            Class.forName("core.log.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            conn = DriverManager.getConnection(url, "Kyou", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return conn;
    }
}
