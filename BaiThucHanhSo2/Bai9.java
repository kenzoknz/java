package BaiThucHanhSo2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Bai9 extends JFrame implements MouseListener {

    public Bai9(String title) {
        super(title);
        setBounds(100, 100, 300, 200);
       
        requestFocus();
        addMouseListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            System.out.println("You right clicked the mouse at " + e.getX() + ", " + e.getY());
        } else {
            System.out.println("You left clicked the mouse at " + e.getX() + ", " + e.getY());
        }
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("You pressed the mouse at " + e.getX() + ", " + e.getY());
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("You released the mouse at " + e.getX() + ", " + e.getY());
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("You enter the window at " + e.getX() + ", " + e.getY());
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("You exit the window at " + e.getX() + ", " + e.getY());
    }

    public static void main(String args[]) {
        new Bai9("MouseEventTest");
       
    }
}