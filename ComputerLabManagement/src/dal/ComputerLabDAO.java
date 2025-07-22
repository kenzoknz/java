package dal;

import model.ComputerLab;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputerLabDAO {
    public List<ComputerLab> getAllLabs() {
        List<ComputerLab> labs = new ArrayList<>();
        String sql = "SELECT * FROM computer_labs";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                labs.add(new ComputerLab(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getInt("capacity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labs;
    }


    public boolean addLab(ComputerLab lab) {
        String sql = "INSERT INTO computer_labs (name, location, capacity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, lab.getName());
            pstmt.setString(2, lab.getLocation());
            pstmt.setInt(3, lab.getCapacity());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLab(ComputerLab lab) {
        String sql = "UPDATE computer_labs SET name = ?, location = ?, capacity = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, lab.getName());
            pstmt.setString(2, lab.getLocation());
            pstmt.setInt(3, lab.getCapacity());
            pstmt.setInt(4, lab.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLab(int id) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); 

            // 1. Xóa tất cả máy tính trong phòng trước
            String deleteComputersSQL = "DELETE FROM computers WHERE lab_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteComputersSQL)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }

            // 2. Xóa phòng máy
            String deleteLabSQL = "DELETE FROM computer_labs WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteLabSQL)) {
                pstmt.setInt(1, id);
                int affectedRows = pstmt.executeUpdate();
                
                if (affectedRows > 0) {
                    conn.commit(); // Commit transaction nếu thành công
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Đặt lại auto commit
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public List<ComputerLab> searchLabs(String keyword) {
        List<ComputerLab> labs = new ArrayList<>();
        String sql = "SELECT * FROM computer_labs WHERE LOWER(name) LIKE ? OR LOWER(location) LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword.toLowerCase() + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                labs.add(new ComputerLab(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getInt("capacity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labs;
    }
}