package BaiThucHanhSo2;

import java.awt.*;
import java.awt.event.*;

public class Bai1 extends Frame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Label lb = new Label("GIAI PHUONG TRINH BAC NHAT"),
			lb1= new Label("Nhap a: "),
			lb2 = new Label("Nhap b: "),
			lb3 = new Label("Ket qua: ");
	TextField txta = new TextField(),txtb= new TextField(),txtkq= new TextField();
	Button tinh = new Button("Tinh"), 
			reset = new Button("Reset"), 
			thoat = new Button("Thoat");
	Panel pn	= new Panel(new GridLayout(3,1)),
		  pn1	= new Panel(new FlowLayout()),
		  pn2	= new Panel(new GridLayout(3,2)),
		  pn3 	= new Panel(new GridLayout(1,3));
	
	
	public void GUI() {
		tinh.addActionListener(this);
		reset.addActionListener(this);
		thoat.addActionListener(this);
		
		pn1.add(lb);
		
		pn2.add(lb1);
		pn2.add(txta);
		pn2.add(lb2);
		pn2.add(txtb);
		pn2.add(lb3);
		pn2.add(txtkq);
		
		pn3.add(tinh);pn3.add(reset);pn3.add(thoat);
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);
		add(pn);
		setSize(400,400);
		setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); 
            }
        });
	}
	public Bai1() {
		super("Giải phương trình bậc nhất");
		GUI();
	}
	void solve() {
		try {
			 double a = Double.parseDouble(txta.getText());
	         double b = Double.parseDouble(txtb.getText());
	            
	        String result;
	        if (a == 0) {
                if (b == 0) {
                    result = "Phương trình có vô số nghiệm";
                } else {
                    result = "Phương trình vô nghiệm";
                }
            } else {
                double x = -b / a;
                result =  "" + x;
                
            }
	        txtkq.setText(result);
            
		}
		catch (NumberFormatException ex) {
			txtkq.setText("Vui lòng nhập số hợp lệ!");
		}
		catch (Exception e) {
			txtkq.setText("Lỗi không xác định");
		}
	}
	public static void main(String[] args) {
		new Bai1();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==tinh) {
			solve();
		}
		else if (e.getSource()==reset ) {
			txta.setText("");
			txtb.setText("");
			txtkq.setText("");
		}
		else System.exit(0);
		
	}

}
