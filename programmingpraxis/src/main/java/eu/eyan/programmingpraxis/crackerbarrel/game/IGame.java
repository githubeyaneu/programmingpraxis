package eu.eyan.programmingpraxis.crackerbarrel.game;

import java.util.List;

public interface IGame<GAME extends IGame<GAME, TABLE, STEP>, TABLE extends ITable, STEP extends IStep>
{
    GAME doStep(STEP step);

    boolean isWin();

    List<STEP> getSteps();

    void setTable(TABLE table);
}