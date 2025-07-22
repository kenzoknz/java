package BaiThucHanhSo4;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Bai1 extends Frame {
    TextField txtConnection, txtSQLQuery;
    Choice choiceQueryType;
    TextArea tableArea;
    Button btnSubmit, btnReset, btnCancel;
    private static final String user = "root";
    private static final String password = "123456";

    public Bai1() {
        setTitle("Database Programming");
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));

        // Panel trên cùng
        Panel topPanel = new Panel(new GridLayout(2, 3, 10, 10));
        topPanel.add(new Label("Input Information"));
        txtConnection = new TextField("jdbc:mysql://127.0.0.1:3306/DATA");
        topPanel.add(txtConnection);
        topPanel.add(new Label(""));

        topPanel.add(new Label("SQL Query"));
        txtSQLQuery = new TextField("SELECT * FROM Table1");
        topPanel.add(txtSQLQuery);
        choiceQueryType = new Choice();
        choiceQueryType.add("Select");
        choiceQueryType.add("Insert");
        choiceQueryType.add("Update");
        choiceQueryType.add("Delete");
        choiceQueryType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = choiceQueryType.getSelectedItem();
                switch (type) {
                    case "Select":
                        txtSQLQuery.setText("SELECT * FROM Table1");
                        break;
                    case "Insert":
                        txtSQLQuery.setText("INSERT INTO Table1 VALUES (?, ?, ?, ?)");
                        break;
                    case "Update":
                        txtSQLQuery.setText("UPDATE Table1 SET NAME=?, ADDRESS=?, TOTAL=? WHERE ID=?");
                        break;
                    case "Delete":
                        txtSQLQuery.setText("DELETE FROM Table1 WHERE ID=?");
                        break;
                }
            }
        });

        topPanel.add(choiceQueryType);
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
        String sql = txtSQLQuery.getText();
        String type = choiceQueryType.getSelectedItem();
        tableArea.setText(""); 
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();

            if (type.equals("Select")) {
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
            } else {
                int result = stmt.executeUpdate(sql);
                tableArea.append(type + " success. Rows affected: " + result + "\n");
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
        new Bai1();
    }
}
