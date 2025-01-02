package virtual.catalog.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
    private final String DB = "virtual_catalog";
    private final String URL = "jdbc:mysql://localhost:3306/" + DB;
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    private Connection connection = null;
    
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
}
