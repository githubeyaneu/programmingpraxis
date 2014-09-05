package eu.eyan.programmingpraxis.crackerbarrel.cbimpl;

import static eu.eyan.programmingpraxis.crackerbarrel.cbimpl.State.FULL;

import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import com.google.common.collect.Maps;

@EqualsAndHashCode(of = { "level", "index", "state" })
public class Node
{

    @Getter
    private final int level;

    @Getter
    private final int index;

    @Getter
    @Setter
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