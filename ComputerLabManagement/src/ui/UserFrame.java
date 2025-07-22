package ui;

import model.Computer;
import model.ComputerLab;
import bll.ComputerBLL;
import bll.ComputerLabBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class UserFrame extends JFrame {
    private DefaultTableModel labTableModel;
    private ComputerLabBLL labBLL;
    private ComputerBLL computerBLL;
    private JTable labTable;
    private JButton btnViewComputers;
    private JButton btnRefresh;

    public UserFrame() {
        labBLL = new ComputerLabBLL();
        computerBLL = new ComputerBLL();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Quản lý phòng máy - User");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table model for labs
        labTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        labTableModel.addColumn("ID");
        labTableModel.addColumn("Tên phòng");
        labTableModel.addColumn("Vị trí");
        labTableModel.addColumn("Sức chứa");
        labTableModel.addColumn("Số máy đang hoạt động");

        labTable = new JTable(labTableModel);
        labTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(labTable);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnViewComputers = new JButton("Xem máy tính");
        btnViewComputers.addActionListener(this::viewComputers);
        
        btnRefresh = new JButton("Làm mới danh sách");
        btnRefresh.addActionListener(e -> loadLabs());
        
        buttonPanel.add(btnViewComputers);
        buttonPanel.add(btnRefresh);

        // Add components to main panel
        mainPanel.add(new JLabel("Danh sách phòng máy", JLabel.CENTER), BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        loadLabs();
    }

    private void loadLabs() {
        labTableModel.setRowCount(0); // Clear existing data
        List<ComputerLab> labs = labBLL.getAllLabs();
        
        for (ComputerLab lab : labs) {
            // Count active computers for each lab
            List<Computer> computers = computerBLL.getComputersByLab(lab.getId());
            long activeComputers = computers.stream().filter(Computer::isStatus).count();
            
            labTableModel.addRow(new Object[]{
                lab.getId(),
                lab.getName(),
                lab.getLocation(),
                lab.getCapacity(),
                activeComputers + "/" + computers.size()
            });
        }
    }

    private void viewComputers(ActionEvent e) {
        int selectedRow = labTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn một phòng máy trước!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int labId = (int) labTableModel.getValueAt(selectedRow, 0);
        String labName = (String) labTableModel.getValueAt(selectedRow, 1);
        
        List<Computer> computers = computerBLL.getComputersByLab(labId);
        
        // Create table model for computers
        DefaultTableModel computerModel = new DefaultTableModel();
        computerModel.addColumn("ID");
        computerModel.addColumn("Tên máy");
        computerModel.addColumn("Trạng thái");
        
        for (Computer computer : computers) {
            computerModel.addRow(new Object[]{
                computer.getId(),
                computer.getName(),
                computer.isStatus() ? "✅ Đang hoạt động" : "❌ Đang tắt"
            });
        }
        
        // Create and configure computer table
        JTable computerTable = new JTable(computerModel);
        computerTable.setRowHeight(25);
        computerTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        
        // Create dialog to display computers
        JDialog computerDialog = new JDialog(this, "Danh sách máy tính - " + labName, true);
        computerDialog.setSize(500, 400);
        computerDialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(computerTable), BorderLayout.CENTER);
        
        JButton btnClose = new JButton("Đóng");
        btnClose.addActionListener(ev -> computerDialog.dispose());
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnClose);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        computerDialog.add(panel);
        computerDialog.setVisible(true);
    }


}