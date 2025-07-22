package BaiThucHanhSo1;

public class Bai10 {
	
	public static void main(String[] args) {

        System.out.println(forSum(5));
        System.out.println(whileSum(5));
        System.out.println(dowhileSum(5));
	}
	
    public static double forSum(int n) {
        double sum = 0;

        for (int i = 1; i <= n; i++) 
            sum += (1.0 / factorial(2*i-1));

        return sum;
    }
    public static double whileSum(int n) {
        double sum = 0;
        int i = 0;
        while (++i <= n) sum +=  (1.0 / factorial(2*i-1));
        return sum;
    }
    public static double dowhileSum(int n) {
        double sum = 0;
        int i = 0;
        do sum +=  (1.0 / factorial(2*(++i)-1)); while(i<=n);
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
