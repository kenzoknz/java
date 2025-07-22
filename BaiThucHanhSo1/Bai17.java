package BaiThucHanhSo1;
import java.util.Scanner;
class Bai17 {
	   public static boolean isPrime(int num) {
	        if (num < 2) return false;
	        for (int i = 2; i <= Math.sqrt(num); i++) {
	            if (num % i == 0) return false;
	        }
	        return true;
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Nhập n: ");
	        int n = scanner.nextInt();

	        for (int i = 2; i <= n; i++) {
	            if (isPrime(i)) {
	                System.out.println(i);
	            }
	        }

	        scanner.close();
	    }


}
