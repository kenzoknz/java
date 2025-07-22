package BaiThucHanhSo1;

public class Bai9 {
	
	public static void main(String[] args) {

        System.out.println(forSum(4));
        System.out.println(whileSum(4));
	}
	
    public static double forSum(int n) {
        double sum = 15.0;

        for (int i = 1; i <= n; i++) 
            sum += Math.pow(-1, i) * (1.0 / factorial(i));

        return sum;
    }
    public static double whileSum(int n) {
        double sum = 15.0;
        int i = 0;
        while (++i <= n) sum += Math.pow(-1, i) * (1.0 / factorial(i));
        return sum;
    }
    public static long factorial(int num) {
        long fact = 1;
        for (int i = 2; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }
}
