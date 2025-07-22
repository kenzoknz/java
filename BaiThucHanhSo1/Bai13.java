package BaiThucHanhSo1;
import java.util.Scanner;
public class Bai13 {

    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int findLCM(int a, int b) {
        return (a * b) / findGCD(a, b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a,b; 
        do {
        System.out.print("Nhập số nguyên dương a: ");
         a = scanner.nextInt();
        System.out.print("Nhập số nguyên dương b: ");
         b = scanner.nextInt();
        } while (a<=0 && b<=0);
            int gcd = findGCD(a, b);
            int lcm = findLCM(a, b);

            System.out.println("Ước chung lớn nhất (UCLN) của " + a + " và " + b + " là: " + gcd);
            System.out.println("Bội chung nhỏ nhất (BCNN) của " + a + " và " + b + " là: " + lcm);
        

        scanner.close();
    }

}
