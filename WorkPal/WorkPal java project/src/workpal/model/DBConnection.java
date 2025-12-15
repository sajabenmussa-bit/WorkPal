package workpal.model;

import java.sql.*;



public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/workpal"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}


    