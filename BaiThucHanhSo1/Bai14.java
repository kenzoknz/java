package BaiThucHanhSo1;

class Bai14 {
	public static boolean isPrime (int n) { //ham for
		if (n<=2) return false;
		for (int i = 2; i<= Math.sqrt(n); i++)
			if (n%i==0) return false;
		return true;
	}
	public static boolean isPrimeW(int n) {//ham while
		if (n<=2) return false;
		int i = 2;
		while (i++<=Math.sqrt(n)) 
			if (n%i==0) return false;
		return true;
	}
	public static void main(String[] args) {
		int number = 997;
		System.out.println("Number: " + number);
		
		System.out.print("for...:");
		if (isPrime(number)) System.out.println("True");
		else System.out.println("False");
		System.out.print("While do...:");
		if (isPrimeW(number)) System.out.println("True");
		else System.out.println("False");
	}

}
