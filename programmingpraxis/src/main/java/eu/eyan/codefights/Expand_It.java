package eu.eyan.codefights;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Expand_It {

	/**
	 * 
	 Example
	 * 
	 * Expand_It("a2b3c2a1", 2) = "a" The decompressed s equals "aabbbcca", and
	 * once sorted it becomes "aaabbbcc". Its second 1-based letter is 'a',
	 * which is the answer.
	 * 
	 * [input] string s
	 * 
	 * The compressed string, 2 ≤ s.length ≤ 104. The length of the decompressed
	 * string is no greater than 1018. [input] integer k
	 * 
	 * A non-negative integer, the 1-based index of the letter to return. If
	 * s.length < k, return "-1" instead. [output] string
	 * 
	 * The kth letter of the decompressed string, or "-1" if it does not exist.
	 */
	static String ExpandIt(String s, int k) {
		List<String> t = new ArrayList<String>();

		Matcher m = Pattern.compile("[a-z]\\d*").matcher(s);
		while (m.find()) {
			for (int i = 0; i < Integer.valueOf(m.group().substring(1)); i++) {
				t.add("" + m.group().charAt(0));
			}
		}
		Collections.sort(t);

		return k <= t.size() ? t.get(k - 1) : "-1";
	}

	long l[] = new long[28], x;
	String Expand_It_6(String s, int k) {
		char c = 97;

		for (char d : (s+"x").toCharArray())
			if (d > 58) {
				l[c - 97] += x;
				x = 0;
				c = d;
			} else
				x += x * 10 + d - 48;

		c = 97;
		for (long n : l) {
			if (k <= n) return "" + c;
			c++;
			k -= n;
		}
		return "";
	}

	static String Expand_It_5(String s, int k) {

		int[] l = new int[26];

		Matcher m = Pattern.compile("[a-z]\\d*").matcher(s);
		while (m.find()) {
			int i = (int) m.group().charAt(0) - 97;
			l[i] = l[i] + new Integer(m.group().substring(1));
		}

		char c = 97;
		for (int n : l) {
			if (k <= n)
				return "" + c;
			c++;
			k -= n;
		}
		return "-1";
	}

	static String Expand_It_4_bad(String s, int k) {

		int[] l = new int[26];
		String[] t = s.split("[a-z]");
		for (int i = 0; i < t.length - 1; i++) {
			int j = s.split("\\d(?=)")[i].charAt(0) - 97;
			l[j] = l[j] + new Integer(String.valueOf(t[i + 1]));
		}
		char c = 97;
		for (int n : l) {
			if (k <= n)
				return "" + c;
			c++;
			k -= n;
		}
		return "-1";
	}

	static String Expand_It_3(String s, int k) {

		int[] l = new int[26];

		Matcher m = Pattern.compile("[a-z]\\d*").matcher(s);
		while (m.find()) {
			String n = m.group();
			l[(int) n.charAt(0) - 97] = l[(int) n.charAt(0) - 97]
					+ new Integer(n.substring(1));
		}

		char c = 97;
		for (int n : l) {
			if (k <= n)
				return "" + c;
			c++;
			k -= n;
		}
		return "-1";
	}

	static String Expand_It_2(String s, int k) {

		int[] l = new int[26];

		Matcher m = Pattern.compile("[a-z]\\d*").matcher(s);
		while (m.find()) {
			String n = m.group();
			l[(int) n.charAt(0) - 97] = l[(int) n.charAt(0) - 97]
					+ Integer.valueOf(n.substring(1));
		}

		for (int i = 0; i < l.length; i++) {
			if (k <= l[i])
				return "" + (char) (i + 97);
			k -= l[i];
		}
		return "";
	}

	static String Expand_It_1(String s, int k) {
		TreeMap<String, Integer> letters = new TreeMap<String, Integer>();

		for (int i = 0; i < s.length(); i += 2) {
			String letter = s.substring(i, i + 1);
			int number = Integer.parseInt(s.substring(i + 1, i + 2));
			if (!letters.containsKey(letter))
				letters.put(letter, 0);
			letters.put(letter, letters.get(letter) + number);
		}

		for (String key : letters.keySet()) {
			if (k <= letters.get(key))
				return key;
			k -= letters.get(key);
		}
		return "no";
	}

	@Test
	public void testName() throws Exception {
		assertThat(ExpandIt("a2b3c2a1", 1)).isEqualTo("a");
		assertThat(ExpandIt("a2b3c2a1", 2)).isEqualTo("a");
		assertThat(ExpandIt("a2b3c2a1", 3)).isEqualTo("a");
		assertThat(ExpandIt("a2b3c2a1", 4)).isEqualTo("b");
		assertThat(ExpandIt("a2b3c2a1", 5)).isEqualTo("b");
		assertThat(ExpandIt("a2b3c2a1", 6)).isEqualTo("b");
		assertThat(ExpandIt("a2b3c2a1", 7)).isEqualTo("c");
		assertThat(ExpandIt("a2b3c2a1", 8)).isEqualTo("c");
		assertThat(ExpandIt("a2b3c2a1", 9)).isEqualTo("-1");

		assertThat(ExpandIt("", 1)).isEqualTo("-1");
		// assertThat(ExpandIt("a", 1)).isEqualTo("-1");
		assertThat(ExpandIt("a1", 1)).isEqualTo("a");
		assertThat(ExpandIt("a01", 1)).isEqualTo("a");
		assertThat(ExpandIt("a01", 2)).isEqualTo("-1");
		assertThat(ExpandIt("a01a123", 2)).isEqualTo("a");

		assertThat(ExpandIt("a01z00001", 1)).isEqualTo("a");
		assertThat(ExpandIt("a01z00001", 2)).isEqualTo("z");
		assertThat(ExpandIt("a01z00001a1", 2)).isEqualTo("a");
	}

}
