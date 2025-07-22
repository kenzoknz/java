package BaiThucHanhSo3;
import java.io.*;
import java.util.*;

public class bai4 {
    private int[][] a;
    private int m, n;
    private int[] X;


    private void nhapMaTran() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Nhập số dòng của ma trận (m): ");
            m = Integer.parseInt(reader.readLine());
            System.out.print("Nhập số cột của ma trận (n): ");
            n = Integer.parseInt(reader.readLine());
            
            a = new int[m][n];
            
            System.out.println("Nhập các phần tử của ma trận (mỗi dòng cách nhau bằng khoảng trắng):");
            for (int i = 0; i < m; i++) {
                System.out.print("Nhập dòng thứ " + (i + 1) + ": ");
                StringTokenizer st = new StringTokenizer(reader.readLine());
                for (int j = 0; j < n; j++) {
                    if (st.hasMoreTokens()) {
                        a[i][j] = Integer.parseInt(st.nextToken());
                    } else {
                        // Nếu không đủ token thì nhập tiếp
          
                        st = new StringTokenizer(reader.readLine());
                        j = -1; 
                    }
                }
            }
            
            System.out.println("Ma trận vừa nhập:");
            xuatMaTran();
        } catch (IOException e) {
            System.out.println("Lỗi nhập xuất: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số: " + e.getMessage());
        }
    }

    private void tinhTichBoi3DongDau() {
        int tich = 1;
        boolean coBoi3 = false;
        
        for (int j = 0; j < n; j++) {
            if (a[0][j] % 3 == 0) {
                tich *= a[0][j];
                coBoi3 = true;
            }
        }
        
        if (coBoi3) {
            System.out.println("Tích các số bội 3 trên dòng đầu tiên: " + tich);
        } else {
            System.out.println("Không có số bội 3 nào trên dòng đầu tiên");
        }
    }

    private void taoMangX() {
        X = new int[m];
        
        for (int i = 0; i < m; i++) {
            int max = a[i][0];
            for (int j = 1; j < n; j++) {
                if (a[i][j] > max) {
                    max = a[i][j];
                }
            }
            X[i] = max;
        }
        
        System.out.print("Mảng X (giá trị lớn nhất từng dòng): ");
        xuatMangX();
    }

    private void xoaPhanTuDauX() {
        if (m == 0) {
            System.out.println("Mảng X rỗng, không thể xóa");
            return;
        }
        
        int[] newX = new int[m - 1];
        
        for (int i = 1; i < m; i++) {
            newX[i - 1] = X[i];
        }
        
        X = newX;
        m--;
        
        System.out.print("Mảng X sau khi xóa phần tử đầu: ");
        xuatMangX();
    }

    private void xuatMaTran() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void xuatMangX() {
        for (int i = 0; i < m; i++) {
            System.out.print(X[i] + " ");
        }
        System.out.println();
    }



    public void run() {
        nhapMaTran();
        tinhTichBoi3DongDau();
        taoMangX();
        xoaPhanTuDauX();
    }
    public static void main(String[] args) {
        bai4 program = new bai4();
        program.run();
    }
}
