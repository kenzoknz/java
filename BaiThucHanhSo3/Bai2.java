package BaiThucHanhSo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bai2 {
	public static int nhap() throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		String s = br.readLine();
		return Integer.parseInt(s);
	}
	public static int reverse(int m) {
		String result = Integer.toString(m);
		String reversed = "";
		 for (int i = result.length() - 1; i >= 0; i--) {
	            reversed += result.charAt(i);
	        }
		return Integer.parseInt(reversed);
	}
	public static boolean fibo(int n) {
	    int f1 = 1, f2 = 1, f3;
	    if (n == 0 || n == 1) return true; 

	    while (f2 <= n) {
	        if (f2 == n) return true; 
	        f3 = f1 + f2;
	        f1 = f2;
	        f2 = f3;
	    }

	    return false;
	}
	public static int tinh(int m) {
	    int S = 0;
	    while (m > 0) {
	        S += m % 10;
	        m = m / 10;
	    }
	    return S;
	}
	public static boolean isSymmetry(int m) {
		int ex = reverse(m);
		return m==ex;
	}
	public static void main(String args[]) {
		
		int m = 0;
		
		do {
		    try {
		        System.out.print("Nhập số nguyên dương m: ");
		        m = nhap();
		        if (m > 0) break;
		    } catch (Exception e) {
		        System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập lại số nguyên dương!");
		    }
		} while (true);

		System.out.println("Tổng các chữ số: " + tinh(m));
		System.out.println("Số đảo ngược là: " + reverse(m));
		System.out.println(m + ((fibo(m)==true)? " " : " không ") + "là số Fibonacci");
		System.out.println(m + ((isSymmetry(m)==true)? " " : " không ") + "là số đối xứng");
	}
	 
}
