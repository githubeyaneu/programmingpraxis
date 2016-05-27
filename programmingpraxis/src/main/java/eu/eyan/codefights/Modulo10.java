package eu.eyan.codefights;

import java.math.BigInteger;

import scala.math.BigDecimal;

public class Modulo10 {

	public static void main(String[] args) {
		//		String a = "123";
		//		String b = "124";
		//		System.out.println((a.charAt(a.length()-1)-48)*(b.charAt(b.length()-1)-48)%10);
		//		System.out.println(new BigInteger(a).multiply(new BigInteger(b)).mod(new BigInteger("10")));
		//		
		//		for(int i=48; i< 58; i++){
		//			for(int j=48; j< 58; j++){
		//				
		//			System.out.println((char)i+", "+(char)j+", "+i+", "+j+", "+((i-48)*(j-48)%10)+" - "+(i*j)%10+", ");
		//		}}
		for (int j = 48; j < 58; j++) {
			char c = (char) j;
			System.out.println(j+" -> "+ ~-~c+" "+c%10+" "+~-~c%10);
			System.out.println("  j    " + j + " " + Integer.toBinaryString(j));
			System.out.println("  ~j   " + ~j + " " + Integer.toBinaryString(~j));
			System.out.println("  -~j  " + -~j + " "					+ Integer.toBinaryString(-~j));
			System.out.println("  ~-~j " + ~-~j + " "					+ Integer.toBinaryString(~-~j));
		}
		
		for (int j = -100; j < 101; j++) {
//			System.out.println(j+" "+~-~j+ "  "+Integer.toBinaryString(j));
			System.out.println(j+" "+~j+ " "+-~j+" "+~-~j+" "+~-~-~j);
		}

	}
}
