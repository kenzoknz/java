package BaiThucHanhSo1;
import java.util.*;

class Bai7 {
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) return true;
		return false;
	}
	public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);

	        System.out.print("Year: ");
	        int year = scanner.nextInt();
	        System.out.print("Month: ");
	        int month = scanner.nextInt();
	        scanner.close();

	        int days = 0;
	        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                days = 31;
                break;
            case 4: case 6: case 9: case 11:
                days = 30;
                break;
            case 2:
                if (isLeapYear(year)) days = 29;
                else 				  days = 28;
                break;
            default:
                System.out.println("Invaild month.");
                return;
        }
	        System.out.println("Thang " + month + " nam " + year + " co " + days + " ngay");
	   

	}

}
