

import java.util.Random;

/**
 * This program achieves moveLeft() method in the game 2048 and 
 * tests the method in the main() method using random examples.
 * @author Chengyao Li
 *
 */
public class MoveLeft {
    /**
     * Perform a move in direction left on row b[i].
     * Return the points gained for this move (-1 if no move made).1. I deleted two lines in the .
     * 
     * @param b a square matrix in the game 2048
     * @param i row i in the matrix b
     * @return  -1 when moveMade is false and score when moveMade is true
     */
    public int moveLeft(int[][] b, int i) {
        int h = 0;
        int score = 0;
        boolean moveMade = false;
        
        /* Loop invariant:
         *        -0-----------h---------j----------------------
         * P b[i] |##merged###<x>00000000??????????????????????|
         *        ----------------------------------------------
         * #: has its final value and cannot be changed again
         * <x>: the next value to be merged
         * 0: blanks
         * ?: wait to be merged
         * score: points produced in the move
         * moveMade: becomes true if a position change happens  */
        
        for (int j = 1; j < b[i].length; j++) {
            if (b[i][j] == 0) continue; /* b[i][j] = 0, skip blanks */
            if (b[i][j] == b[i][h]) { /* b[i][j] = x, merge */
                b[i][h] += b[i][j];
                b[i][j] = 0;
                score += b[i][h++];
                moveMade = true;
            }
            else {
                if (b[i][h] == 0) { /* x = 0, swap b[i][j] and x */
                    b[i][h] = b[i][j];
                    b[i][j] = 0;
                }
                else { /* x != 0, x has reached its final value */
                    h= h+1;
                    if(h == j) {  /* no move made */
                    }
                    else {
                        b[i][h] = b[i][j];
                        b[i][j] = 0;
                        moveMade = true;
                    }
                }
            }
        }
        return moveMade ? score : -1;
    }
    
    /**
     * Use random values to test the moveLeft() method.
     * @param args
     */
    public static void main(String args[]) {
        MoveLeft test = new MoveLeft();
        Random rand = new Random();
        
        /* The scale of the matrix b is randomized to [4, 7] */
        int range = rand.nextInt(4) + 4;
        int[][] b = new int[range][range];
        
        /* The value of each element is also randomized to {0, 2, 4, 8, 16} */
        System.out.printf("0:");
        for (int i = 0; i < b[0].length; i++) {
            int tmp = rand.nextInt(5);
            b[0][i] = (int) ((tmp == 0) ? 0 : Math.pow(2.0, tmp));
            System.out.printf(" %d", b[0][i]);
        }
        
        /* Print the result of the left move */
        System.out.printf("\n");
        System.out.printf("%d:", test.moveLeft(b, 0));
        for (int j = 0; j < b[0].length; j++)
            System.out.printf(" %d", b[0][j]);
        System.out.printf("\n");
    }
}
