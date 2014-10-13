package eu.eyan.programmingpraxis.magic1089;

/**
 * http://programmingpraxis.com/2014/10/03/magic-1089/
 * 
 * I’ve been very busy at work this week, so I only have time for a simple exercise today.
 * 
 * This is a simple puzzle in arithmetic.
 * Take any three-digit number with digits in descending order;
 * for instance, 532 is acceptable, but 481 is not.
 * Reverse the digits of the original number, and subtract the reversal from the original.
 * Then reverse that difference, and add the reversal of the difference to the difference.
 * Write the result as output.
 * 
 * For instance, start with the number 532.
 * Its reversal is 235, and the difference is 532 – 235 = 297.
 * Reversing the difference gives 792, and 297 + 792 = 1089, which is the result.
 * 
 * Your task is to write a program that makes this calculation,
 * and try it on several different starting numbers;
 * you might enjoy working out the arithmetic behind the results that you see.
 * 
 * @author András
 *
 */
public class Magig1089
{
    public static void main(String... args)
    {
        for (int n1 = 9; n1 > 2; n1--)
        {
            for (int n2 = n1 - 1; n2 > 1; n2--)
            {
                for (int n3 = n2 - 1; n3 > 0; n3--)
                {
                    int n = 100 * n1 + 10 * n2 + n3;          // 532
                    int nr = 100 * n3 + 10 * n2 + n1;         // 235
                    int d = 100 * (n1 - n3 - 1) + 10 * (9) + (10 - n1 + n3); // 99*(n1-n3)
//                    int d_check = 100 * (n1 - n3) + 0 + (n3 - n1);  // 297
//                    assert (d_check == d);
                    int dr = 100 * (10 - n1 + n3) + 10 * (9) + (n1 - n3 - 1);
//                    int dr_check = Integer.valueOf(new StringBuilder(Integer.toString(d)).reverse().toString());
//                    assert (dr_check == dr);
                    int ddr = 100 * (10 - n1 + n3 + n1 - n3 - 1 + 1) + 10 * (9 + 9 - 10) + (n1 - n3 - 1 + 10 - n1 + n3); // 1089
//                    int ddr2 = dr + d; // 1089
//                    assert (ddr2 == ddr);
                    System.out.println(n + " " + nr + " " + d + " " + dr + " " + ddr);
                }
            }
        }
    }
}
