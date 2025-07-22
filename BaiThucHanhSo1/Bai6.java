package BaiThucHanhSo1;
import java.util.Scanner;
class Bai6 {

	public static void main(String[] args) {
	      Scanner scanner = new Scanner(System.in);

	        System.out.print("Nhập giờ bắt đầu: ");
	        int a = scanner.nextInt();
	        System.out.print("Nhập giờ kết thúc: ");
	        int b = scanner.nextInt();
	        int gia;
	       if (b<18) gia = 45000;
	       else gia = 60000;
	       int sogio = b-a;
	       int tonggia = sogio*gia;
	       
	            System.out.printf("Tổng số giờ hát: %d giờ\n", sogio);
	            System.out.printf("Tổng số tiền cần thanh toán: %dđ\n", tonggia);
	            scanner.close();
	       
	}

}
