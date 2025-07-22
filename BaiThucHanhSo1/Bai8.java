package BaiThucHanhSo1;

public class Bai8 {
	public static double Tinh(int n) {
		double S = 0;
		for (int i = 1; i <= n; i++) 
			S += 1.0/i;
		return S;
	}
	public static double whileTinh(int n) {
		double S=0;
		int i=1;
		while (i<=n) {
			S += 1.0/i;
			i++;
		}
		return S;
	}
	public static double doTinh(int n) {
		double S=0;
		int i=1;
		do {
			S += 1.0/i;
			i++;
		} while (i<=n);
		return S;
	}
	public static void main(String[] args) {
		System.out.println(Tinh(4));
		System.out.println(whileTinh(4));
		System.out.println(doTinh(4));

	}

}
