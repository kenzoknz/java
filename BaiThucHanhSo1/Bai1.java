package BaiThucHanhSo1;

import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhap a: ");
        int a = scanner.nextInt();
        System.out.print("Nhap b: ");
        int b = scanner.nextInt();
        System.out.print("Nhap c: ");
        int c = scanner.nextInt();
        scanner.close();
        int max = a;
        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }
        System.out.println("Max (dung if): " + max);
        //if else
        if (a >= b && a >= c)  		  max = a;
        else if (b >= a && b >= c)    max = b;
        else 						  max = c;
        
        System.out.println("Max (dung if...else): " + max);

        // ?:
        max = (a > b) ? ((a > c) ? a : c) : ((b > c) ? b : c);
        System.out.println("Max (dung toan tu ?:): " + max);
        
        
        
    }
}
