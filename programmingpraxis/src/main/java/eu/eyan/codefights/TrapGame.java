package eu.eyan.codefights;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Locale;

import org.junit.Test;

public class TrapGame {
	String[] Trap_Game(int[] K) {
		int m = 1001, k, l, a, b, d[][] = new int[m][m], i = K.length,y;
		String r[] = new String[i];
		
		for (l = 0; l < i; l++) {
			k= K[l];
			y=0;
			
			for(a=1; a<=k;a++)
				for(b=a; b<=k;b++) {
					if(d[a][b]==0) d[a][b]=d(a,b);
					if(d[a][b]>1) y+= b==a?1:2;
				}
				
			
			r[l] = String.format(Locale.US, "%.4f", (double) y/k/k);
		}
		return r;
	}

	int d(int a, int b) {
	  if(a == 0 || b == 0) return a+b; // base case
	  return d(b, a%b);
	}
	
	@Test
	public void test() throws Exception {
		assertThat(Trap_Game(new int[] { 1, 2 })).isEqualTo(
				new String[] { "0.0000", "0.2500" });
		assertThat(Trap_Game(new int[] { 3, 4 })).isEqualTo(
				new String[] { "0.2222", "0.3125" });
		assertThat(Trap_Game(new int[] { 500, 300, 1000, 25 })).isEqualTo(
				new String[] { "0.3911", "0.3912", "0.3916", "0.3616" });

	}
	
	
}
