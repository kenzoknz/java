package BaiThucHanhSo3;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import MultiThreadProgramming.stringBuffer;

public class Bai1 {

	 public static String nhap() throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        System.out.print("Enter a string: ");
	        return br.readLine();
	    }

	    public String reverse(String s) {
	        String reversed = "";
	        for (int i = s.length() - 1; i >= 0; i--) {
	            reversed += s.charAt(i);
	        }

	        return reversed;
	    }
	    public String toUpper(String s) {
	    	String upper = "";
	    	 for (int i = 0; i < s.length(); i++) {
		            char c = s.charAt(i);
		            if (c >= 'a' && c <= 'z') {
		                c = (char)(c - 32);
		            }
		            upper += c;
		        }
	    	 return upper;
	    }
	    public String toLower(String s) {
	    	String lower = "";
	    	 for (int i = 0; i < s.length(); i++) {
		            char c = s.charAt(i);
		            if (c >= 'A' && c <= 'Z') {
		                c = (char)(c + 32);
		            }
		            lower += c;
		        }
	    	 return lower;
	    }
	    public String toUppertoLower(String s) {
	    	String result = "";
	    	 for (int i = 0; i < s.length(); i++) {
		            char c = s.charAt(i);
		            if (c >= 'A' && c <= 'Z') {
		                c = (char)(c + 32);
		            }
		            else c = (char)(c - 32);
		            result += c;
		        }
	    	 return result;
	    }
	    public int count (String s) {
	    	StringTokenizer st = new StringTokenizer (s, " ", false);
			int count = 0;
			while ( st. hasMoreTokens ()) {count++; st.nextToken();}
			return count;
	    }
	    public void wordFrequencyTable(String st1) {
	    	String s = st1.toLowerCase();
	        ArrayList<String> words = new ArrayList<>();
	        ArrayList<Integer> counts = new ArrayList<>();

	        StringTokenizer st = new StringTokenizer(s, " ", false);

	        while (st.hasMoreTokens()) {
	            String word = st.nextToken();

	            int index = words.indexOf(word);

	            if (index != -1) {
	                counts.set(index, counts.get(index) + 1);
	            } else {
	                words.add(word);
	                counts.add(1);
	            }
	        }

	        System.out.println("Bảng tần số xuất hiện của các từ:");
	        for (int i = 0; i < words.size(); i++) {
	            System.out.printf("'%s': %d lần\n", words.get(i), counts.get(i));
	        }
	    }
	    public static void main(String[] args) throws IOException {
	        Bai1 b = new Bai1();
	        String s = nhap();
	        System.out.println("Reverse: " + b.reverse(s));
	        System.out.println("Upper: " + b.toUpper(s));
	        System.out.println("Lower: " + b.toLower(s));
	        System.out.println("To lower and upper: " + b.toUppertoLower(s));
	        System.out.println("word count:" + b.count(s));
	        b.wordFrequencyTable(s);
	    }
	    

}
