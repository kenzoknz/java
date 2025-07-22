package ui;

import model.User;
import utils.DatabaseConnection;
import bll.UserBLL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private UserBLL userBLL;

    public LoginFrame() {
        userBLL = new UserBLL();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Đăng nhập hệ thống");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Tên đăng nhập:"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Mật khẩu:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        JButton btnLogin = new JButton("Đăng nhập");
        btnLogin.addActionListener(this::performLogin);
        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel);
    }

    private void performLogin(ActionEvent e) {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        User user = userBLL.authenticate(username, password);
        if (user != null) {
            dispose();
            if (user.isAdmin()) {
                new AdminFrame().setVisible(true);
            } else {
                new UserFrame().setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Tên đăng nhập hoặc mật khẩu không đúng!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        // check connection
        try (Connection conn = DatabaseConnection.getConnection()) {
            SwingUtilities.invokeLater(() -> {
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            });
            
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối database:");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Không thể kết nối database!\n" + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

}