package eu.eyan.codefights;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class Caesar {

	@Test
	public void testName() throws Exception {
		assertThat(Caesar("abc", 0)).isEqualTo("ABC");
		assertThat(Caesar("hello, world!", 1)).isEqualTo("IFMMP, XPSME!");
	}
	
	String Caesar(String text, int shift) {
		String ret ="";
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if(96<c && c<123) ret+= "" + (char)((c-97+shift)%26+65);
			else ret+=c;
		}
		return ret;
	}

}
