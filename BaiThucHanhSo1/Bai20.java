package BaiThucHanhSo1;

class Bai20 {

	public static void main(String[] args) {
		int K = 34;
        int position = find(K);
        if (position != -1) {
            System.out.println(K + " thuoc day thu " + position);
        } else {
            System.out.println(K + " k thuoc Fibonacci.");
        }

	}
	 public static int find(int K) {
	        if (K < 1) return -1;

	        int a = 1, b = 1, index = 2;
	        if (K == 1) return 1; 

	        while (b < K) {
	            int next = a + b;
	            a = b;
	            b = next;
	            index++;
	        }

	        return (b == K) ? index : -1;
	    }
}
