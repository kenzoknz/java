package BaiThucHanhSo1;
import java.util.Scanner;
class Bai4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(System.in);
		 //Giai he pt 2 an bang pp Kramer
		 System.out.println("Giai He pt 2 an");
		 
	        System.out.print("Nhap a1, b1, c1 cho phuong trinh a1x+b1y=c1:");
	        double a1 = scanner.nextDouble();
	        double b1 = scanner.nextDouble();
	        double c1 = scanner.nextDouble();

	        System.out.print("Nhap a2, b2, c2 a2x+b2y=c2:");
	        double a2 = scanner.nextDouble();
	        double b2 = scanner.nextDouble();
	        double c2 = scanner.nextDouble();

	        // Tinh dinh thuc
	        System.out.println("He phuong trinh vua nhap:");
	        System.out.println(a1 + "x " + ((b1<0)? b1 : ( "+" + b1)) + "y = " + c1 );
	        System.out.println(a2 + "x " + ((b2<0)? b2 : ( "+" + b2)) + "y = " + c2 );
	        //Su dung phuong phap Cramer de giai he pt
	        double D  = a1 * b2 - a2 * b1;
	        double Dx = c1 * b2 - c2 * b1;
	        double Dy = a1 * c2 - a2 * c1;

	        // Check
	        if (D == 0) {
	            if (Dx == 0 && Dy == 0) {
	                System.out.println("Infinite solution.");
	            } else {
	                System.out.println("No solution.");
	            }
	        } else {
	            double x = Dx / D;
	            double y = Dy / D;
	            System.out.print("The solution is:");
	            System.out.printf("x = %.2f, y = %.2f\n", x, y);
	        }

	        scanner.close();
	}

}
