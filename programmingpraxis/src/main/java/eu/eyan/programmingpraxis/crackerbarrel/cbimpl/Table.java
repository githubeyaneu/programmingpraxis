package eu.eyan.programmingpraxis.crackerbarrel.cbimpl;

import static com.google.common.collect.Lists.newArrayList;
import static eu.eyan.programmingpraxis.crackerbarrel.cbimpl.Direction.*;
import static eu.eyan.programmingpraxis.crackerbarrel.cbimpl.State.*;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import eu.eyan.programmingpraxis.crackerbarrel.game.ITable;

@EqualsAndHashCode(exclude = { "levels" })
public class Table implements ITable
{
    public static final String NL = System.lineSeparator();

    @Getter
    private int levels;

    @Getter
    private List<Node> nodes = newArrayList();

    public static Table table(String states)
    {
        return new Table(states);
    }

    public static Table table(int levels, int numberOfFree)
    {
        return new Table(levels, numberOfFree);
    }

    private Table(String states)
    {
        this(states.split("_")[states.split("_").length - 1].length(), 1);
        for (int i = 1; i <= this.size(); i++)
        {
            this.get(i).setState(states.replace("_", "").charAt(i - 1) == 'F' ? FULL : EMPTY);
        }
    }

//    public static Table table(String states)
//    {
//        int nodes = 0;
//        int levels = 0;
//        while (nodes < states.replaceAll("_", "").length())
//        {
//            levels++;
//            nodes += levels;
//        }
//        return table(levels, states);
//    }

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
        this.get(numberOfFree).setState(State.EMPTY);
    }

    public void add(Node node)
    {
        nodes.add(node);
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
        int level = 1;
        String states = "";
        for (Node node : nodes)
        {
            if (node.getLevel() > level)
            {
                level++;
                states += "_";
            }
            states += node.getState().getTitle();
        }
        return states;
    }

    @Override
    public String getString()
    {
        String tableString = "";
        String lineBefore = "";
        String line = "";
        String lineAfter = "";
        int level = 1;
        for (Node node : nodes)
        {
            if (node.getLevel() > level)
            {
                tableString += lineBefore.trim() + NL + line.trim() + NL + lineAfter.trim() + NL;
                lineBefore = line = lineAfter = "";
                level = node.getLevel();
            }

            if (node.hasConnection(UP_LEFT))
            {
                lineBefore += "\\";
            }
            else if (node.getIndex() > 1)
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
            else if (node.getIndex() > 1)
            {
                line += " ";
            }
            line += node.getState();
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
        tableString += lineBefore.trim() + NL + line.trim() + NL + lineAfter.trim() + NL;
        return tableString;
    }

    public int numberOf(Node node)
    {
        return nodes.indexOf(node) + 1;
    }

    @Override
    public String toString()
    {
        return getStates();
    }
}