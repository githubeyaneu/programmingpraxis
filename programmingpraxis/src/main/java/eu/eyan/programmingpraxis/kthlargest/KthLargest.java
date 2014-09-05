package eu.eyan.programmingpraxis.kthlargest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

import com.google.common.collect.Lists;

public class KthLargest
{

    public static int kthLargest(int k, int... numbers)
    {
        List<Integer> klist = Lists.newArrayList();
        int[] firstK = Arrays.copyOfRange(numbers, 0, k);
        for (int i : firstK)
        {
            klist.add(i);
        }
        Collections.sort(klist);

        for (int i = k; i < numbers.length; i++)
        {
            int number = numbers[i];
            if (number > klist.get(0))
            {
                klist.set(0, number);
                Collections.sort(klist);
            }
            System.out.println(klist);
        }

        return klist.get(0);
    }

    @Test
    public void testKthLargest() throws Exception
    {
        Assertions.assertThat(kthLargest(1, 1, 2, 3, 4, 5, 6, 7, 8, 9)).isEqualTo(9);
        Assertions.assertThat(kthLargest(5, 1, 2, 3, 4, 5, 6, 7, 8, 9)).isEqualTo(5);
        Assertions.assertThat(kthLargest(9, 1, 2, 3, 4, 5, 6, 7, 8, 9)).isEqualTo(1);

        Assertions.assertThat(kthLargest(1, -1, 2, -4, 8)).isEqualTo(8);
        Assertions.assertThat(kthLargest(2, -1, 2, -4, 8)).isEqualTo(2);
        Assertions.assertThat(kthLargest(3, -1, 2, -4, 8)).isEqualTo(-1);
        Assertions.assertThat(kthLargest(4, -1, 2, -4, 8)).isEqualTo(-4);
    }
}