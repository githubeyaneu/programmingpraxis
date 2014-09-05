package eu.eyan.programmingpraxis.distinctproduct;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class DistintProduct
{
    private static Set<Integer> products;
    private static int testCounter;

    public static void main(String[] args)
    {
        for (int n = 1; n <= 11; n++)
        {
            System.out.println("On2 Distinct products of " + n + "x" + n + " table: " + distinctProducts_O_n2(n) + ", " + testCounter + " tests.");
            System.out.println("2nd Distinct products of " + n + "x" + n + " table: " + distinctProducts(n) + ", " + testCounter + " tests.");
            System.out.println();
        }
    }

    public static int distinctProducts_O_n2(Integer n)
    {
        init();
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                testAndAdd(i * j);
            }
        }
        return products.size();
    }

    private static void testAndAdd(int number)
    {
        products.add(number);
        testCounter++;
    }

    private static void init()
    {
        products = Sets.newHashSet();
        testCounter = 0;
    }

    @Test
    public void testDistinct() throws Exception
    {
        assertThat(distinctProducts_O_n2(9)).isEqualTo(36);
        for (int n = 1; n <= 100; n++)
        {
            assertThat(distinctProducts(n)).as("distinct products same for n=" + n).isEqualTo(distinctProducts_O_n2(n));
        }
    }

    private static int distinctProducts(int n)
    {
        init();
        for (int i = 1; i <= n; i++)
        {
            if (products.contains(i))
            {
                for (int j = i; j <= n; j++)
                {
                    testAndAdd(i * j);
                }
            }
        }
        return products.size();
    }
}