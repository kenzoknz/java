package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database utility class to manage connections to the database
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/computer_lab_management";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    
    private static Connection connection = null;
    
    /**
     * Get a connection to the database
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
    
    /**
     * Close the database connection
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}