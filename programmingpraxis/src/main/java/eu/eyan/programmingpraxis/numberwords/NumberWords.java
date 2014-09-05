package eu.eyan.programmingpraxis.numberwords;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * http://programmingpraxis.com/2014/07/25/number-words/
 * 
 * Today’s exercise is an interview question from Career Cup. When I first saw the exercise, I thought it would be easy,
 * but I had trouble writing a solution, so maybe it will a good exercise for all you readers, as well:
 * Given a positive integer, return all the ways that the integer can be represented by letters using the
 * mapping 1 -> A, 2 -> B, …, 26 -> Z. For instance, the number 1234 can be represented by the words ABCD, AWD and LCD.
 * Your task is to write the program to generate words from numbers.
 * 1A, 2B, 3C, 4D, 5E, 6F, 7G, 8H, 9I, 10J, 11K, 12L, 13M, 14N, 15O, 16P, 17Q, 18R, 19S, 20T, 21U, 22V, 23W, 24X, 25Y, 26Z
 */
public class NumberWords
{

    private static List<String> numberWords(String number)
    {
        List<String> words = Lists.newArrayList();
        for (int i = 1; i < 27; i++)
        {
            String letter = String.valueOf(i);
            if (number.equals(letter))
            {
                words.add("" + (char) (65 + i - 1));
            }
            else
            {
                if (number.startsWith(letter))
                {
                    for (String subWord : numberWords(number.substring(letter.length())))
                    {
                        words.add(((char) (65 + i - 1)) + subWord);
                    }
                    ;
                }
            }
        }
        return words;
    }

    @Test
    public void testNumberWords() throws Exception
    {
        assertThat(numberWords("1234")).hasSize(3);
        assertThat(numberWords("1234")).containsExactly("ABCD", "AWD", "LCD");
        System.out.println(numberWords("1234"));

        System.out.println(numberWords("12345"));

        // 1-A 2-B 12-L 21-U
        assertThat(numberWords("121")).containsExactly("ABA", "AU", "LA");
        System.out.println(numberWords("12121"));

        System.out.println(numberWords("11111"));

        assertThat(numberWords("1234567891011121314151617181920212223242526").size()).isEqualTo(259584);
    }
}