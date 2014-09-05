package eu.eyan.programmingpraxis.crackerbarrel;

import static com.google.common.collect.Lists.newArrayList;
import static eu.eyan.programmingpraxis.crackerbarrel.cbimpl.CrackerBarrelGame.game;
import static eu.eyan.programmingpraxis.crackerbarrel.cbimpl.Direction.*;
import static eu.eyan.programmingpraxis.crackerbarrel.cbimpl.Step.step;
import static eu.eyan.programmingpraxis.crackerbarrel.cbimpl.Table.*;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import eu.eyan.programmingpraxis.crackerbarrel.cbimpl.CrackerBarrelGame;
import eu.eyan.programmingpraxis.crackerbarrel.cbimpl.Step;
import eu.eyan.programmingpraxis.crackerbarrel.cbimpl.Table;
import eu.eyan.programmingpraxis.crackerbarrel.game.DummyAlgorithm;
import eu.eyan.programmingpraxis.crackerbarrel.game.IAlgorythm;
import eu.eyan.programmingpraxis.crackerbarrel.game.NonRepeatingAlgorythm;

/**
 * http://programmingpraxis.com/2014/08/19/cracker-barrel/
 * 
 * While I was out of town last week, I ate dinner one evening at Cracker Barrel,
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
public class CrackerBarrel
{
    private IAlgorythm<CrackerBarrelGame, Table, Step> dummyAlgorythm;
    private IAlgorythm<CrackerBarrelGame, Table, Step> nonRepeatingAlgorythm;

    @Before
    public void setUp()
    {
        dummyAlgorythm = new DummyAlgorithm<CrackerBarrelGame, Table, Step>();
        nonRepeatingAlgorythm = new NonRepeatingAlgorythm<CrackerBarrelGame, Table, Step>();
    }

    @Test
    public void testDummyAlgorythm() throws Exception
    {
        assertThat(dummyAlgorythm.findSolutions(game("E_FF_FFF_FFFF_FFFFF")).size()).isEqualTo(29760);
    }

    @Test
    public void testNonRepeatingAlgorythm() throws Exception
    {
        assertThat(nonRepeatingAlgorythm.findSolutions(game("E_FF_FFF_FFFF_FFFFF")).size()).isEqualTo(29760);
    }

    @Test
    public void testCirclePlusOneStep() throws Exception
    {
        CrackerBarrelGame game = game("E_EF_EEF_EEFF_EEEFE");
        System.out.println(game.getTable().getString());
        assertThat(dummyAlgorythm.findSolutions(game)).contains(
                newArrayList(step(6, DOWN_RIGHT), step(15, LEFT), step(13, UP_RIGHT), step(6, UP_LEFT)),
                newArrayList(step(6, DOWN_RIGHT), step(15, LEFT), step(13, UP_RIGHT), step(3, DOWN_RIGHT)),
                newArrayList(step(6, DOWN_LEFT), step(13, RIGHT), step(15, UP_LEFT), step(6, UP_LEFT)),
                newArrayList(step(6, DOWN_LEFT), step(13, RIGHT), step(15, UP_LEFT), step(3, DOWN_RIGHT))
                );
        assertThat(nonRepeatingAlgorythm.findSolutions(game)).contains(
                newArrayList(step(6, DOWN_RIGHT), step(15, LEFT), step(13, UP_RIGHT), step(6, UP_LEFT)),
                newArrayList(step(6, DOWN_RIGHT), step(15, LEFT), step(13, UP_RIGHT), step(3, DOWN_RIGHT)),
                newArrayList(step(6, DOWN_LEFT), step(13, RIGHT), step(15, UP_LEFT), step(6, UP_LEFT)),
                newArrayList(step(6, DOWN_LEFT), step(13, RIGHT), step(15, UP_LEFT), step(3, DOWN_RIGHT))
                );
    }

    @Test
    public void testSolutions() throws Exception
    {
        assertThat(nonRepeatingAlgorythm.findSolutions(game("F_EF_EEE"))).containsExactly(newArrayList(step(1, DOWN_RIGHT)));

        assertThat(dummyAlgorythm.findSolutions(game("F_FF_FFE"))).hasSize(0);

        assertThat(dummyAlgorythm.findSolutions(game("F_EF_EEE"))).containsExactly(newArrayList(step(1, DOWN_RIGHT)));

        assertThat(dummyAlgorythm.findSolutions(game("F_EF_EFE"))).containsExactly(
                newArrayList(step(1, DOWN_RIGHT), step(6, LEFT)));

        assertThat(dummyAlgorythm.findSolutions(game("F_FF_EFE"))).containsExactly(
                newArrayList(step(1, DOWN_RIGHT), step(6, LEFT), step(4, UP_RIGHT)),
                newArrayList(step(1, DOWN_LEFT), step(4, RIGHT), step(6, UP_LEFT)));
    }

    @Test
    public void testSolutionPossible() throws Exception
    {
        assertThat(dummyAlgorythm.solutionPossible(game("F_FF_FFE"))).isEqualTo(false);
        assertThat(dummyAlgorythm.solutionPossible(game("F_EF_EEE"))).isEqualTo(true);
    }

    @Test
    public void testWin() throws Exception
    {
        assertThat(game("F_FF_FFE").isWin()).isFalse();
        assertThat(game("E_EF_EEE").isWin()).isTrue();
    }

    @Test
    public void testDoStep() throws Exception
    {
        assertThat(game("F_FF_FFE").doStep(step(1, DOWN_RIGHT)).getTable().getStates()).isEqualTo("E_FE_FFF");
        assertThat(game("F_FF_FFE").doStep(step(4, RIGHT)).getTable().getStates()).isEqualTo("F_FF_EEF");
    }

    @Test
    public void testSteps() throws Exception
    {
        assertThat(game("F_FF_FFE").getSteps()).hasSize(2);
        assertThat(game("F_FF_FFE").getSteps()).contains(step(1, DOWN_RIGHT));
        assertThat(game("F_FF_FFE").getSteps()).contains(step(4, RIGHT));
    }

    @Test
    public void testTableCrateAndPrint() throws Exception
    {
        String expected = "";
        expected += "" + NL;
        expected += "F" + NL;
        expected += "|\\" + NL;
        expected += "| \\" + NL;
        expected += "F--F" + NL;
        expected += "|\\ |\\" + NL;
        expected += "| \\| \\" + NL;
        expected += "F--F--E" + NL;
        expected += "" + NL;
        assertThat(table("F_FF_FFE").getString()).isEqualTo(expected);

        expected = "";
        expected += "" + NL;
        expected += "F" + NL;
        expected += "|\\" + NL;
        expected += "| \\" + NL;
        expected += "E--F" + NL;
        expected += "|\\ |\\" + NL;
        expected += "| \\| \\" + NL;
        expected += "E--F--E" + NL;
        expected += "" + NL;
        assertThat(table("F_EF_EFE").getString()).isEqualTo(expected);
    }
}