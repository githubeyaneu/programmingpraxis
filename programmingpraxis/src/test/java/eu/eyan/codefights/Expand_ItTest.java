package eu.eyan.codefights;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class Expand_ItTest {

	@Test
	public void testExpand_It_6() throws Exception {
		assertThat(new Expand_It().Expand_It_6("", 1)).isEqualTo("");
		assertThat(new Expand_It().Expand_It_6("a2b3c2a1", 1)).isEqualTo("a");
		assertThat(new Expand_It().Expand_It_6("a2b3c2a1", 2)).isEqualTo("a");
		assertThat(new Expand_It().Expand_It_6("a2b3c2a1", 3)).isEqualTo("a");
		assertThat(new Expand_It().Expand_It_6("a2b3c2a1", 4)).isEqualTo("b");
		assertThat(new Expand_It().Expand_It_6("a2b3c2a1", 5)).isEqualTo("b");
		assertThat(new Expand_It().Expand_It_6("a2b3c2a1", 6)).isEqualTo("b");
		assertThat(new Expand_It().Expand_It_6("a2b3c2a1", 7)).isEqualTo("c");
		assertThat(new Expand_It().Expand_It_6("a2b3c2a1", 8)).isEqualTo("c");
		assertThat(new Expand_It().Expand_It_6("a2b3c2a1", 9)).isEqualTo("");

		assertThat(new Expand_It().Expand_It_6("a1a2a0b0c1", 1)).isEqualTo("a");
		assertThat(new Expand_It().Expand_It_6("a1a2a0b0c1", 2)).isEqualTo("a");
		assertThat(new Expand_It().Expand_It_6("a1a2a0b0c1", 3)).isEqualTo("a");
		assertThat(new Expand_It().Expand_It_6("a1a2a0b0c1", 4)).isEqualTo("c");

		assertThat(new Expand_It().Expand_It_6("z10a10", 4)).isEqualTo("a");
		assertThat(new Expand_It().Expand_It_6("z10a10", 14)).isEqualTo("z");
	}
}
