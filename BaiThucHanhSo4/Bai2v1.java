package BaiThucHanhSo4;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Bai2v1 extends Frame {
    TextField txtConnection, txtSQLQuery;
    TextArea tableArea;
    Button btnSubmit, btnReset, btnCancel;

    public Bai2v1() {
        setTitle("Database Programming with ODBC");
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));

        Panel topPanel = new Panel(new GridLayout(2, 2, 10, 10));
        topPanel.add(new Label("Input Information"));
        txtConnection = new TextField("jdbc:odbc:DATA");
        topPanel.add(txtConnection);

        topPanel.add(new Label("SQL Query"));
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
        
        try {
            // Load the JDBC-ODBC bridge driver
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            
            try (Connection conn = DriverManager.getConnection(url)) {
              
                if (sql.toUpperCase().startsWith("SELECT")) {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    ResultSetMetaData meta = rs.getMetaData();
                    int columnCount = meta.getColumnCount();

                    for (int i = 1; i <= columnCount; i++) {
                        tableArea.append(padRight(meta.getColumnName(i), 20));
                    }
                    tableArea.append("\n");
                    
                    while (rs.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            tableArea.append(padRight(rs.getString(i), 20));
                        }
                        tableArea.append("\n");
                    }
                    rs.close();
                    stmt.close();
                } else {
                 
                    Statement stmt = conn.createStatement();
                    int result = stmt.executeUpdate(sql);
                    tableArea.append("Query executed successfully. Rows affected: " + result + "\n");
                    stmt.close();
                }
            } catch (SQLException ex) {
                tableArea.append("SQL Error: " + ex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            tableArea.append("JDBC-ODBC Bridge Driver not found: " + ex.getMessage());
        }
    }

    public static String padRight(String text, int length) {
        return String.format("%-" + length + "s", text == null ? "" : text);
    }

    public static void main(String[] args) {
        new Bai2v1();
    }
}