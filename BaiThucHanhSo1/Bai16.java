package BaiThucHanhSo1;
import java.util.Scanner;

public class Bai16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập M: ");
        int M = scanner.nextInt();
        int original = M, reversed = 0;

        while (M > 0) {
            reversed = reversed * 10 + M % 10;
            M /= 10;
        }

        System.out.println(original + (original == reversed ? " là số đối xứng." : " không là số đối xứng."));
        scanner.close();
    }
}