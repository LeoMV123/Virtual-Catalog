/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtual.catalog.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author burto
 */
public class ConnectionMySQL {
    private final String DB = "virtual_category";
    private final String URL = "jdbc:mysql:localhost:3306/" + DB;
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private static ConnectionMySQL instance;
    private Connection connection;
    
    public ConnectionMySQL () {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public Connection getConnection () {
        return connection;
    }
    
    public static ConnectionMySQL getInstance () {
        if (instance == null) {
            instance = new ConnectionMySQL();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new ConnectionMySQL();
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        return instance;
    }
}
