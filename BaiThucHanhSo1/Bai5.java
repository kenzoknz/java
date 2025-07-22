package BaiThucHanhSo1;
import java.util.Scanner;

class Bai5 {

	public static int trungGianIf(int a, int b, int c) {
        if ((a > b && a < c) || (a > c && a < b)) return a;
        if ((b > a && b < c) || (b > c && b < a)) return b;
        return c;
    }
	 public static int trungGian(int a, int b, int c) {
	        return (a > b) ? ((a < c) ? a : (b > c) ? b : c) 
	                       : ((b < c) ? b : (a > c) ? a : c);
	    }
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a,b,c;
		System.out.print("Nhap lan luot a,b,c: ");
		a = scanner.nextInt();
		b = scanner.nextInt();
		c = scanner.nextInt();
		int if_result = trungGianIf(a,b,c);
		int tg_result = trungGian(a,b,c);
		System.out.print("Ket qua khi dung 2 cach: ");
		System.out.println(if_result + " " + tg_result);
		scanner.close();

	}

}
