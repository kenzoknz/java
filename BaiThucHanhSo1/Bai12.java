package BaiThucHanhSo1;

public class Bai12 {

    public static int sumOfDigits(int m) {
        int S = 0;
        while (m > 0) {
            S += m % 10; 
            m /= 10;       
        }
        return S;
    }

    public static int productOfDigits(int m) {
        int P = 1;
        while (m > 0) {
            P *= m % 10; 
            m /= 10;          
        }
        return P;
    }
	public static void main(String[] args) {
		int m = 1234567;
        int sum = sumOfDigits(m);
        int product = productOfDigits(m);

        // In kết quả
        System.out.println("Tổng các chữ số của " + m + " là: " + sum);
        System.out.println("Tích các chữ số của " + m + " là: " + product);

	}

}
