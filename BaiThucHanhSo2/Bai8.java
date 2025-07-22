package BaiThucHanhSo2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Bai8 extends Frame implements KeyListener {
    Label label;

    Bai8(String title) {
        super(title);
        setBounds(100, 100, 300, 300);
        label = new Label("A", Label.CENTER);
        add(label);
        requestFocus();
        addKeyListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });
        
    }

    public void keyTyped(KeyEvent e) {
        label.setText(String.valueOf(e.getKeyChar()));
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public static void main(String args[]) {
        Bai8 e = new Bai8("Key Event Test");
        e.setVisible(true);
    }
}