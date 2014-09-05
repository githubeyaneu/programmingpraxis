package eu.eyan.programmingpraxis.crackerbarrel.crackerbarrelallinone;

import static com.google.common.collect.Lists.newArrayList;
import static eu.eyan.programmingpraxis.crackerbarrel.crackerbarrelallinone.AllInOne.Direction.*;
import static eu.eyan.programmingpraxis.crackerbarrel.crackerbarrelallinone.AllInOne.State.*;
import static eu.eyan.programmingpraxis.crackerbarrel.crackerbarrelallinone.AllInOne.Step.step;
import static eu.eyan.programmingpraxis.crackerbarrel.crackerbarrelallinone.AllInOne.Table.table;
import static org.fest.assertions.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

/*
 While I was out of town last week, I ate dinner one evening at Cracker Barrel,
 * which provides a triangle puzzle at each table so you can amuse yourself while you are waiting for your food.
 * When my daughter challenged me to solve the problem, I failed, but I promised her that I would write a program to solve it.
 * 
 * As shown in the picture at right, the puzzle is a triangle with 15 holes and 14 pegs;
 * one hole is initially vacant.
 * The game is played by making 13 jumps;
 * each jump removes one peg from the triangle, so at the end of 13 jumps there is one peg remaining.
 * A jump takes a peg from a starting hole, over an occupied hole, to an empty finishing hole, removing the intermediate peg.
 * 
 * Your task is to write a program that solves the Cracker Barrel puzzle;
 * find all possible solutions that start with a corner hole vacant and end with the remaining peg in the original vacant corner.
 *
 */
public class AllInOne
{

    private static final String SEPA = System.lineSeparator();

    @Test
    public void testBigTable() throws Exception
    {
        Table bigTable = table(5, 1);
        printTable(bigTable);
        System.out.println("Possible: " + solutionPossible(bigTable));
        List<List<Step>> solutions = solutions(bigTable, list(), list());
        System.out.println("Solutions: " + solutions.size());
//        printSolutions(bigTable, list());

    }

    @Test
    public void testSolutions() throws Exception
    {
        assertThat(solutions(table(3, "FFFFFE"), list(), list())).hasSize(0);
        assertThat(solutions(table(3, "FEFEEE"), list(), list())).containsExactly(newArrayList(step(1, DOWN_RIGHT)));
        assertThat(solutions(table(3, "FEFEFE"), list(), list())).containsExactly(
                newArrayList(step(1, DOWN_RIGHT), step(6, LEFT)));
    }

    public static void printSolutions(Table table, List<Step> actualPath)
    {
        if (isWin(table))
        {
            System.out.println(actualPath);
        }
        else
        {
            for (Step step : getSteps(table))
            {
                List<Step> nextPath = newArrayList(actualPath);
                nextPath.add(step);
                Table nextTable = doStep(table, step);
                printSolutions(nextTable, nextPath);
            }
        }
    }

    private List<List<Step>> solutions(Table table, List<Step> actualPath, List<List<Step>> solutions)
    {
        if (isWin(table))
        {
            solutions.add(actualPath);
        }
        else
        {
            for (Step step : getSteps(table))
            {
                List<Step> nextPath = newArrayList(actualPath);
                nextPath.add(step);
                Table nextTable = doStep(table, step);
                solutions(nextTable, nextPath, solutions);
            }
        }
        return solutions;
    }

    @Test
    public void testSolutionPossible() throws Exception
    {
        assertThat(solutionPossible(table(3, "FFFFFE"))).isEqualTo(false);
        assertThat(solutionPossible(table(3, "FEFEEE"))).isEqualTo(true);
    }

    private boolean solutionPossible(Table table)
    {
        if (isWin(table))
        {
            return true;
        }
        else
        {
            List<Step> steps = getSteps(table);
            if (steps.size() == 0)
            {
                return false;
            }
            else
            {
                boolean possible = false;
                for (Step step : steps)
                {
                    possible = possible || solutionPossible(doStep(table, step));
                }
                return possible;
            }
        }
    }

    @Test
    public void testWin() throws Exception
    {
        assertThat(isWin(table(3, "FFFFFE"))).isEqualTo(false);
        assertThat(isWin(table(3, "EEFEEE"))).isEqualTo(true);
    }

    private static boolean isWin(Table table)
    {
        // return table.nodes.stream().map(n -> (n.state == FULL ? 1 : 0)).reduce((i, j) -> i + j).get() == 1;
        return table.nodes.stream().filter(n -> n.state == FULL).count() == 1;
    }

    @Test
    public void testDoStep() throws Exception
    {
        assertThat(doStep(table(3, "FFFFFE"), step(1, DOWN_RIGHT)).getStates()).isEqualTo("EFEFFF");
        assertThat(doStep(table(3, "FFFFFE"), step(4, RIGHT)).getStates()).isEqualTo("FFFEEF");
    }

    private static Table doStep(Table table, Step step)
    {
        Table newTable = table(table.levels, table.getStates());
        newTable.get(step.from).state = EMPTY;
        newTable.get(step.from).getConnection(step.direction).state = EMPTY;
        newTable.get(step.from).getConnection(step.direction).getConnection(step.direction).state = FULL;
        return newTable;
    }

    @Test
    public void testSteps() throws Exception
    {
        assertThat(getSteps(table(3, "FFFFFE"))).hasSize(2);
        assertThat(getSteps(table(3, "FFFFFE"))).contains(step(1, DOWN_RIGHT));
        assertThat(getSteps(table(3, "FFFFFE"))).contains(step(4, RIGHT));
    }

    @Test
    public void testTableCrateAndPrint() throws Exception
    {
        String expected = "";
        expected += "" + SEPA;
        expected += "F" + SEPA;
        expected += "|\\" + SEPA;
        expected += "| \\" + SEPA;
        expected += "F--F" + SEPA;
        expected += "|\\ |\\" + SEPA;
        expected += "| \\| \\" + SEPA;
        expected += "F--F--E" + SEPA;
        expected += "" + SEPA;
        assertThat(printTable(table(3, 6))).isEqualTo(expected);

        expected = "";
        expected += "" + SEPA;
        expected += "F" + SEPA;
        expected += "|\\" + SEPA;
        expected += "| \\" + SEPA;
        expected += "E--F" + SEPA;
        expected += "|\\ |\\" + SEPA;
        expected += "| \\| \\" + SEPA;
        expected += "E--F--E" + SEPA;
        expected += "" + SEPA;
        assertThat(printTable(table(3, "FEFEFE"))).isEqualTo(expected);
    }

    private static List<Step> getSteps(Table table)
    {
        List<Step> steps = list();
        for (Node node : table.nodes)
        {
            for (Direction direction : Direction.values())
            {
                if (node.state == FULL && node.getConnection(direction) != null
                        && node.getConnection(direction).state == FULL
                        && node.getConnection(direction).getConnection(direction) != null
                        && node.getConnection(direction).getConnection(direction).state == EMPTY)
                {
                    steps.add(step(table.numberOf(node), direction));
                }
            }
        }
        return steps;
    }

    public static class Table
    {

        private int levels;

        public static Table table(int levels, String states)
        {
            return new Table(levels, states);
        }

        public static Table table(int levels, int numberOfFree)
        {
            return new Table(levels, numberOfFree);
        }

        private Table(int levels, String states)
        {
            this(levels, 1);
            for (int i = 1; i <= this.size(); i++)
            {
                this.get(i).state = states.charAt(i - 1) == 'F' ? FULL : EMPTY;
            }
        }

        private Table(int levels, int numberOfFree)
        {
            this.levels = levels;
            for (int level = 1; level <= levels; level++)
            {
                for (int item = 1; item <= level; item++)
                {
                    Node node = new Node(level, item);
                    this.add(node);
                    if (item > 1)
                    {
                        node.addConnection(LEFT, this.get(this.size() - 1));
                        (this.get(this.size() - 1)).addConnection(RIGHT, node);
                    }
                    if (item > 1 && level > 1)
                    {
                        node.addConnection(UP_LEFT, this.get(this.size() - level));
                        (this.get(this.size() - level)).addConnection(DOWN_RIGHT, node);
                    }
                    if (item < level && level > 1)
                    {
                        node.addConnection(UP_RIGHT, this.get(this.size() - level + 1));
                        (this.get(this.size() - level + 1)).addConnection(DOWN_LEFT, node);
                    }
                }
            }
            this.get(numberOfFree).state = State.EMPTY;
        }

        List<Node> nodes = list();

        public void add(Node node)
        {
            nodes.add(node);
        }

        public int numberOf(Node node)
        {
            return nodes.indexOf(node) + 1;
        }

        public int size()
        {
            return nodes.size();
        }

        public Node get(int numberOfFree)
        {
            return nodes.get(numberOfFree - 1);
        }

        public String getStates()
        {
            String states = "";
            for (Node node : nodes)
            {
                states += node.state.title;
            }
            return states;
        }
    }

    private String printTable(Table table)
    {
        String tableString = "";
        String lineBefore = "";
        String line = "";
        String lineAfter = "";
        int level = 1;
        for (Node node : table.nodes)
        {
            if (node.level > level)
            {
                tableString += lineBefore.trim() + SEPA + line.trim() + SEPA + lineAfter.trim() + SEPA;
                lineBefore = line = lineAfter = "";
                level = node.level;
            }

            if (node.hasConnection(UP_LEFT))
            {
                lineBefore += "\\";
            }
            else if (node.index > 1)
            {
                lineBefore += " ";
            }
            if (node.hasConnection(UP_RIGHT))
            {
                lineBefore += "|";
            }
            else
            {
                lineBefore += " ";
            }
            lineBefore += " ";

            if (node.hasConnection(LEFT))
            {
                line += "-";
            }
            else if (node.index > 1)
            {
                line += " ";
            }
            line += node.state;
            if (node.hasConnection(RIGHT))
            {
                line += "-";
            }
            else
            {
                line += " ";
            }

            if (node.hasConnection(DOWN_LEFT))
            {
                lineAfter += "|";
            }
            else
            {
                lineAfter += " ";
            }
            if (node.hasConnection(DOWN_RIGHT))
            {
                lineAfter += "\\ ";
            }
            else
            {
                lineAfter += "  ";
            }
        }
        tableString += lineBefore.trim() + SEPA + line.trim() + SEPA + lineAfter.trim() + SEPA;
        System.out.println(tableString);
        return tableString;
    }

    public static class Node
    {

        private int level;
        private int index;
        private State state;
        private Map<Direction, Node> map = Maps.newHashMap();

        public Node(int level, int item)
        {
            this.level = level;
            this.index = item;
            state = FULL;
        }

        public boolean hasConnection(Direction direction)
        {
            return map.containsKey(direction);
        }

        public Node getConnection(Direction direction)
        {
            return map.get(direction);
        }

        public void addConnection(Direction direction, Node node)
        {
            map.put(direction, node);
        }
    }

    public static enum Direction
    {
        LEFT,
        UP_LEFT,
        UP_RIGHT,
        RIGHT,
        DOWN_RIGHT,
        DOWN_LEFT;
    }

    public static enum State
    {
        FULL("F"),
        EMPTY("E");

        private String title;

        State(String s)
        {
            this.title = s;
        }

        @Override
        public String toString()
        {
            return title;
        }
    }

    public static class Step
    {

        private int from;
        private Direction direction;

        public static Step step(int from, Direction direction)
        {
            return new Step(from, direction);
        }

        private Step(int from, Direction direction)
        {
            this.from = from;
            this.direction = direction;
        }

        @Override
        public String toString()
        {
            return from + "->" + direction;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (obj instanceof Step)
            {
                Step other = (Step) obj;
                if (from == other.from && direction == other.direction)
                {
                    return true;
                }
            }
            return super.equals(obj);
        }

        @Override
        public int hashCode()
        {
            return from + direction.hashCode() * 31;
        }
    }

    private static <E> List<E> list()
    {
        return newArrayList();
    }
}