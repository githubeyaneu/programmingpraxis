package eu.eyan.programmingpraxis.patiencesorting;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

/**
 * http://programmingpraxis.com/2014/08/22/patience-sorting/
 * 
 * This sorting algorithm derives its name from the game of Patience
 * (that’s the British name, we call it Solitaire in the United States)
 * because it is implemented by analogy to sorting a shuffled deck of cards:
 * 
 * Starting with no piles, add the next card from the deck to the first pile with top card greater than the next card from the deck.
 * If the next card from the deck is greater than the top card of all the piles, start a new pile.
 * When the deck is exhausted, collect the cards in order by selecting the smallest visible card at each step.
 * 
 * For instance, consider sorting the list (4 3 9 1 5 2 7 8 6).
 * The first stack gets 4 and 3. Since 9 is larger than 3, it starts a second stack, 1 goes on the first stack,
 * then 5 and 2 go on the second stack.
 * At this point the first stack (top to bottom) consists of (1 3 4), the second stack consists of (2 5 9),
 * and the remaining deck consists of (7 8 6). Now 7 goes on a third stack, 8 goes on a fourth stack,
 * and 6 goes on top of the 7 in the third stack.
 * With all the cards dealt, 1 is collected from the first stack, 2 from the second stack, 3 and 4 from the first stack,
 * 5 from the second stack, 6 and 7 from the third stack, 8 from the fourth stack, and 9 from the second stack.
 * The algorithm has complexity O(n log n).
 * 
 * Your task is to implement the patience sorting algorithm.
 */
public class PatienceSorting
{

    private static final int CAP = 1000 * 100;// 0;//0 * 100;
    private List<Integer> originalList = new ArrayList<Integer>(CAP);
    private List<Integer> listToSort;

    @Before
    public void setUp()
    {
        Random rnd = new Random(23);
        for (int i = 0; i < CAP; i++)
        {
            Integer r = rnd.nextInt(CAP);
            originalList.add(r);
        }
        listToSort = newArrayList(originalList);
    }

    @Test
    public void testCollectionsSortBig() throws Exception
    {
        Collections.sort(listToSort);
    }

    @Test
    public void testPatienceSortBig() throws Exception
    {
        sort(listToSort);
    }

    @Test
    public void testBigs() throws Exception
    {
        // TODO: Speed anomaly
        // Collections.sort(listToSort);
        // List<Integer> sort = sort(listToSort);

        Collections.sort(listToSort);
        List<Integer> sort = sort(originalList);
        assertThat(sort).isEqualTo(listToSort);
    }

    @Test
    public void testPatienceSorting() throws Exception
    {
        assertThat(sort(newArrayList(4, 3, 9, 1, 5, 2, 7, 8, 6))).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(sort(newArrayList(4, 3, 9, 1, 5, 2, 7, 8, 6))).isEqualTo(newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    public static <T extends Comparable<? super T>> List<T> sort(List<T> list)
    {
        List<Stack<T>> stacks = newArrayList();
        for (T next : list)
        {
            Stack<T> stackForNext = null;
            for (Stack<T> stack : stacks)
            {
                if (next.compareTo(stack.peek()) < 0)
                {
                    stackForNext = stack;
                    break;
                }
            }
            if (stackForNext == null)
            {
                stackForNext = new Stack<T>();
                stacks.add(stackForNext);
            }
            // System.out.println("push " + next + " -> " + stackForNext);
            stackForNext.push(next);
            // System.out.println("    " + stacks);
        }

        List<T> sortedList = newArrayList();
        for (int i = 0; i < list.size(); i++)
        {
            Stack<T> stackWithSmallest = null;
            for (Stack<T> stack : stacks)
            {
                if (stackWithSmallest == null || stackWithSmallest.size() == 0
                        || (stack.size() > 0 && stack.peek().compareTo(stackWithSmallest.peek()) < 0))
                {
                    stackWithSmallest = stack;
                }
            }
            sortedList.add(stackWithSmallest.pop());
        }
        return sortedList;
    }
}