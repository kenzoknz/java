/**
 * 
 */
package BaiThucHanhSo1;
import java.util.Scanner;

public class Bai11 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   Scanner scanner = new Scanner(System.in);
		   int n;
		   do {
	        System.out.print("Nhập số nguyên n: ");
	        n = scanner.nextInt();
		   } while (n<0);

	            long resultFor = doubleFactorialFor(n);
	            long resultWhile = doubleFactorialWhile(n);
	            long resultDoWhile = doubleFactorialDoWhile(n);
	            long resultRecursion = doublefactorial(n);

	            System.out.println("Sử dụng vòng lặp for: " + n + "!! = " + resultFor);
	            System.out.println("Sử dụng vòng lặp while: " + n + "!! = " + resultWhile);
	            System.out.println("Sử dụng vòng lặp do-while: " + n + "!! = " + resultDoWhile);
	            System.out.println("Sử dụng đệ quy: " + n + "!! = " + resultRecursion);
	            System.out.println("Sử dụng break continue: " + n + "!! = " + DBFbreak(n));

	        scanner.close();

	}
    public static long doublefactorial(long n) //recursion
    {
        if (n == 0 || n==1)
            return 1;
             
        return n * doublefactorial(n - 2);
    }
    public static long doubleFactorialFor(int n) {
        long result = 1;
        if (n % 2 == 1) {
            //  n lẻ
            for (int i = 1; i <= n; i += 2) 
                result *= i;
           
        } 
        else {
            //  n chẵn
            for (int i = 2; i <= n; i += 2) 
                result *= i;
            
        }
        return result;
    }

    public static long doubleFactorialWhile(int n) {
        long result = 1;
        int i;
        if (n % 2 == 1) {
            i = 1;
            while (i <= n) {
                result *= i;
                i += 2;
            }
        } else {

            i = 2;
            while (i <= n) {
                result *= i;
                i += 2;
            }
        }
        return result;
    }

    public static long doubleFactorialDoWhile(int n) {
        long result = 1;
        int i;
        if (n % 2 == 1) {
            i = 1;
            do {
                result *= i;
                i += 2;
            } while (i <= n);
        } else {
            i = 2;
            do {
                result *= i;
                i += 2;
            } while (i <= n);
        }
        return result;
    }
    public static long DBFbreak(int n) {
    	long result = 1;
    	int i = 1;
    	switch (n%2) {
    	case 0: // n chẵn
    		while (i++<=n) if (i%2!=0) continue;
    		else result *= i;
    		break;
    	case 1: // n lẻ
    		while (i++<=n) if (i%2==0) continue;
    		else result *= i;
    	}
    	return result;
    }
}
