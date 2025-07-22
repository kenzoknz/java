package BaiThucHanhSo1;

public class Bai18 {
    public static boolean isPerfect(int num) {
        int sum = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) sum += i;
        }
        return sum == num;
    }
    public static void forPerfect() {
        for (int i = 1; i < 1000; i++) {
            if (isPerfect(i)) {
                System.out.print(i + " ");
            }
        }
    }
    public static void whilePerfect() {
    	int i=1;
       while (i++<1000) {
            if (isPerfect(i)) {
                System.out.print(i + " ");
            }
        }
    }
    public static void main(String[] args) {
    	forPerfect();
    	System.out.println();
    	whilePerfect();
    }


}
