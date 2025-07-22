package dal;

import model.Computer;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputerDAO {
    public List<Computer> getComputersByLab(int labId) {
        List<Computer> computers = new ArrayList<>();
        String sql = "SELECT * FROM computers WHERE lab_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, labId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                computers.add(new Computer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("status"),
                    rs.getInt("lab_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return computers;
    }

    public boolean addComputer(Computer computer) {
        String sql = "INSERT INTO computers (name, status, lab_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, computer.getName());
            pstmt.setBoolean(2, computer.isStatus());
            pstmt.setInt(3, computer.getLabId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateComputer(Computer computer) {
        String sql = "UPDATE computers SET name = ?, status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, computer.getName());
            pstmt.setBoolean(2, computer.isStatus());
            pstmt.setInt(3, computer.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteComputer(int id) {
        String sql = "DELETE FROM computers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Computer> searchComputers(String keyword, Integer labId) {
        List<Computer> computers = new ArrayList<>();
        String sql = "SELECT * FROM computers WHERE LOWER(name) LIKE ?" + (labId != null ? " AND lab_id = ?" : "");
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword.toLowerCase() + "%";
            pstmt.setString(1, searchPattern);
            if (labId != null) {
                pstmt.setInt(2, labId);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                computers.add(new Computer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("status"),
                    rs.getInt("lab_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return computers;
    }
}