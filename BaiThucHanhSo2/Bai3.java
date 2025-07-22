package BaiThucHanhSo2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai3 extends JFrame implements ActionListener {
    
    private JPanel colorPanel;
    private JButton redButton, greenButton, blueButton, exitButton;
    
    public Bai3() {
        setTitle("Color Changer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        colorPanel = new JPanel();
        colorPanel.setBackground(Color.WHITE);
        add(colorPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        
        redButton = new JButton("RED");
        greenButton = new JButton("GREEN");
        blueButton = new JButton("BLUE");
        exitButton = new JButton("Thoat");
        
        redButton.addActionListener(this);
        greenButton.addActionListener(this);
        blueButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        buttonPanel.add(redButton);
        buttonPanel.add(greenButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == redButton) {
            colorPanel.setBackground(Color.RED);
        } else if (e.getSource() == greenButton) {
            colorPanel.setBackground(Color.GREEN);
        } else if (e.getSource() == blueButton) {
            colorPanel.setBackground(Color.BLUE);
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        
            Bai3 app = new Bai3();
            app.setVisible(true);
      
    }
}