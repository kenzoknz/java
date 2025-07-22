package BaiThucHanhSo1;

class Bai19 {

	public static void main(String[] args) {
		printFibonacci(20);

	}
    public static void printFibonacci(int n) {
        if (n <= 0) return;

        int a = 1, b = 1;
        System.out.print(a + " ");
        if (n > 1) System.out.print(b + " ");

        for (int i = 3; i <= n; i++) {
            int next = a + b;
            System.out.print(next + " ");
            a = b;
            b = next;
        }
        System.out.println();
    }
}
