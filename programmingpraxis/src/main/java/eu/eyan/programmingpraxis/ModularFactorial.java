package eu.eyan.programmingpraxis;

public class ModularFactorial
{

    public static long calculateWithPrimeModulo(long n, long modulo)
    {
        long ret = 1;
        for (long i = 1; i <= n; i++)
        {
            ret = (ret * i) % modulo;
        }
        return ret;
    }

}
