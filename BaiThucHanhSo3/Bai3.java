package BaiThucHanhSo3;
import java.io.*;
import java.util.*;

public class Bai3 {
    private int[] a;
    private int n;

    public static void main(String[] args) {
        Bai3 program = new Bai3();
        program.run();
    }

    public void run() {
        nhapMang();
        tinhTongDuongLe();
        timPhanTuK();
        sapXepTangDan();
        chenPhanTuP();
    }

    private void nhapMang() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Nhập số phần tử của mảng (n): ");
            n = Integer.parseInt(reader.readLine());
            a = new int[n];

            System.out.println("Nhập các phần tử của mảng:");
            StringTokenizer st = new StringTokenizer(reader.readLine());
            
            for (int i = 0; i < n; i++) {
                if (st.hasMoreTokens()) {
                    a[i] = Integer.parseInt(st.nextToken());
                } else {
                    st = new StringTokenizer(reader.readLine());
                    i--;
                }
            }
            
            System.out.print("Mảng vừa nhập: ");
            xuatMang();
        } catch (IOException e) {
            System.out.println("Lỗi nhập xuất: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số: " + e.getMessage());
        }
    }

    private void tinhTongDuongLe() {
        int tong = 0;
        for (int num : a) {
            if (num > 0 && num % 2 != 0) {
                tong += num;
            }
        }
        System.out.println("Tổng các số dương lẻ trong mảng: " + tong);
    }

    private void timPhanTuK() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Nhập giá trị k cần tìm: ");
            int k = Integer.parseInt(reader.readLine());
            
            int viTri = -1;
            for (int i = 0; i < n; i++) {
                if (a[i] == k) {
                    viTri = i;
                    break;
                }
            }
            
            if (viTri != -1) {
                System.out.println("Phần tử " + k + " xuất hiện đầu tiên tại vị trí: " + viTri);
            } else {
                System.out.println("Phần tử " + k + " không xuất hiện trong mảng.");
            }
        } catch (IOException e) {
            System.out.println("Lỗi nhập xuất: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số: " + e.getMessage());
        }
    }

    private void sapXepTangDan() {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.print("Mảng sau khi sắp xếp tăng dần: ");
        xuatMang();
    }

    private void chenPhanTuP() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Nhập giá trị p cần chèn: ");
            int p = Integer.parseInt(reader.readLine());

            int[] newArray = new int[n + 1];

            int pos = 0;
            while (pos < n && a[pos] < p) {
                pos++;
            }

            for (int i = 0; i < pos; i++) {
                newArray[i] = a[i];
            }

            newArray[pos] = p;

            for (int i = pos; i < n; i++) {
                newArray[i + 1] = a[i];
            }

            a = newArray;
            n++;
            
            System.out.print("Mảng sau khi chèn " + p + ": ");
            xuatMang();
        } catch (IOException e) {
            System.out.println("Lỗi nhập xuất: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số: " + e.getMessage());
        }
    }

    private void xuatMang() {
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}