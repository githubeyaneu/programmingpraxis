package eu.eyan.programmingpraxis.palindromes;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * http://programmingpraxis.com/2014/08/29/generating-palindromes/
 * 
 * The first hundred palindromic numbers are
 * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 22, 33, 44, 55, 66, 77, 88, 99,
 * 101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, 212, 222, 232, 242, 252, 262, 272, 282, 292,
 * 303, 313, 323, 333, 343, 353, 363, 373, 383, 393, 404, 414, 424, 434, 444, 454, 464, 474, 484, 494,
 * 505, 515, 525, 535, 545, 555, 565, 575, 585, 595, 606, 616, 626, 636, 646, 656, 666, 676, 686, 696,
 * 707, 717, 727, 737, 747, 757, 767, 777, 787, 797, 808, 818, 828, 838, 848, 858, 868, 878, 888, 898, and 909.
 * 
 * Your task is to write a program that generates the palindromic numbers in order;
 * use it to find the ten-thousandth palindromic number.
 * 
 */
public class GeneratingPalindromes
{

    @Test
    public void testPalindomGeneratorDummy() throws Exception
    {
        assertPalindromes(generatePalindromesDummy(10000));
    }

    @Test
    public void testPalindomGeneratorSmart() throws Exception
    {
        assertPalindromes(generatePalindromesSmart(10000));
    }

    private void assertPalindromes(List<Integer> palindromes)
    {
        assertThat(palindromes).hasSize(10000);
        assertThat(palindromes.get(1 - 1)).isEqualTo(0);
        assertThat(palindromes.get(2 - 1)).isEqualTo(1);
        assertThat(palindromes.get(3 - 1)).isEqualTo(2);
        assertThat(palindromes.get(10 - 1)).isEqualTo(9);
        assertThat(palindromes.get(11 - 1)).isEqualTo(11);
        assertThat(palindromes.get(12 - 1)).isEqualTo(22);
        assertThat(palindromes.get(20 - 1)).isEqualTo(101);
        assertThat(palindromes.get(100 - 1)).isEqualTo(909);
        assertThat(palindromes.get(10000 - 1)).isEqualTo(9000009);
    }

    public static List<Integer> generatePalindromesDummy(int number)
    {
        List<Integer> palindromes = Lists.newArrayList();
        int counter = 0;
        while (palindromes.size() < number)
        {
            String counterString = String.valueOf(counter);
            if (counterString.equals(new StringBuilder(counterString).reverse().toString()))
            {
                palindromes.add(counter);
            }
            counter++;
        }
        return palindromes;
    }

    public static List<Integer> generatePalindromesSmart(int number)
    {
        List<Integer> palindromes = Lists.newArrayList();

        int counter = 0;
        int digits = 1;
        boolean lastIncludedInMirror = true;

        while (palindromes.size() < number)
        {
            String counterString = String.valueOf(counter);
            String palindrome;
            if (lastIncludedInMirror)
            {
                palindrome =
                        counterString
                                + new StringBuilder(counterString.substring(0, counterString.length() - 1)).reverse()
                                        .toString();
            }
            else
            {
                palindrome = counterString + new StringBuilder(counterString).reverse().toString();
            }
            palindromes.add(Integer.valueOf(palindrome));
            counter++;
            if (String.valueOf(counter).length() > digits)
            {
                if (lastIncludedInMirror)
                {
                    counter = counter / 10;
                }
                else
                {
                    digits++;
                }
                lastIncludedInMirror = !lastIncludedInMirror;
            }
        }
        return palindromes;
    }
}