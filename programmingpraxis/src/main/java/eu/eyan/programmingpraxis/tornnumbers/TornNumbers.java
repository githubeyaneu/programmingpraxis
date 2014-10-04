package eu.eyan.programmingpraxis.tornnumbers;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.junit.Test;

/**
 * http://programmingpraxis.com/2014/09/16/torn-numbers/
 * 
 * In 1917, Henry Ernest Dudeney published a book Amusements in Mathematics of arithmetic puzzles. 
 * Today's exercise solves puzzle 113 from that book:
 * 
 * A number n is a torn number if it can be chopped into two parts which, when added together and squared, 
 * are equal to the original number. 
 * For instance, 88209 is a torn number because (88 + 209)^2 = 297^2 = 88209.
 * 
 * Your task is to write a program to find torn numbers.
 *
 */
public class TornNumbers {

    public static List<Integer> tornNumbers(int max) {
        List<Integer> tornNumbers = newArrayList();
        for (int i = 10; i <= max; i++) {
            if (isTornNumber(i)) {
                tornNumbers.add(i);
            }
        }
        return tornNumbers;
    }

    public static boolean isTornNumber(Integer number) {
        boolean isTorn = false;
        String numberString = String.valueOf(number);
        for (int i = 1; i < numberString.length(); i++) {
            Integer a = Integer.valueOf(numberString.substring(0, i));
            Integer b = Integer.valueOf(numberString.substring(i));
            if ((a + b) * (a + b) == number) {
                System.out.println(number + ": " + a + " + " + b + " (" + (a + b) + ")");
                isTorn = true;
            }
        }
        return isTorn;
    }

    @Test
    public void testTornNumbers() throws Exception {
        System.out.println(tornNumbers(88209));
    }
}