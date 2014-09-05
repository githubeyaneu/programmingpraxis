package eu.eyan.programmingpraxis.crackerbarrel.cbimpl;

import static com.google.common.collect.Lists.newArrayList;
import static eu.eyan.programmingpraxis.crackerbarrel.cbimpl.State.*;
import static eu.eyan.programmingpraxis.crackerbarrel.cbimpl.Step.step;
import static eu.eyan.programmingpraxis.crackerbarrel.cbimpl.Table.table;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import eu.eyan.programmingpraxis.crackerbarrel.game.IGame;

@ToString
@EqualsAndHashCode
public class CrackerBarrelGame implements IGame<CrackerBarrelGame, Table, Step>
{
    @Getter
    @Setter
    private Table table;

    public static CrackerBarrelGame game()
    {
        return new CrackerBarrelGame();
    }

    public static CrackerBarrelGame game(String tableStates)
    {
        CrackerBarrelGame game = new CrackerBarrelGame();
        game.setTable(table(tableStates));
        return game;
    }

    @Override
    public CrackerBarrelGame doStep(Step step)
    {
        Table newTable = table(table.getStates());
        newTable.get(step.getFrom()).setState(EMPTY);
        newTable.get(step.getFrom()).getConnection(step.getDirection()).setState(EMPTY);
        newTable.get(step.getFrom()).getConnection(step.getDirection()).getConnection(step.getDirection()).setState(FULL);
        CrackerBarrelGame newGame = new CrackerBarrelGame();
        newGame.setTable(newTable);
        return newGame;
    }

    @Override
    public List<Step> getSteps()
    {
        List<Step> steps = newArrayList();
        for (Node node : table.getNodes())
        {
            for (Direction direction : Direction.values())
            {
                if (node.getState() == FULL && node.getConnection(direction) != null
                        && node.getConnection(direction).getState() == FULL
                        && node.getConnection(direction).getConnection(direction) != null
                        && node.getConnection(direction).getConnection(direction).getState() == EMPTY)
                {
                    steps.add(step(table.numberOf(node), direction));
                }
            }
        }
        return steps;
    }

    @Override
    public boolean isWin()
    {
        return table.getNodes().stream().filter(n -> n.getState() == FULL).count() == 1;
    }
}