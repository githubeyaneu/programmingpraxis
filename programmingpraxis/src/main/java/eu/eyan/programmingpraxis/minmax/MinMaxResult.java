package eu.eyan.programmingpraxis.minmax;

public class MinMaxResult
{

    private int min;
    private int max;
    private int comparisonCounter;

    public int getMin()
    {
        return min;
    }

    public int getMax()
    {
        return max;
    }

    public int getComparisonCounter()
    {
        return comparisonCounter;
    }

    public MinMaxResult(int min, int max, int comparisonCounter)
    {
        this.min = min;
        this.max = max;
        this.comparisonCounter = comparisonCounter;
    }
}
