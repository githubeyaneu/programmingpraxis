package eu.eyan.programmingpraxis.drawingdiamonds;

import org.fest.assertions.Assertions;
import org.junit.Test;

/**
 * http://programmingpraxis.com/2014/09/09/drawing-diamonds/
 * 
 * Given a small positive integer n, write a function that draws a diamond, either filled or in outline as specified by the user. 
 * For instance, here are filled and outline diamonds for n = 5:
 *
 *          *              *
 *         * *            * *
 *        * * *          *   *
 *       * * * *        *     *
 *      * * * * *      *       *
 *       * * * *        *     *
 *        * * *          *   *
 *         * *            * *
 *          *              *
 *                  
 *
 * Note that there is a single space between asterisks in the filled version of the diamond.
 * 
 * Your task is to write a program that draws diamonds as described above. 
 *
 */
public class DrawingDiamonds {

    private static final String S = System.lineSeparator();

    @Test
    public void testDrawDiamond() throws Exception {
        Assertions.assertThat(drawDiamond(1, false)).isEqualTo("*" + S);

        Assertions.assertThat(drawDiamond(2, false)).isEqualTo(
            new StringBuilder().append(" * " + S).append("* *" + S).append(" * " + S).toString());

        Assertions.assertThat(drawDiamond(3, false)).isEqualTo(
            new StringBuilder().append("  *  " + S)
                               .append(" * * " + S)
                               .append("*   *" + S)
                               .append(" * * " + S)
                               .append("  *  " + S)
                               .toString());

        Assertions.assertThat(drawDiamond(1, true)).isEqualTo("*" + S);

        Assertions.assertThat(drawDiamond(2, true)).isEqualTo(
            new StringBuilder().append(" * " + S).append("***" + S).append(" * " + S).toString());

        Assertions.assertThat(drawDiamond(3, true)).isEqualTo(
            new StringBuilder().append("  *  " + S)
                               .append(" *** " + S)
                               .append("*****" + S)
                               .append(" *** " + S)
                               .append("  *  " + S)
                               .toString());

        drawDiamond(1, true);
        drawDiamond(2, true);
        drawDiamond(3, true);
        drawDiamond(4, true);
        drawDiamond(5, true);
    }

    public static String drawDiamond(int size, boolean filled) {
        String diamond = "";
        for (int y = 1 - size; y <= (size - 1); y++) {
            for (int x = 1 - size; x <= size - 1; x++) {
                if (filled) {
                    if ((x + size - 1 >= -y) && (x + size - 1 >= y) && (x - size + 1 <= -y) && (x - size + 1 <= y)) {
                        diamond += "*";
                    } else {
                        diamond += " ";
                    }
                } else {
                    if ((x + size - 1 == -y) || (x + size - 1 == y) || (x - size + 1 == -y) || (x - size + 1 == y)) {
                        diamond += "*";
                    } else {
                        diamond += " ";
                    }
                }
            }
            diamond += S;
        }
        return diamond;
    }
}