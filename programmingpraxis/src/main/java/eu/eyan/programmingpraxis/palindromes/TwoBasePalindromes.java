package eu.eyan.programmingpraxis.palindromes;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

/**
 * http://programmingpraxis.com/2014/10/21/two-base-palindromes/#comment-37079
 * 
 * I wanted to do this exercise as a follow-up to the earlier exercise on generating palindromes, but didn’t get around to it until now.
 * 
 * Your task is to write a program that generates a list of numbers that are palindromes in base 10 and base 8; 
 * for instance 149694110 = 55535558. 
 * 
 */
public class TwoBasePalindromes {

    @Test
    public void testName() throws Exception {
        Palindromgenerator generator10 = new Palindromgenerator(10);
        Palindromgenerator generator8 = new Palindromgenerator(8);
        Palindromgenerator generator12 = new Palindromgenerator(12);
        List<Long> palindromes = newArrayList();
        for (int i = 0; i < 10000; i++) {
            long next10 = generator10.nextInDecimal();
            palindromes.add(next10);
            System.out.println(next10 + " - " + generator8.next() + " - " + generator12.next());
        }
        assertPalindromes(palindromes);
    }

    private void assertPalindromes(List<Long> palindromes) {
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

    public static void main(String[] args) {
        Palindromgenerator generator10 = new Palindromgenerator(10);
        Palindromgenerator generator8 = new Palindromgenerator(8);
        long p10 = generator10.nextInDecimal();
        long p8 = generator8.nextInDecimal();
        while (true) {
            if (p8 == p10) {
                System.out.println(p10 + "(" + String.valueOf(p10).length() + ") " + Long.toString(p8, 8));
                p8 = generator8.nextInDecimal();
                p10 = generator10.nextInDecimal();
            } else if (p8 < p10) {
                p8 = generator8.nextInDecimal();
            } else {
                p10 = generator10.nextInDecimal();
            }
        }
    }

    public static class Palindromgenerator {

        private final int radix;
        private String counter = "0";
        private int digits = 1;
        private boolean lastIncludedInMirror = true;

        Palindromgenerator(int radix) {
            this.radix = radix;
        }

        public long nextInDecimal() {
            return Long.valueOf(next(), radix);
        }

        public String next() {
            String counterString = String.valueOf(counter);
            String palindrome;
            if (lastIncludedInMirror) {
                palindrome =
                    counterString
                        + new StringBuilder(counterString.substring(0, counterString.length() - 1)).reverse()
                                                                                                   .toString();
            } else {
                palindrome = counterString + new StringBuilder(counterString).reverse().toString();
            }
            counter = decToRad(radToDec(counter) + 1L);
            if (String.valueOf(counter).length() > digits) {
                if (lastIncludedInMirror) {
                    counter = decToRad(radToDec(counter) / radix);
                } else {
                    digits++;
                }
                lastIncludedInMirror = !lastIncludedInMirror;
            }
            return palindrome;
        }

        private Long radToDec(String rad) {
            return Long.valueOf(rad, radix);
        }

        private String decToRad(long dec) {
            return Long.toString(dec, radix);
        }
    }

    //    public static List<Long> generateTwoBasePalindromesSmart(int radix) {
    //        List<Long> palindromes = Lists.newArrayList();
    //
    //        int counter = 0;
    //        int solutions = 0;
    //        int digits = 1;
    //        boolean lastIncludedInMirror = true;
    //
    //        long start = System.currentTimeMillis();
    //        String last = "";
    //        while (solutions < 1000 * 1000 * 1000) {
    //            String counterString = String.valueOf(counter);
    //            String palindrome;
    //            if (lastIncludedInMirror) {
    //                palindrome =
    //                    counterString
    //                        + new StringBuilder(counterString.substring(0, counterString.length() - 1)).reverse()
    //                                                                                                   .toString();
    //            } else {
    //                palindrome = counterString + new StringBuilder(counterString).reverse().toString();
    //            }
    //            Long palindromeLong = Long.valueOf(palindrome);
    //            //            if (isPalindrom(Long.toOctalString(palindromeLong))) {
    //            //            palindromes.add(palindromeLong);
    //            //                System.out.println(palindromeLong + "(" + palindrome.length() + ")" + " - "
    //            //                    + Long.toString(palindromeLong, radix) + " counter:" + counter);
    //            //            }
    //            counter++;
    //            solutions++;
    //            if (palindrome.length() > last.length()) {
    //                System.out.println(palindrome + " " + palindrome.length() + " "
    //                    + (System.currentTimeMillis() - start) / 1000 + "s");
    //            }
    //            last = palindrome;
    //            if (String.valueOf(counter).length() > digits) {
    //                if (lastIncludedInMirror) {
    //                    counter = counter / 10;
    //                } else {
    //                    digits++;
    //                }
    //                lastIncludedInMirror = !lastIncludedInMirror;
    //            }
    //        }
    //        System.out.println(last + "(" + last.length() + ")");
    //        return palindromes;
    //    }
}
