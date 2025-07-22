package BaiThucHanhSo2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Bai10 extends JFrame {
    Bai10(String title) {
        super(title);
        setBounds(300, 200, 200, 200);
        
        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        
        Menu fileMenu = new Menu("File");
        
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem exitItem = new MenuItem("Exit");
        
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    Bai10.this, 
                    "Bạn có chắc chắn muốn thoát?",
                    "Xác nhận thoát",
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0); 
                }
            }
        });
        
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(exitItem);
        mb.add(fileMenu);
        
        // Edit menu
        Menu editMenu = new Menu("Edit");
        editMenu.add(new MenuItem("Copy"));
        editMenu.add(new MenuItem("Cut"));
        editMenu.add(new MenuItem("Paste"));
        
        // Submenu
        Menu subMenu = new Menu("Option");
        subMenu.add(new MenuItem("First"));
        subMenu.add(new MenuItem("Second"));
        subMenu.add(new MenuItem("Third"));
        editMenu.add(subMenu);
        
        editMenu.add(new CheckboxMenuItem("Protected"));
        mb.add(editMenu);
        
        setVisible(true);
    }

    public static void main(String args[]) {
        new Bai10("Menu Demo");
    }
}