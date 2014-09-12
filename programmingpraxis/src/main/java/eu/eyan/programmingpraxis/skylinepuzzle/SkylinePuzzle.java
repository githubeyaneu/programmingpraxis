package eu.eyan.programmingpraxis.skylinepuzzle;

import static com.google.common.collect.Lists.newArrayList;
import static eu.eyan.programmingpraxis.skylinepuzzle.SkylinePuzzle.Building.building;
import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import lombok.AllArgsConstructor;

import org.junit.Test;

/**
 * http://programmingpraxis.com/2014/09/05/skyline-puzzle/
 * 
 * The Skyline Puzzle is a classic programming exercise;
 * it draws a silhouette of a city skyline by blocking out portions of buildings that are masked by taller buildings.
 * A city is a list of buildings specified as triples containing left edge, height, and right edge.
 * For instance, the list of triples (1 11 5) (2 6 7) (3 13 9) (12 7 16) (14 3 25) (19 18 22) (23 13 29) (24 4 28)
 * encodes the eight buildings shown at the left of the diagram,
 * and the path 1 11 3 13 9 0 12 7 16 3 19 18 22 3 23 13 29 0 encodes the skyline shown at the right of the diagram,
 * where the odd-indexed elements of the output are the x-coordinate of the skyline
 * and the even-indexed elements of the output are the y-coordinate of the skyline.
 * (It makes more sense to me that the output should look like
 * (1 11) (3 13) (9 0) (12 7) (16 3) (19 18) (22 3) (23 13) (29 0) but that’s not the way the puzzle is ever specified.)
 * Notice that the second (2 6 7) and eighth (24 4 28) buildings are not part of the skyline.
 * 
 * Your task is to write a program that takes a list of buildings and returns a skyline.
 * 
 */
public class SkylinePuzzle
{
    @Test
    public void testSkylinePuzzle() throws Exception
    {
        assertThat(skyLinePuzzle(newArrayList(
                building(1, 11, 5)
                , building(2, 6, 7)
                , building(3, 13, 9)
                , building(12, 7, 16)
                , building(14, 3, 25)
                , building(19, 18, 22)
                , building(23, 13, 29)
                , building(24, 4, 28)))).isEqualTo(
                newArrayList(
                        1, 11
                        , 3, 13
                        , 9, 0
                        , 12, 7
                        , 16, 3
                        , 19, 18
                        , 22, 3
                        , 23, 13
                        , 29, 0));
    }

    public static List<Integer> skyLinePuzzle(List<Building> buildings)
    {
        int start = buildings.stream().map(b -> b.start).min(Integer::compareTo).orElse(0);
        int end = buildings.stream().map(b -> b.end).max(Integer::compareTo).orElse(start);
        List<Integer> result = newArrayList();
        int lastHeight = -1;
        for (int i = start; i <= end; i++)
        {
            final int index = i;
            int height = buildings.stream().filter(b -> b.start <= index && index < b.end).map(b -> b.height).max(Integer::compareTo).orElse(0);
            if (height != lastHeight)
            {
                result.add(index);
                result.add(height);
                lastHeight = height;
            }
        }
        return result;
    }

    @AllArgsConstructor
    public static class Building
    {
        private int start;
        private int height;
        private int end;

        static Building building(int start, int height, int end)
        {
            return new Building(start, height, end);
        }
    }
}
