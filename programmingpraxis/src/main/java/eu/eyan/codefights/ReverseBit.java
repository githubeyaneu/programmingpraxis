package eu.eyan.codefights;

import scala.collection.mutable.StringBuilder;

public class ReverseBit {
public static void main(String[] args) {
	int x=234;
	
	int ret = Integer.valueOf(new StringBuilder(Integer.toString(x, 2)).reverse().toString(), 2);
	System.out.println(ret);
}
}
