package BaiThucHanhSo2;

import java.awt.EventQueue;
import java.awt.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bai4 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField input;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bai4 frame = new Bai4();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 private String swapCase(String input) {
	        if (input == null || input.isEmpty()) {
	            return input;
	        }
	        
	        char[] chars = input.toCharArray();
	        for (int i = 0; i < chars.length; i++) {
	            char c = chars[i];
	            if (Character.isUpperCase(c)) {
	                chars[i] = Character.toLowerCase(c);
	            } else if (Character.isLowerCase(c)) {
	                chars[i] = Character.toUpperCase(c);
	            }
	            
	        }
	        return new String(chars);
	    }
	/**
	 * Create the frame.
	 */
	public Bai4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 324);
		contentPane = new JPanel(new GridLayout(2,1));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel(new GridLayout(5,2));
		JPanel panel2 = new JPanel(new FlowLayout());
		contentPane.add(panel);
		contentPane.add(panel2);
		
		JButton btnOK = new JButton("OK");

		panel2.add(btnOK);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String S = input.getText();
				textField_1.setText(S.toUpperCase());
				textField_2.setText(S.toLowerCase());
				textField_3.setText(swapCase(S));
				StringTokenizer st = new StringTokenizer (S, " ", false);
				int count = 0;
				while ( st. hasMoreTokens ()) {count++; st.nextToken();}
				textField_4.setText(Integer.toString(count));
			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				input.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		panel2.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel2.add(btnExit);
		
		
		JLabel lblString = new JLabel("Enter a String");
		panel.add(lblString);
		
		input = new JTextField();
		panel.add(input);
		input.setColumns(10);
		
		JLabel lbtoUpper = new JLabel("To Uppercase");
		panel.add(lbtoUpper);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lntoLower = new JLabel("To Lowercase");
		panel.add(lntoLower);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lbtoLowerUpper = new JLabel("To LowerUpper");
		panel.add(lbtoLowerUpper);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lbwordCount = new JLabel("Word count: ");
		panel.add(lbwordCount);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		panel.add(textField_4);
		textField_4.setColumns(10);

		
	}

}
