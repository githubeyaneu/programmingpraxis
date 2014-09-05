package eu.eyan.programmingpraxis.crackerbarrel.game;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

public class DummyAlgorithm<GAME extends IGame<GAME, TABLE, STEP>, TABLE extends ITable, STEP extends IStep> implements IAlgorythm<GAME, TABLE, STEP>
{
    @Override
    public boolean solutionPossible(GAME game)
    {
        if (game.isWin())
        {
            return true;
        }
        else
        {
            List<STEP> steps = game.getSteps();
            if (steps.size() == 0)
            {
                return false;
            }
            else
            {
                boolean possible = false;
                for (STEP step : steps)
                {
                    possible = possible || solutionPossible(game.doStep(step));
                }
                return possible;
            }
        }
    }

    @Override
    public List<List<STEP>> findSolutions(GAME game)
    {
        return solutions(game, newArrayList(), newArrayList());
    }

    private List<List<STEP>> solutions(GAME game, List<STEP> actualPath, List<List<STEP>> solutions)
    {
        if (game.isWin())
        {
            solutions.add(actualPath);
        }
        else
        {
            for (STEP step : game.getSteps())
            {
                List<STEP> nextPath = newArrayList(actualPath);
                nextPath.add(step);
                GAME nextGame = game.doStep(step);
                solutions(nextGame, nextPath, solutions);
            }
        }
        return solutions;
    }
}