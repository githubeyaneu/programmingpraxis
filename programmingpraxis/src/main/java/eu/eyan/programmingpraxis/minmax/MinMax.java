package eu.eyan.programmingpraxis.minmax;

public class MinMax
{

    private static int comparisonCounter;

    public static MinMaxResult minmax(int[] numbers)
    {
        if (numbers.length == 0)
        {
            return new MinMaxResult(0, 0, comparisonCounter);
        }
        int min = numbers[0];
        int max = numbers[0];
        comparisonCounter = 0;
        for (int i = 1; i < numbers.length; i++)
        {
            if (bigger(numbers[i], max))
            {
                max = numbers[i];
            }
            else if (bigger(min, numbers[i]))
            {
                min = numbers[i];
            }
        }
        MinMaxResult result = new MinMaxResult(min, max, comparisonCounter);
        return result;
    }

    private static boolean bigger(int i, int j)
    {
        comparisonCounter++;
        return i > j;
    }
}
