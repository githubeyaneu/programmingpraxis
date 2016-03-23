package eu.eyan.codefights;

import static eu.eyan.codefights.Expand_It.Expand_It_6_bad;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class Expand_ItTest {

	@Test
	public void testExpand_It_6_bad() throws Exception {
		assertThat(Expand_It_6_bad("", 1)).isEqualTo("-1");
		assertThat(Expand_It_6_bad("a2b3c2a1", 1)).isEqualTo("a");
		assertThat(Expand_It_6_bad("a2b3c2a1", 2)).isEqualTo("a");
		assertThat(Expand_It_6_bad("a2b3c2a1", 3)).isEqualTo("a");
		assertThat(Expand_It_6_bad("a2b3c2a1", 4)).isEqualTo("b");
		assertThat(Expand_It_6_bad("a2b3c2a1", 5)).isEqualTo("b");
		assertThat(Expand_It_6_bad("a2b3c2a1", 6)).isEqualTo("b");
		assertThat(Expand_It_6_bad("a2b3c2a1", 7)).isEqualTo("c");
		assertThat(Expand_It_6_bad("a2b3c2a1", 8)).isEqualTo("c");
		assertThat(Expand_It_6_bad("a2b3c2a1", 9)).isEqualTo("-1");
		
		assertThat(Expand_It_6_bad("a1a2a0b0c1", 1)).isEqualTo("a");
		assertThat(Expand_It_6_bad("a1a2a0b0c1", 2)).isEqualTo("a");
		assertThat(Expand_It_6_bad("a1a2a0b0c1", 3)).isEqualTo("a");
		assertThat(Expand_It_6_bad("a1a2a0b0c1", 4)).isEqualTo("c");

		assertThat(Expand_It_6_bad("z10a10", 4)).isEqualTo("a");
		assertThat(Expand_It_6_bad("z10a10", 14)).isEqualTo("z");
	}
}
