package eu.eyan.programmingpraxis.luckynumbers;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * http://programmingpraxis.com/2014/03/18/lucky-numbers/
 * 
 * In today’s exercise, we study Lucky Numbers, which are those positive integers that survive the Sieve of Josephus.
 * It works like this:
 * Start with the numbers 1 through n, where n is the desired limit of the sieve;
 * we’ll illustrate with n = 20: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20.
 * At the first step, remove every second number from the sieve, starting from the 0’th:
 * this leaves 1, 3, 5, 7, 9, 11, 13, 15, 17, 19.
 * 
 * At the second step, the second number in the list is 3, so remove every third number from the sieve,
 * starting from the 0’th: this leaves 1, 3, 7, 9, 13, 15, 19. At the third step,
 * the third number in the list is 7, so remove every seventh number from the sieve,
 * starting from the 0’th: this leaves 1, 3, 7, 9, 13, 15. And so on.
 * The lucky numbers are listed at A000959: 1, 3, 7, 9, 13, 15, 21, 25, 31, 33, 37, 43, 49, 51, 63, 67, 69, 73, 75, 79, 87, 93, 99, 105, 111, 115, 127, 129, 133, 135, 141, 151, 159, 163, 169, 171, 189, 193, 195, 201, 205, 211, 219, 223, 231, 235, 237, 241, 259, 261, 267, 273, 283, 285, 289, 297,
 * 303, ....
 * 
 * Lucky numbers share many properties with prime numbers, including the same asymptotic behavior as the primes:
 * any given number n is lucky with probability 1 / loge n,
 * the same as any given number n is prime with probability 1 / loge n.
 * 
 * Your task is to write a program that lists the lucky numbers less than n.
 *
 */
public class LuckyNumbers
{
    public static List<Integer> luckyNumbers(int n)
    {
        List<Integer> luckies = newArrayList();
        for (int i = 1; i < n + 1; i++)
        {
            luckies.add(i);
        }
        luckyRemove(luckies, 2);
        int counter = 1;
        while (counter < luckies.size() && luckies.get(counter) <= n)
        {
            luckyRemove(luckies, luckies.get(counter));
            counter++;
        }
        return luckies;
    }

    private static void luckyRemove(List<Integer> luckies, Integer step)
    {
        ArrayList<Integer> toRemove = newArrayList();
        int counter = step - 1;
        while (counter < luckies.size())
        {
            toRemove.add(luckies.get(counter));
            counter += step;
        }
        luckies.removeAll(toRemove);
    }

    @Test
    public void testLuckyNumbers()
    {
        assertThat(LuckyNumbers.luckyNumbers(20)).containsExactly(1, 3, 7, 9, 13, 15);
        List<Integer> lucky303 = LuckyNumbers.luckyNumbers(303);
        List<Integer> expected = newArrayList(1, 3, 7, 9, 13, 15, 21, 25, 31, 33, 37, 43, 49, 51, 63, 67, 69, 73, 75, 79, 87, 93, 99, 105, 111, 115, 127, 129, 133, 135, 141, 151, 159, 163, 169, 171, 189, 193, 195, 201, 205, 211, 219, 223, 231, 235, 237, 241, 259, 261, 267, 273, 283, 285, 289, 297, 303);
        assertThat(lucky303).containsExactly(expected.toArray());
    }
}
