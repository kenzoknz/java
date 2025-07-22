package BaiThucHanhSo1;
import java.util.Scanner;

class Bai2 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Giai phuong trinh bac nhat ax + b = 0");
		System.out.print("Nhap a:");
		double a = scanner.nextDouble();
		System.out.print("Nhap b:");
		double b = scanner.nextDouble();

		double x ;
		System.out.println("Ket qua dung if else:");
		if (a==0) {
			if (b==0) 
				System.out.println("Phuong trinh co vo so nghiem");
			else System.out.println("Phuong trinh vo nghiem");
		}
		else  {
			 x = -b/a;
			 System.out.println("Phuong trinh co nghiem x = " + x);
		}
		
		System.out.println("Ket qua dung toan tu ?: :");
		String ketqua = (a==0) 
				? ((b==0) ? "Phuong trinh vo so nghiem" : "Phuong trinh vo nghiem") : ("Phuong trinh co nghiem x = " + (-b/a));
		System.out.println(ketqua);

		
		scanner.close();
		
	}
	
		
}
