package BaiThucHanhSo1;
import java.util.Scanner;
class Bai3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Giai phuong trinh bac 2 ax^2+bx+c-0");
		double a,b,c;
		while (true) {
		System.out.print("Nhap lan luot a, b, c (a!=0): ");
		
		 a = scanner.nextDouble();
		 b = scanner.nextDouble();
		 c = scanner.nextDouble();
		if (a!=0) break;
		else System.out.println("a phai khac 0! Vui long nhap lai");
		
		}
		
		scanner.close();
		double delta = b*b -4*a*c;
		
		if (delta < 0) System.out.println("Phuong trinh khong co nghiem thuc");
		if (delta == 0) {
			double x = -b/2*a;
			System.out.println("Phuong trinh co nghiem kep x = " + x);
		}
		if (delta > 0) {
			double x1 = (-b+Math.sqrt(delta))/2*a;
			double x2 = (-b-Math.sqrt(delta))/2*a;
			System.out.println("Phuong trinh co 2 nghiem phan biet x1 = " + x1 + ", x2 = " + x2);
			
		}
	}

}
