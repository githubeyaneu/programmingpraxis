package eu.eyan.programmingpraxis.crackerbarrel.game;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;

public class NonRepeatingAlgorythm<GAME extends IGame<GAME, TABLE, STEP>, TABLE extends ITable, STEP extends IStep> implements IAlgorythm<GAME, TABLE, STEP>
{

    @Override
    public boolean solutionPossible(GAME game)
    {
        return false;
    }

    @Override
    public List<List<STEP>> findSolutions(GAME game)
    {
        return solutions(game, newHashMap(), "");
    }

    private List<List<STEP>> solutions(GAME game, Map<GAME, List<List<STEP>>> foundSolutions, String level)
    {
        if (foundSolutions.containsKey(game))
        {
            return foundSolutions.get(game);
        }
        if (game.isWin())
        {
            return newArrayList();
        }
        List<List<STEP>> solutions = null;
        for (STEP step : game.getSteps())
        {
            GAME nextGame = game.doStep(step);
            List<List<STEP>> nextGameSolutions = solutions(nextGame, foundSolutions, level + "  ");
            if (nextGameSolutions != null)
            {
                if (nextGameSolutions.isEmpty())
                {
                    if (solutions == null)
                    {
                        solutions = newArrayList();
                    }
                    List<STEP> solution = newArrayList();
                    solution.add(step);
                    solutions.add(solution);
                }
                else
                {
                    for (List<STEP> nextTableSolution : nextGameSolutions)
                    {
                        if (solutions == null)
                        {
                            solutions = newArrayList();
                        }
                        List<STEP> solution = newArrayList();
                        solution.add(step);
                        solution.addAll(nextTableSolution);
                        solutions.add(solution);
                    }
                }
            }
        }
        foundSolutions.put(game, solutions);
        return solutions;
    }
}