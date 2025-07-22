package BaiThucHanhSo4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Bai2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String username = "root";
    private static final String password = "123456";
    
    private JTextField textField;
    DefaultTableModel model;
    JTable table;

    public Bai2() {
        super("Select");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(632, 433);
        setLocationRelativeTo(null);

        JPanel pn = new JPanel(new BorderLayout());
        getContentPane().add(pn);

        // Top panel (input)
        JPanel topPanel = new JPanel();
        pn.add(topPanel, BorderLayout.NORTH);

        JLabel txtLabel = new JLabel("Nhập nội dung");
        topPanel.add(txtLabel);

        textField = new JTextField();
        topPanel.add(textField);
        textField.setColumns(20);

        JButton btnSearch = new JButton("Search");
        topPanel.add(btnSearch);

        JButton btnReset = new JButton("Reset");
        topPanel.add(btnReset);

        JButton btnExit = new JButton("Exit");
        topPanel.add(btnExit);

        // Middle panel (radio buttons)
        JPanel middlePanel = new JPanel();
        pn.add(middlePanel, BorderLayout.CENTER);

        JLabel lbSearch = new JLabel("Tìm kiếm theo: ");
        middlePanel.add(lbSearch);

        JRadioButton rb1 = new JRadioButton("Id");
        JRadioButton rb2 = new JRadioButton("Name");
        JRadioButton rb3 = new JRadioButton("Date");
        JRadioButton rb4 = new JRadioButton("Address");
        JRadioButton rb5 = new JRadioButton("Gender");

        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        group.add(rb3);
        group.add(rb4);
        group.add(rb5);

        middlePanel.add(rb1);
        middlePanel.add(rb2);
        middlePanel.add(rb3);
        middlePanel.add(rb4);
        middlePanel.add(rb5);

        // Bottom panel (table)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        pn.add(bottomPanel, BorderLayout.SOUTH);

        String[] columns = { "MaSo", "HoTen", "NgaySinh", "DiaChi", "GioiTinh" };
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 150));
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // Listener for Search button
        btnSearch.addActionListener(e -> {
            if (!rb1.isSelected() && !rb2.isSelected() && !rb3.isSelected() && !rb4.isSelected() && !rb5.isSelected()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tiêu chí tìm kiếm!");
                return;
            }

            String keyword = textField.getText().trim();
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập nội dung tìm kiếm!");
                return;
            }

            String column = "";
            if (rb1.isSelected()) column = "Id";
            else if (rb2.isSelected()) column = "Name";
            else if (rb3.isSelected()) column = "Date";
            else if (rb4.isSelected()) column = "Address";
            else if (rb5.isSelected()) column = "Gender";

            searchDatabase(column, keyword);
        });

        // Listener for Reset button
        btnReset.addActionListener(e -> {
            textField.setText("");
            group.clearSelection();
            model.setRowCount(0);
        });

        // Listener for Exit button
        btnExit.addActionListener(e -> System.exit(0));
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/DATA", username, password); // sửa password nếu cần
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void searchDatabase(String column, String keyword) {
        model.setRowCount(0); // clear table

        String sql;
        if (column.equals("Gender")) {
            if (keyword.equalsIgnoreCase("nữ")) {
                sql = "SELECT * FROM Table2 WHERE Gender = true";
            } else if (keyword.equalsIgnoreCase("nam")) {
                sql = "SELECT * FROM Table2 WHERE Gender = false";
            } else {
                JOptionPane.showMessageDialog(this, "Giới tính chỉ được nhập 'Nam' hoặc 'Nữ'");
                return;
            }
        } else {
            sql = "SELECT * FROM Table2 WHERE " + column + " LIKE ?";
        }

        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            if (!column.equals("Gender")) {
                ps.setString(1, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("Id");
                String name = rs.getString("Name");
                String date = rs.getString("Date");
                String address = rs.getString("Address");
                boolean gender = rs.getBoolean("Gender");
                String genderStr = gender ? "Nữ" : "Nam";
                model.addRow(new Object[] { id, name, date, address, genderStr });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Bai2 frame = new Bai2();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
