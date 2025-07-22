package BaiThucHanhSo4;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Bai3v1 extends Frame {
    TextField txtConnection, txtSQLQuery;
    TextArea tableArea;
    Button btnSubmit, btnReset, btnCancel;
    private static final String user = "root";
    private static final String password = "123456";

    public Bai3v1() {
        setTitle("Database Programming");
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));

        Panel topPanel = new Panel(new GridLayout(2, 2, 10, 10));
        topPanel.add(new Label("Connection String:"));
        txtConnection = new TextField("jdbc:mysql://127.0.0.1:3306/DATA");
        topPanel.add(txtConnection);

        topPanel.add(new Label("SQL Query:"));
        txtSQLQuery = new TextField("SELECT * FROM Table1");
        topPanel.add(txtSQLQuery);
        
        add(topPanel, BorderLayout.NORTH);

        tableArea = new TextArea("", 10, 50, TextArea.SCROLLBARS_BOTH);
        tableArea.setEditable(false);
        add(tableArea, BorderLayout.CENTER);
        
        Panel bottomPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnSubmit = new Button("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                executeSQL();
            }
        });

        btnReset = new Button("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtSQLQuery.setText("");
                tableArea.setText("");
            }
        });

        btnCancel = new Button("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               System.exit(0); 
            }
        });

        bottomPanel.add(btnSubmit);
        bottomPanel.add(btnReset);
        bottomPanel.add(btnCancel);
        add(bottomPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void executeSQL() {
        String url = txtConnection.getText();
        String sql = txtSQLQuery.getText().trim();
        tableArea.setText("");
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            
            if (sql.toUpperCase().startsWith("SELECT")) {
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                StringBuilder headerLine = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    headerLine.append(padRight(meta.getColumnName(i), 20));
                }
                tableArea.append(headerLine.toString() + "\n");
                
                // Display separator line
                tableArea.append("-".repeat(headerLine.length()) + "\n");
                
                // Display data rows
                while (rs.next()) {
                    StringBuilder rowLine = new StringBuilder();
                    for (int i = 1; i <= columnCount; i++) {
                        rowLine.append(padRight(rs.getString(i), 20));
                    }
                    tableArea.append(rowLine.toString() + "\n");
                }
                rs.close();
            } else {
                // For UPDATE, INSERT, DELETE
                int result = stmt.executeUpdate(sql);
                tableArea.append("Query executed successfully.\nRows affected: " + result + "\n");
            }
            stmt.close();
        } catch (SQLException ex) {
            tableArea.append("SQL Error: " + ex.getMessage());
        }
    }

    public static String padRight(String text, int length) {
        return String.format("%-" + length + "s", text == null ? "" : text);
    }

    public static void main(String[] args) {
        new Bai3v1();
    }
}