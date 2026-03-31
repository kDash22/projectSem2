package salessystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Establishes and returns a connection to the MySQL database using the given URL, username, and password.

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/sales_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(SQLException e) {
            throw new RuntimeException(" DataBase connection failed ! ",e);
        }
    }
}
