package eu.eyan.codefights;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class Sorting_Fun {


	String[] SortingFun(String[] g) {
		Arrays.sort(g, new Comparator() {
			
			public int compare(Object o1, Object o2) {
				int m=0,n=0;
				for (int c : ((String) o1).toCharArray()) m += c - 97;
				for (int c : ((String) o2).toCharArray()) n += c - 97;
				return m-n;
			}
		});
		return g;
		
	}
}

//	String[] SortingFun(String[] g) {
//		int l = g.length, i , j, m, n;
//		for (i = 0; i < l; i++) 
//			for (j = i, m=0, n=0; j < l; j++) {
//				for (int c : g[j].toCharArray()) m += c - 97;
//				for (int c : g[i].toCharArray()) n += c - 97;
//				if(m<n){
//					String s = g[i];
//					g[i] = g[j];
//					g[j] = s;
//				}
//			}
//		return g;
//		
//	}

//String[] SortingFun(String[] g) {
//	int l = g.length, i , j;
//	for (i = 0; i < l; i++) 
//		for (j = i; j < l; j++) 
//			if(s(g[j])<s(g[i])){
//				String s = g[i];
//				g[i] = g[j];
//				g[j] = s;
//			}
//	return g;
//}
//
//int s(String s) {
//	int r = 0;
//	for (int i = 0; i < s.length(); i++) {
//		r += s.charAt(i) - 97;
//	}
//	return r;
//}