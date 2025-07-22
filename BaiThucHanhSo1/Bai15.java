package BaiThucHanhSo1;

class Bai15 {
	public static void check(int P) {
        int sqrt = (int) Math.sqrt(P);
        if (sqrt * sqrt == P) {
            System.out.println(P + " là số chính phương.");
        } else {
            System.out.println(P + " không là số chính phương.");
        }

	}
    public static void main(String[] args) {
        
        int P = 15;
        check(P);
    }

}
