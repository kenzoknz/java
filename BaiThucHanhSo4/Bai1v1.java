package BaiThucHanhSo4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class Bai1v1 {
	//Cau a
    public static void accessWithJdbcOdbcBridge() {
        System.out.println("type 1 (jdbc-odbc Bridge)");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
        String url = "jdbc:odbc:data";


        try {
            Class.forName(driver); 
            conn = DriverManager.getConnection(url); 
            stmt = conn.createStatement();
            String sql = "SELECT Id, Name, Address, Total FROM Table1"; 
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                double total = rs.getDouble("Total");

                System.out.printf( "ID=%d Name=%s Address=%s Total=%.1f%n", id, name, address, total);
            }


        } catch (Exception e) {
            System.err.println("Exception:");
            e.printStackTrace();
        }
    }

    //Cau b
    public static void accessWithNativeDriverMySQL() {
        System.out.println("(Type 4 - MySQL)");

        String dbUrl = "jdbc:mysql://localhost:3306/data"; 
        String username = "root"; 
        String password = "123456"; 

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT Id, Name, Address, Total FROM Table1")) {
            System.out.println("Id Name Address Total"); 

            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                double total = rs.getDouble("Total");

                System.out.printf("%d %s %s %.1f%n", id, name, address, total);
            }

        } catch (SQLException e) {
            System.err.println("Failed to connect.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        accessWithJdbcOdbcBridge();
        accessWithNativeDriverMySQL();
    }
}