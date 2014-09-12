package eu.eyan.programmingpraxis.minmax;

import java.util.Random;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class MinMaxTest
{
    @Test
    public void testMinMax()
    {
        verifyMinMax(new int[] {}, 0, 0);
        verifyMinMax(new int[] { 1 }, 1, 1);
        verifyMinMax(new int[] { 1, 1, 1, 1 }, 1, 1);
        verifyMinMax(new int[] { 3, 2, 1 }, 1, 3);
        verifyMinMax(new int[] { 1, 2, 3 }, 1, 3);
        verifyMinMax(randomArray(1000, 17), 1, 999);
        verifyMinMax(randomArray(10000, 117), 0, 9998);
    }

    private int[] randomArray(int size, int seed)
    {
        int[] array = new int[size];
        Random random = new Random(seed);
        for (int i = 0; i < array.length; i++)
        {
            array[i] = random.nextInt(size);
        }
        return array;
    }

    private void verifyMinMax(int[] numbers, int expectedMin, int expectedMax)
    {
        MinMaxResult minmax = MinMax.minmax(numbers);
        System.out.println(numbers.length + " items -> " + minmax.getComparisonCounter() + " comparison");
        Assertions.assertThat(minmax.getMin()).isEqualTo(expectedMin);
        Assertions.assertThat(minmax.getMax()).isEqualTo(expectedMax);
    }
}
