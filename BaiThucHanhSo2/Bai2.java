package BaiThucHanhSo2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Bai2 extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textFieldResult;
    
    private JButton btnAdd;
    private JButton btnSubtract;
    private JButton btnMul;
    private JButton btnDivide;
    private JButton btnReset;
    private JButton btnExit;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Bai2 frame = new Bai2("Các phép toán cơ bản");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 546, 416);
        contentPane = new JPanel(new GridLayout(4,1));
        
        textField1 = new JTextField();
        textField2 = new JTextField();
        textFieldResult = new JTextField();
        textFieldResult.setEditable(false);
        JPanel panelTitle = new JPanel(new FlowLayout());
        JLabel labelTitle = new JLabel("Basic Arithmetic Operations");
        panelTitle.add(labelTitle);
        contentPane.add(panelTitle);
        
        JPanel panelInput = new JPanel(new GridLayout(3,2));
        panelInput.add(new JLabel("Number 1:"));
        panelInput.add(textField1);
        panelInput.add(new JLabel("Number 2:"));
        panelInput.add(textField2);
        panelInput.add(new JLabel("Result:"));
        panelInput.add(textFieldResult);
        contentPane.add(panelInput);
        
        JPanel panelButton = new JPanel(new FlowLayout());
        btnAdd = new JButton("Addition");
        btnSubtract = new JButton("Subtraction");
        btnMul = new JButton("Multiplication");
        btnDivide = new JButton("Division");
        
        panelButton.add(btnAdd);
        panelButton.add(btnSubtract);
        panelButton.add(btnMul);
        panelButton.add(btnDivide);
        contentPane.add(panelButton);
        
        JPanel panelButton2 = new JPanel(new FlowLayout());
        btnReset = new JButton("Reset");
        btnExit = new JButton("Exit");
        
        panelButton2.add(btnReset);
        panelButton2.add(btnExit);
        contentPane.add(panelButton2);
        
        //  action listener
        btnAdd.addActionListener(this);
        btnSubtract.addActionListener(this);
        btnMul.addActionListener(this);
        btnDivide.addActionListener(this);
        btnReset.addActionListener(this);
        btnExit.addActionListener(this);
        
        setContentPane(contentPane);
    }

    public Bai2(String st) {
        super(st);
        GUI();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(textField1.getText());
            double num2 = Double.parseDouble(textField2.getText());
            double result = 0;
            
            if (e.getSource() == btnAdd) {
                result = num1 + num2;
            } else if (e.getSource() == btnSubtract) {
                result = num1 - num2;
            } else if (e.getSource() == btnMul) {
                result = num1 * num2;
            } else if (e.getSource() == btnDivide) {
               if (num2==0) throw ( new ArithmeticException());
                result = num1 / num2;
            } else if (e.getSource() == btnReset) {
                textField1.setText("");
                textField2.setText("");
                textFieldResult.setText("");
                return;
            } else if (e.getSource() == btnExit) System.exit(0);
            
            textFieldResult.setText(String.format("%.2f", result));
            
        } 
        catch (ArithmeticException ex) {
        	textFieldResult.setText("Cannot divide by Zero");
        }
        catch (NumberFormatException ex) {
            textFieldResult.setText("Invalid input");
        }
    }
}