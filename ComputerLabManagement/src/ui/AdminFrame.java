package ui;

import model.Computer;
import model.ComputerLab;
import bll.ComputerBLL;
import bll.ComputerLabBLL;
import model.User;
import bll.UserBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AdminFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private DefaultTableModel labTableModel;
    private DefaultTableModel computerTableModel;
    private ComputerLabBLL labBLL;
    private ComputerBLL computerBLL;
    private JComboBox<ComputerLab> labComboBox;
    private JTable labTable;
    private JTable computerTable;
    private JTextField labSearchField;
    private JTextField computerSearchField;
    private JButton btnAddUser;
    private UserBLL userBLL;
    private DefaultTableModel userTableModel;
    private JTable userTable;

    public AdminFrame() {
        labBLL = new ComputerLabBLL();
        computerBLL = new ComputerBLL();
        userBLL = new UserBLL();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Quản lý phòng máy - Admin");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        
        // Tab 1: Quản lý Phòng
        tabbedPane.addTab("Quản lý Phòng", createLabManagementPanel());
        
        // Tab 2: Quản lý Máy tính
        tabbedPane.addTab("Quản lý Máy tính", createComputerManagementPanel());
        
        // Tab 3: Quản lý Người dùng
        tabbedPane.addTab("Quản lý Người dùng", createUserManagementPanel());
        
        add(tabbedPane);
    }

    private JPanel createLabManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model
        labTableModel = new DefaultTableModel();
        labTableModel.addColumn("ID");
        labTableModel.addColumn("Tên phòng");
        labTableModel.addColumn("Vị trí");
        labTableModel.addColumn("Sức chứa");

        labTable = new JTable(labTableModel);
        JScrollPane scrollPane = new JScrollPane(labTable);

        // Search
        labSearchField = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> searchLabs());
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Tìm kiếm phòng:"));
        searchPanel.add(labSearchField);
        searchPanel.add(btnSearch);

        // Buttons
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnRefresh = new JButton("Làm mới");
        JButton btnLogout = new JButton("Đăng xuất");

        // Button actions
        btnAdd.addActionListener(this::showAddLabDialog);
        btnEdit.addActionListener(this::showEditLabDialog);
        btnDelete.addActionListener(this::deleteLab);
        btnRefresh.addActionListener(e -> loadLabs());
        btnLogout.addActionListener(this::logout);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnLogout);

        // Layout
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        loadLabs();
        return panel;
    }

    private void searchLabs() {
        String keyword = labSearchField.getText().trim();
        if (keyword.isEmpty()) {
            loadLabs();
            return;
        }
        labTableModel.setRowCount(0);
        List<ComputerLab> labs = labBLL.searchLabs(keyword);
        for (ComputerLab lab : labs) {
            labTableModel.addRow(new Object[]{
                lab.getId(),
                lab.getName(),
                lab.getLocation(),
                lab.getCapacity()
            });
        }
    }

    private void toggleComputerStatus(ActionEvent e) {
        int selectedRow = computerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn một máy để bật/tắt!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int computerId = (int) computerTableModel.getValueAt(selectedRow, 0);
        boolean currentStatus = computerTableModel.getValueAt(selectedRow, 2).equals("Đang bật");
        
        Computer computer = new Computer(
            computerId,
            (String) computerTableModel.getValueAt(selectedRow, 1),
            !currentStatus,
            ((ComputerLab) labComboBox.getSelectedItem()).getId()
        );
        
        if (computerBLL.updateComputer(computer)) {
            JOptionPane.showMessageDialog(this, 
                "Đã " + (!currentStatus ? "bật" : "tắt") + " máy thành công!");
            loadComputersForSelectedLab(null);
        }
    }
    private JPanel createComputerManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // chọn phòng
        labComboBox = new JComboBox<>();
        labComboBox.addActionListener(this::loadComputersForSelectedLab);
        
        JButton refreshLabsBtn = new JButton("Làm mới DS phòng");
        refreshLabsBtn.addActionListener(e -> loadLabsToComboBox());

        // Search
        computerSearchField = new JTextField(20);
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(e -> searchComputers());
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Tìm kiếm máy:"));
        searchPanel.add(computerSearchField);
        searchPanel.add(btnSearch);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Chọn phòng:"));
        topPanel.add(labComboBox);
        topPanel.add(refreshLabsBtn);

        // Table model
        computerTableModel = new DefaultTableModel();
        computerTableModel.addColumn("ID");
        computerTableModel.addColumn("Tên máy");
        computerTableModel.addColumn("Trạng thái");
       // computerTableModel.addColumn("Phòng");

        computerTable = new JTable(computerTableModel);
        JScrollPane scrollPane = new JScrollPane(computerTable);

        // Buttons
        JButton btnAdd = new JButton("Thêm máy");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnToggle = new JButton("Bật/Tắt");
        JButton btnRefresh = new JButton("Làm mới DS máy");
        JButton btnLogout = new JButton("Đăng xuất");
        // Button actions
        btnAdd.addActionListener(this::showAddComputerDialog);
        btnEdit.addActionListener(this::showEditComputerDialog);
        btnDelete.addActionListener(this::deleteComputer);
        btnToggle.addActionListener(this::toggleComputerStatus);
        btnRefresh.addActionListener(e -> loadComputersForSelectedLab(null));
        btnLogout.addActionListener(this::logout);


        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnToggle);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnLogout);

        // Layout
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(topPanel, BorderLayout.NORTH);
        northPanel.add(searchPanel, BorderLayout.SOUTH);
        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        loadLabsToComboBox();
        return panel;
    }

    private void searchComputers() {
        String keyword = computerSearchField.getText().trim();
        ComputerLab selectedLab = (ComputerLab) labComboBox.getSelectedItem();
        Integer labId = selectedLab != null ? selectedLab.getId() : null;
        if (keyword.isEmpty()) {
            if (labId != null) {
                loadComputers(labId);
            }
            return;
        }
        computerTableModel.setRowCount(0);
        List<Computer> computers = computerBLL.searchComputers(keyword, labId);
        for (Computer computer : computers) {
            computerTableModel.addRow(new Object[]{
                computer.getId(),
                computer.getName(),
                computer.isStatus() ? "Đang bật" : "Đang tắt"
            });
        }
    }

    private void loadLabs() {
        labTableModel.setRowCount(0);
        List<ComputerLab> labs = labBLL.getAllLabs();
        for (ComputerLab lab : labs) {
            labTableModel.addRow(new Object[]{
                lab.getId(),
                lab.getName(),
                lab.getLocation(),
                lab.getCapacity()
            });
        }
    }

    private void loadLabsToComboBox() {
        labComboBox.removeAllItems();
        List<ComputerLab> labs = labBLL.getAllLabs();
        for (ComputerLab lab : labs) {
            labComboBox.addItem(lab);
        }
        labComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                         boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof ComputerLab) {
                    ComputerLab lab = (ComputerLab) value;
                    setToolTipText(lab.getLocation() + " - Sức chứa: " + lab.getCapacity());
                }
                return c;
            }
        });
    }

    private void loadComputersForSelectedLab(ActionEvent e) {
        ComputerLab selectedLab = (ComputerLab) labComboBox.getSelectedItem();
        if (selectedLab != null) {
            loadComputers(selectedLab.getId());
        }
    }

    private void loadComputers(int labId) {
        computerTableModel.setRowCount(0);
        List<Computer> computers = computerBLL.getComputersByLab(labId);
        
        // Lấy thông tin phòng máy
        ComputerLab currentLab = null;
        for (ComputerLab lab : labBLL.getAllLabs()) {
            if (lab.getId() == labId) {
                currentLab = lab;
                break;
            }
        }
        
        for (Computer computer : computers) {
            computerTableModel.addRow(new Object[]{
                computer.getId(),
                computer.getName(),
                computer.isStatus() ? "Đang bật" : "Đang tắt"
            });
        }
    }

    private void showAddLabDialog(ActionEvent e) {
        JTextField txtName = new JTextField();
        JTextField txtLocation = new JTextField();
        JTextField txtCapacity = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Tên phòng:"));
        panel.add(txtName);
        panel.add(new JLabel("Vị trí:"));
        panel.add(txtLocation);
        panel.add(new JLabel("Sức chứa:"));
        panel.add(txtCapacity);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Thêm phòng máy mới", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                ComputerLab lab = new ComputerLab(
                    0,
                    txtName.getText(),
                    txtLocation.getText(),
                    Integer.parseInt(txtCapacity.getText())
                );
                if (labBLL.addLab(lab)) {
                    JOptionPane.showMessageDialog(this, "Thêm phòng thành công!");
                    loadLabs();
                    loadLabsToComboBox();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Sức chứa phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showEditLabDialog(ActionEvent e) {
        int selectedRow = labTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn một phòng để sửa!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) labTableModel.getValueAt(selectedRow, 0);
        String name = (String) labTableModel.getValueAt(selectedRow, 1);
        String location = (String) labTableModel.getValueAt(selectedRow, 2);
        int capacity = (int) labTableModel.getValueAt(selectedRow, 3);

        JTextField txtName = new JTextField(name);
        JTextField txtLocation = new JTextField(location);
        JTextField txtCapacity = new JTextField(String.valueOf(capacity));

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Tên phòng:"));
        panel.add(txtName);
        panel.add(new JLabel("Vị trí:"));
        panel.add(txtLocation);
        panel.add(new JLabel("Sức chứa:"));
        panel.add(txtCapacity);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Sửa thông tin phòng máy", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                ComputerLab lab = new ComputerLab(
                    id,
                    txtName.getText(),
                    txtLocation.getText(),
                    Integer.parseInt(txtCapacity.getText())
                );
                if (labBLL.updateLab(lab)) {
                    JOptionPane.showMessageDialog(this, "Cập nhật phòng thành công!");
                    loadLabs();
                    loadLabsToComboBox();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Sức chứa phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteLab(ActionEvent e) {
        int selectedRow = labTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn một phòng để xóa!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) labTableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Bạn có chắc chắn muốn xóa phòng này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (labBLL.deleteLab(id)) {
                JOptionPane.showMessageDialog(this, "Xóa phòng thành công!");
                loadLabs();
                loadLabsToComboBox();
            }
        }
    }

    private void showAddComputerDialog(ActionEvent e) {
        ComputerLab selectedLab = (ComputerLab) labComboBox.getSelectedItem();
        if (selectedLab == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phòng trước khi thêm máy!");
            return;
        }

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Thêm máy vào phòng: " + selectedLab.getName()));
        panel.add(new JLabel("Tên máy:"));
        
        JTextField txtName = new JTextField();
        panel.add(txtName);
        
        JCheckBox chkStatus = new JCheckBox("Đang bật");
        panel.add(chkStatus);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Thêm máy tính mới", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Computer computer = new Computer(
                0,
                txtName.getText(),
                chkStatus.isSelected(),
                selectedLab.getId()
            );
            
            if (computerBLL.addComputer(computer)) {
                JOptionPane.showMessageDialog(this, "Thêm máy thành công!");
                loadComputers(selectedLab.getId());
            }
        }
    }

    private void showEditComputerDialog(ActionEvent e) {
        int selectedRow = computerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn một máy để sửa!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy thông tin máy từ bảng (chỉ 3 cột)
        int computerId = (int) computerTableModel.getValueAt(selectedRow, 0);
        String computerName = (String) computerTableModel.getValueAt(selectedRow, 1);
        boolean status = computerTableModel.getValueAt(selectedRow, 2).equals("Đang bật");
        
        // Lấy thông tin phòng từ combobox
        ComputerLab selectedLab = (ComputerLab) labComboBox.getSelectedItem();
        if (selectedLab == null) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn phòng máy trước!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Phòng hiện tại: " + selectedLab.getName()));
        panel.add(new JLabel("Tên máy:"));
        
        JTextField txtName = new JTextField(computerName);
        panel.add(txtName);
        
        JCheckBox chkStatus = new JCheckBox("Đang bật", status);
        panel.add(chkStatus);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Sửa thông tin máy tính", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Computer computer = new Computer(
                computerId,
                txtName.getText(),
                chkStatus.isSelected(),
                selectedLab.getId() // Sử dụng ID từ combobox
            );
            
            if (computerBLL.updateComputer(computer)) {
                JOptionPane.showMessageDialog(this, "Cập nhật máy thành công!");
                loadComputers(selectedLab.getId());
            }
        }
    }

    private void deleteComputer(ActionEvent e) {
        int selectedRow = computerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn một máy để xóa!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc muốn xóa máy này?", 
            "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) computerTableModel.getValueAt(selectedRow, 0);
            if (computerBLL.deleteComputer(id)) {
                JOptionPane.showMessageDialog(this, "Xóa máy thành công!");
                loadComputersForSelectedLab(null);
            } else {
                JOptionPane.showMessageDialog(this, "Xóa máy thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void logout(ActionEvent e) {
        this.dispose();
        new LoginFrame().setVisible(true);
    }

    private JPanel createUserManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Table model for users
        userTableModel = new DefaultTableModel();
        userTableModel.addColumn("ID");
        userTableModel.addColumn("Tên người dùng");
        userTableModel.addColumn("Vai trò");

        userTable = new JTable(userTableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(userTable);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnAddUser = new JButton("Thêm người dùng");
        btnAddUser.addActionListener(this::showAddUserDialog);
        
        JButton btnEditUser = new JButton("Sửa");
        btnEditUser.addActionListener(this::showEditUserDialog);
        
        JButton btnDeleteUser = new JButton("Xóa");
        btnDeleteUser.addActionListener(this::deleteUser);
        
        JButton btnRefresh = new JButton("Làm mới danh sách");
        btnRefresh.addActionListener(e -> loadUsers());
        JButton btnLogout = new JButton("Đăng xuất");
        btnLogout.addActionListener(this::logout);
        
        buttonPanel.add(btnAddUser);
        buttonPanel.add(btnEditUser);
        buttonPanel.add(btnDeleteUser);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnLogout);


        // Add components to main panel
        panel.add(new JLabel("Danh sách người dùng", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        loadUsers();
        return panel;
    }

    private void loadUsers() {
        userTableModel.setRowCount(0); // Clear existing data
        List<User> users = userBLL.getAllUsers();
        for (User user : users) {
            userTableModel.addRow(new Object[]{
                user.getId(),
                user.getUsername(),
                user.getRole()
            });
        }
    }

    private void showAddUserDialog(ActionEvent e) {
        JTextField txtUsername = new JTextField();
        JPasswordField txtPassword = new JPasswordField();
        JComboBox<User.Role> roleComboBox = new JComboBox<>(User.Role.values());

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Tên người dùng:"));
        panel.add(txtUsername);
        panel.add(new JLabel("Mật khẩu:"));
        panel.add(txtPassword);
        panel.add(new JLabel("Vai trò:"));
        panel.add(roleComboBox);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Thêm người dùng mới", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());
            User.Role role = (User.Role) roleComboBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Vui lòng nhập đầy đủ thông tin!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User newUser = new User(0, username, password, role);
            if (userBLL.addUser(newUser)) {
                JOptionPane.showMessageDialog(this, 
                    "Thêm người dùng thành công!", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Thêm người dùng thất bại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showEditUserDialog(ActionEvent e) {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn một người dùng để sửa!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int userId = (int) userTableModel.getValueAt(selectedRow, 0);
        String username = (String) userTableModel.getValueAt(selectedRow, 1);
        User.Role role = (User.Role) userTableModel.getValueAt(selectedRow, 2);

        JTextField txtUsername = new JTextField(username);
        JTextField txtPassword = new JTextField();
        JComboBox<User.Role> roleComboBox = new JComboBox<>(User.Role.values());
        roleComboBox.setSelectedItem(role);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Tên người dùng:"));
        panel.add(txtUsername);
        panel.add(new JLabel("Mật khẩu (để trống nếu không thay đổi):"));
        panel.add(txtPassword);
        panel.add(new JLabel("Vai trò:"));
        panel.add(roleComboBox);

        int result = JOptionPane.showConfirmDialog(
            this, panel, "Sửa thông tin người dùng", 
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String newUsername = txtUsername.getText().trim();
            String newPassword = txtPassword.getText().trim();
            User.Role newRole = (User.Role) roleComboBox.getSelectedItem();

            if (newUsername.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Vui lòng nhập tên người dùng!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User updatedUser = new User(userId, newUsername, newPassword.isEmpty() ? null : newPassword, newRole);
            if (userBLL.updateUser(updatedUser)) {
                JOptionPane.showMessageDialog(this, 
                    "Cập nhật người dùng thành công!", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadUsers();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Cập nhật người dùng thất bại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteUser(ActionEvent e) {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn một người dùng để xóa!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int userId = (int) userTableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Bạn có chắc chắn muốn xóa người dùng này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (userBLL.deleteUser(userId)) {
                JOptionPane.showMessageDialog(this, 
                    "Xóa người dùng thành công!", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadUsers();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Xóa người dùng thất bại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}