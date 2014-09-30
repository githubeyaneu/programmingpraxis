package eu.eyan.programmingpraxis.thuemorse;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * http://programmingpraxis.com/2014/09/30/thue-morse-sequence/
 * 
 * Thue-Morse Sequence by programmingpraxis
 * 
 * Mathematicians are strange people. 
 * As an example, I offer the Thue-Morse sequence, which is a binary sequence of 0's and 1's that never repeats, 
 * obtained by starting with a single 0 and successively appending the binary complement of the sequence. 
 * Thus, 
 * term 0 of the sequence is (0), 
 * term 1 of the sequence is (0 1), 
 * term 2 of the sequence is (0 1 1 0), 
 * term 3 of the sequence is (0 1 1 0 1 0 0 1), 
 * term 4 of the sequence is (0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0), 
 * 
 * and so on; to calculate term 3 from term 2, we started with term 2 (0 1 1 0), complemented each element of the term (1 0 0 1), 
 * then appended the two (0 1 1 0 1 0 0 1). 
 * That looks useless to me, but mathematicians have been excited by it since 1851, 
 * hence my conclusion that mathematicians are strange people.
 * 
 * Your task is to write a program that generates the nterm of the Thue-Morse sequence.
 *
 */
public class ThueMorse {

    @Test
    public void testThueMorse() throws Exception {
        assertThat(thueMorse(0)).isEqualTo("0");
        assertThat(thueMorse(1)).isEqualTo("01");
        assertThat(thueMorse(2)).isEqualTo("0110");
        assertThat(thueMorse(3)).isEqualTo("01101001");
        assertThat(thueMorse(4)).isEqualTo("0110100110010110");
        System.out.println(thueMorse(6));
    }

    private static String thueMorse(int term) {
        return term == 0 ? "0" : thueMorse(term - 1)
            + thueMorse(term - 1).replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1");
    }
}