package eu.eyan.codefights;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class MakePalindrome {

	@Test
	public void testName() throws Exception {
		l=new int[123];
		assertThat(makePalindrome("aabb")).isTrue();
		l=new int[123];
		assertThat(makePalindrome("abcde")).isFalse();
		l=new int[123];
		assertThat(makePalindrome("xyyzx")).isTrue();
		l=new int[123];
		assertThat(makePalindrome("xyyzxs")).isFalse();
		
	}
	
	int l[] = new int[123], c;
	boolean makePalindrome(String s) {
		for(byte b: s.getBytes()) l[b]++;
		
		for(int m:l) c+=m%2;
		
		return c<2;
	}
	
	
}
