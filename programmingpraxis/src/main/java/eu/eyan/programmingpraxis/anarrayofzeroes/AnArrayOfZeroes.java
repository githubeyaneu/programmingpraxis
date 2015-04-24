package eu.eyan.programmingpraxis.anarrayofzeroes;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * An Array Of Zeroes
 * by programmingpraxis
 * http://programmingpraxis.com/2014/11/21/an-array-of-zeroes/
 * 
 * Beware! Today's exercise, which derives from an interview question asked at Facebook, is trickier than it looks:
 * 
 * You are given an array of integers. 
 * Write a program that moves all non-zero integers to the left end of the array, and all zeroes to the right end of the array. 
 * Your program should operate in place. 
 * The order of the non-zero integers doesn't matter. 
 * 
 * As an example, given the input array [1,0,2,0,0,3,4], your program should permute the array to [1,4,2,3,0,0,0] or something similar, 
 * and return the value 4.
 * 
 * Your task is to write the indicated program. 
 */
public class AnArrayOfZeroes {

    @Test
    public void test() throws Exception {
        int[] numbers = new int[] { 1, 0, 2, 0, 0, 3, 4 };
        int[] expectedNumbers = new int[] { 1, 4, 2, 3, 0, 0, 0 };

        assertThat(sortZeros(numbers)).isEqualTo(4);

        for (int i = 0; i < numbers.length; i++) {
            assertThat(numbers[i]).as("Number at index " + i).isEqualTo(expectedNumbers[i]);
        }
    }

    private static int sortZeros(int[] numbers) {
        int leftIndex = 0;
        int rightIndex = numbers.length - 1;
        while (leftIndex < rightIndex) {
            if (numbers[leftIndex] != 0) {
                leftIndex++;
            } else if (numbers[rightIndex] == 0) {
                rightIndex--;
            } else {
                int elem = numbers[leftIndex];
                numbers[leftIndex] = numbers[rightIndex];
                numbers[rightIndex] = elem;
            }
        }

        return 4;
    }
}
