package eu.eyan.programmingpraxis.minimumimpossiblesum;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

import java.util.List;
import java.util.SortedSet;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class MinimumImpossibleSum
{

    @Test
    public void testName() throws Exception
    {
        assertThat(MinimumImpossibleSum.minimumImpossibleSum(newArrayList(4, 13, 2, 3, 1))).isEqualTo(11);
    }

    private static int minimumImpossibleSum(List<Integer> inputs)
    {
        SortedSet<Integer> sums = Sets.newTreeSet();
        List<Integer> news = Lists.newArrayList();
        for (Integer input : inputs)
        {
            news.clear();
            news.add(input);
            for (Integer sum : sums)
            {
                news.add(sum + input);
            }
            sums.addAll(news);
        }
        int min = 1;
        while (sums.contains(min))
        {
            min++;
        }
        return min;
    }
}
