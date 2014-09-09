package eu.eyan.programmingpraxis;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

public class Permutation {

    public static List<String> stringPermutations(String str) {
        List<String> permutations = newArrayList();
        permutation("", str, permutations);
        return permutations;
    }

    private static void permutation(String prefix, String str, List<String> permutations) {
        int n = str.length();
        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), permutations);
            }
        }
    }

    public static <T> List<List<T>> permutations(List<T> input) {
        List<List<T>> permutations = newArrayList();
        permutation(newArrayList(), input, permutations);
        return permutations;
    }

    private static <T> void permutation(List<T> prefix, List<T> input, List<List<T>> permutations) {
        int n = input.size();
        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                List<T> newPrefix = newArrayList(prefix);
                newPrefix.add(input.get(i));

                List<T> newInput = newArrayList();
                newInput.addAll(input.subList(0, i));
                newInput.addAll(input.subList(i + 1, n));

                permutation(newPrefix, newInput, permutations);
            }
        }
    }
}