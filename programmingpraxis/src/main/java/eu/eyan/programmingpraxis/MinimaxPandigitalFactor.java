package eu.eyan.programmingpraxis;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MinimaxPandigitalFactor {

    /**
     * Permutations: 362880
     * Primes: 50251452
     * Min: 948721536, 7
     * Min: 619573248, 7
     * Result: 7
     */
    @Test
    // 1444 Sec (~25 Min)
    public void permutationsString() {
        System.out.println(Arrays.toString(primes(111)));
        String MAX = "987654321";
        List<String> permutations = Permutation.stringPermutations(MAX);
        System.out.println("Permutations: " + permutations.size());
        int[] primes = primes(Integer.parseInt(MAX));
        System.out.println("Primes: " + primes.length);
        System.out.println(findSmallestLargestPrimeFactor(permutations, primes));
    }

    private int findSmallestLargestPrimeFactor(List<String> permutations, int[] primes) {
        int min = Integer.MAX_VALUE;
        for (String string : permutations) {
            int input = Integer.parseInt(string);

            int biggest = 0;
            boolean biggestRelevant = true;
            int ct = 0;
            while (ct < primes.length && primes[ct] <= input) {
                if (input % primes[ct] == 0) {
                    biggest = primes[ct];
                    if (biggest > min) {
                        biggestRelevant = false;
                        break;
                    }
                }
                ct++;
            }

            if (biggestRelevant && biggest <= min) {
                min = biggest;
                System.out.println("Min: " + string + ", " + min);
            }
        }
        return min;
    }

    private int[] primes(int max) {
        boolean[] primes = new boolean[max + 1];
        Arrays.fill(primes, true);
        for (int i = 2; i <= max / 2; i++) {
            if (primes[i]) {
                for (int j = i + i; j <= max; j += i) {
                    primes[j] = false;
                }
            }
        }

        int ct = 0;
        for (int i = 2; i <= max; i++) {
            if (primes[i]) {
                ct++;
            }
        }
        //        System.out.println("Number of primes: " + ct);

        int[] primesList = new int[ct];
        ct = 0;
        for (int i = 2; i <= max; i++) {
            if (primes[i]) {
                primesList[ct] = i;
                ct++;
            }
        }
        return primesList;
    }

    @Test
    public void testArrayListSize() throws Exception {
        int M = 1000000;
        List<Integer> list = new ArrayList<Integer>(100 * M);
        for (int i = 0; i < 100 * M; i++) {
            list.add(i);

            if (i % M == 0) {
                System.out.println(i / M + "M");
            }
        }
        System.out.println(list.size());
    }

    @Test
    public void permutationsInteger() {
        Integer[] integers = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        List<List<Integer>> permutations = Permutation.permutations(newArrayList(Arrays.asList(integers)));
        System.out.println(permutations.size());
    }
}