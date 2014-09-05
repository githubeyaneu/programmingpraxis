package eu.eyan.programmingpraxis.longestsubsequence;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.toList;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * http://programmingpraxis.com/2014/09/02/longest-increasing-subsequence/
 * 
 * A sequence is a list of integers (or any other ordered type, but we’ll use integers to keep things simple).
 * A subsequence is any, possibly non-consecutive,
 * list drawn from the parent sequence with items in the same order as the parent sequence.
 * An increasing subsequence is a subsequence with all the items in increasing order.
 * A longest increasing subsequence (there may be more than one with the same length)
 * is an increasing subsequence of a parent sequence of the greatest possible length.
 * For instance, the sequence (3 2 6 4 5 1) has longest increasing subsequences (2 4 5) and (3 4 5).
 * 
 * The algorithm to find the longest increasing subsequence is similar to the algorithm for patience sorting
 * of a previous exercise, with a small modification.
 * When dealing the cards, each time a card is placed on a pile,
 * a back-pointer to the top card on the previous pile is placed along with the card.
 * Then, when all the cards are dealt, the number of piles is the length of the longest increasing subsequence,
 * and the longest increasing subsequence can be recovered by taking the top card from the last pile
 * and following the back-pointers to previous piles.
 * 
 * For instance, with sequence (3 2 6 4 5 1) the cards are dealt with 3 and 2 on the first pile,
 * 6 and 4 on the second pile, 5 on the third pile, and 1 on the first pile,
 * so the longest increasing subsequence has length 3.
 * The 5 on the third pile ends the longest increasing subsequence,
 * it points to the 4 which was on the top of the second pile when 5 was added to the third pile,
 * and 4 points to the 2 which was on the top of the first pile when 4 was added to the second pile;
 * even though 1 was later added to the first pile, is wasn’t yet on the pile when 4 was added to the second pile,
 * so it’s not part of the longest increasing subsequence.
 * 
 * Your task is to write a program to find the longest increasing subsequence of a sequence.
 * 
 */
public class LongestSubsequence
{
    public static List<List<Integer>> longestSubsequence(List<Integer> sequence)
    {
        List<Stack<ObjectWithPointer<Integer>>> stacks = newArrayList();
        for (Integer item : sequence)
        {
            Stack<ObjectWithPointer<Integer>> stackToInsert = null;
            for (Stack<ObjectWithPointer<Integer>> stack : stacks)
            {
                if (stack.peek().get() >= item)
                {
                    stackToInsert = stack;
                    break;
                }
            }
            if (stackToInsert == null)
            {
                stackToInsert = new Stack<ObjectWithPointer<Integer>>();
                stacks.add(stackToInsert);
            }
            ObjectWithPointer<Integer> pointer = stacks.indexOf(stackToInsert) < 1 ? null : stacks.get(stacks.size() - 2).peek();
            stackToInsert.push(new ObjectWithPointer<Integer>(item, pointer));
            System.out.println(stacks);
        }
        List<List<Integer>> longestSubseqeunces = newArrayList();
        findLongestSubsequences(stacks, longestSubseqeunces, newArrayList());
        return longestSubseqeunces;
    }

    private static void findLongestSubsequences(List<Stack<ObjectWithPointer<Integer>>> stacks, List<List<Integer>> longestSubseqeunces, List<ObjectWithPointer<Integer>> actualSubseqence)
    {
        if (actualSubseqence.size() == stacks.size())
        {
            longestSubseqeunces.add(reverse(actualSubseqence.stream().map(o -> o.get()).collect(toList())));
            return;
        }
        Stack<ObjectWithPointer<Integer>> actualStack = stacks.get(stacks.size() - actualSubseqence.size() - 1);
        Set<ObjectWithPointer<Integer>> relevantElementsOfStack = newHashSet();
        if (actualSubseqence.isEmpty())
        {
            relevantElementsOfStack.addAll(actualStack);
        }
        else
        {
            ObjectWithPointer<Integer> last = actualSubseqence.get(actualSubseqence.size() - 1);
            relevantElementsOfStack = actualStack.stream()
                    .filter(item -> actualStack.indexOf(item) <= actualStack.indexOf(last.pointer))
                    .filter(item -> item.get() < last.get())
                    .collect(Collectors.toSet());
        }

        for (ObjectWithPointer<Integer> objectWithPointer : relevantElementsOfStack)
        {
            ArrayList<ObjectWithPointer<Integer>> nextSubsequence = newArrayList();
            nextSubsequence.addAll(actualSubseqence);
            nextSubsequence.add(objectWithPointer);
            findLongestSubsequences(stacks, longestSubseqeunces, nextSubsequence);
        }
    }

    @Test
    public void testLongestSubsequence() throws Exception
    {
        assertThat(longestSubsequence(newArrayList(3, 2, 6, 4, 5, 1))).containsOnly(newArrayList(2, 4, 5), newArrayList(3, 4, 5));
        assertThat(longestSubsequence(newArrayList(11, 9, 5, 4, 7, 3, 1))).containsOnly(newArrayList(5, 7), newArrayList(4, 7));
        assertThat(longestSubsequence(newArrayList())).containsOnly(newArrayList());
        assertThat(longestSubsequence(newArrayList(1))).containsOnly(newArrayList(1));
        assertThat(longestSubsequence(newArrayList(1, -1))).containsOnly(newArrayList(1), newArrayList(-1));
        assertThat(longestSubsequence(newArrayList(1, 2))).containsOnly(newArrayList(1, 2));
        assertThat(longestSubsequence(newArrayList(1, 1))).containsOnly(newArrayList(1));
        assertThat(longestSubsequence(newArrayList(1, 1, 2))).containsOnly(newArrayList(1, 2));
        assertThat(longestSubsequence(newArrayList(1, 9, 2, 2, 3))).containsOnly(newArrayList(1, 2, 3));
    }

    private static class ObjectWithPointer<O>
    {
        private O o;
        private ObjectWithPointer<O> pointer;

        public ObjectWithPointer(O o, ObjectWithPointer<O> pointer)
        {
            this.o = o;
            this.pointer = pointer;
        }

        public O get()
        {
            return o;
        }

        @Override
        public String toString()
        {
            return o + (pointer == null ? "" : " -> " + pointer.o);
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((o == null) ? 0 : o.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }
            if (obj == null)
            {
                return false;
            }
            if (getClass() != obj.getClass())
            {
                return false;
            }
            @SuppressWarnings("unchecked")
            ObjectWithPointer<O> other = (ObjectWithPointer<O>) obj;
            if (o == null)
            {
                if (other.o != null)
                {
                    return false;
                }
            }
            else if (!o.equals(other.o))
            {
                return false;
            }
            return true;
        }
    }
}