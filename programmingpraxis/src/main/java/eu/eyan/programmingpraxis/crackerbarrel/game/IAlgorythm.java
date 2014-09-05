package eu.eyan.programmingpraxis.crackerbarrel.game;

import java.util.List;

public interface IAlgorythm<GAME extends IGame<GAME, TABLE, STEP>, TABLE extends ITable, STEP extends IStep>
{
    boolean solutionPossible(GAME game);

    List<List<STEP>> findSolutions(GAME game);
}