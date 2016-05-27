package eu.eyan.codefights;

import java.util.ArrayList;

import org.apache.commons.collections4.iterators.ArrayListIterator;

import scala.actors.threadpool.Arrays;

public class Order_ {

	String order(int[] a) {
		int i = 1, p = 0, m = 0;
		for (; i < a.length; i++)
			if (a[i] - a[i - 1] < 0)
				m++;
			else
				p++;
		return p * m > 0 ? "not sorted" : (p > 0 ? "asc" : "desc") + "ending";
	}

	//	String order(int[] a) {
	//		int i = 1, s = 0;
	//		for (; i < a.length; i++) {
	//			if ((a[i] - a[i - 1]) * s < 0)
	//				return "not sorted";
	//			s += a[i] - a[i - 1];
	//		}
	//		return s < 0 ? "descending" : "ascending";
	//	}

	//	String order(int[] a) {
	//	    int i=1, s = 0;
	//	    for (;i<a.length;i++){
	//	        if(a[i]>a[i-1] && s<0
	//	            || s>0 && a[i]<a[i-1]
	//	          ) return "not sorted";
	//	        s+=a[i]-a[i-1];
	//	    }
	//	    return s<0 ? "descending" : "ascending";
	//	}

	//	String order(int[] a) {
	//		
	//	    int i=1, s = 0;
	//	    for (;i<a.length;i++){
	//	        if(a[i]>a[i-1] && s<0
	//	            || s>0 && a[i]<a[i-1]
	//	          ) return "not sorted";
	//	        s+=a[i]-a[i-1];
	//	    }
	//	    return s<0 ? "descending" : s>0 ? "ascending" : "not sorted";
	//	}

	//	String order(int[] a) {
	//		
	//	    int i=2, s = a[0]<a[1] ? 1 : a[0]>a[1] ? -1 : 0;
	//	    for (;i<a.length;i++){
	//	        if(
	//	            s==-1 && a[i]>a[i-1]
	//	            || s==1 && a[i]<a[i-1]
	//	          ) return "not sorted";
	//	    }
	//	    return s==-1 ? "descending" : s==1 ? "ascending" : "not sorted";
	//	}

	public static void main(String[] args) {
		int n = 3;
		System.out.println("    n: "+ Integer.toBinaryString(n) +" "+n );
		System.out.println("   -n: "+ Integer.toBinaryString(-n) +" "+ -n);
		System.out.println("  ~-n: "+ Integer.toBinaryString(~-n) +" "+~-n );
		System.out.println("n*~-n: "+ Integer.toBinaryString(n*~-n) +" "+n*~-n);
		System.out.println("n*~-n: "+ n*~-n );
		
		
	}
}
