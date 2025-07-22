package BaiThucHanhSo2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Bai6 extends JFrame {

    private JPanel contentPane;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Bai6 frame = new Bai6();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Bai6() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel(new FlowLayout());
        setContentPane(contentPane);

        JButton button = new JButton("OK");
        contentPane.add(button);

        textField = new JTextField();
        contentPane.add(textField);
        textField.setColumns(10);

        final DefaultListModel<String> animalList = new DefaultListModel<>();
        animalList.addElement("Tiger");
        animalList.addElement("Lion");
        animalList.addElement("Elephant");
        animalList.addElement("Cat");
        animalList.addElement("Dog");
        animalList.addElement("Giraffe");
        animalList.addElement("Monkey");
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane);
        
        List list = new List();

        contentPane.add(list);
        list.add("Tiger");
        list.add("Lion");
        list.add("Elephant");
        list.add("Cat");
        list.add("Dog");
        list.add("Giraffe");
        list.add("Monkey");

        JLabel label = new JLabel("The event is displayed here");
        contentPane.add(label);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("You have pressed the button");
            }
        });

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("You ENTER the textfield.");
            }
        });
        list.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		label.setText("You have double-clicked the list");
        	}
        });
    }
}