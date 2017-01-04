package com.nimbuzz.algo.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * The knight's tour is a chess problem, whose goal is to visit exactly once all squares of an empty chessboard using
 * the knight piece. This puzzle is well known since the middle ages – it was described by arab scholar Al-Adli in his
 * work Kitab ash-shatranj (Book of chess).
 * 
 * The knight's tour has a surprisingly high number of solutions. For a common chessboard (8x8 squares), there exist 33
 * 439 123 484 294 unoriented paths, through which the knight can go.
 * 
 * 
 * The most simple solution to this puzzle is backtracking algorithm. Naive backtracking tends to be slow, because it
 * can easily reach dead-end and has to reevaluate many decisions.
 * 
 * We can optimize the naive algorithm using the Warnsdorff's rule. When the knight has to choose next step, it will
 * always proceed to the square, from which it has the fewest onwards moves. This heuristic reduces the probability that
 * the knight won't be able to visit some square.
 * 
 * Backtracking Knight's tour solver
 * 
 */
public class KnightsTour {

    /**
     * Indicator that the square was not visited yet
     */
    private static int NOT_VISITED = -1;
    /**
     * Width of the chessboard
     */
    private final int cols;
    /**
     * Height of the chessboard
     */
    private final int rows;
    /**
     * Numver of solutions
     */
    private int solutionsCount;
    /**
     * Solution board 0 -> Initial position of the knight 1 -> first move 2 -> second move . . . n -> n-th move
     */
    private final int[][] solutionBoard;

    public static void main(final String[] args) {
        // for (int i = 1; i < 5; i++) {
        // final KnightsTour kt = new KnightsTour(3, i);
        // kt.solve();
        // System.out.println("<td>" + kt.solutionsCount + "</td>" + "row = " + 3 + " Col = " + i);
        // }

        final KnightsTour kt = new KnightsTour(3, 4);
        kt.solve();
    }

    /**
     * Constructor
     * 
     * @param cols
     *            width of the chessboard
     * @param rows
     *            height of the chessboard
     */
    public KnightsTour(final int cols, final int rows) {
        solutionsCount = 0;

        this.cols = cols;
        this.rows = rows;

        solutionBoard = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                solutionBoard[i][j] = NOT_VISITED;
            }
        }
    }

    /**
     * Solve the knight's tour
     */
    public void solve() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                takeTurn(row, col, 0);
                // BACKTRACK
                solutionBoard[row][col] = NOT_VISITED; // reset the square
            }
        }

        // takeTurn(0, 0, 0);
        // solutionBoard[0][0] = NOT_VISITED;
    }

    /**
     * Return possible destinations of the knight
     * 
     * @param col
     *            x coord of the knight
     * @param row
     *            y coord of the knight
     * @return possible destinations of the knight
     */
    private List<Coords> getFields(final int col, final int row) {
        final List<Coords> l = new ArrayList<Coords>();
        if (col + 2 < cols && row - 1 >= 0) {
            l.add(new Coords(col + 2, row - 1)); // right and upward
        }
        if (col + 1 < cols && row - 2 >= 0) {
            l.add(new Coords(col + 1, row - 2)); // upward and right
        }
        if (col - 1 >= 0 && row - 2 >= 0) {
            l.add(new Coords(col - 1, row - 2)); // upward and left
        }
        if (col - 2 >= 0 && row - 1 >= 0) {
            l.add(new Coords(col - 2, row - 1)); // left and upward
        }
        if (col - 2 >= 0 && row + 1 < rows) {
            l.add(new Coords(col - 2, row + 1)); // left and downward
        }
        if (col - 1 >= 0 && row + 2 < rows) {
            l.add(new Coords(col - 1, row + 2)); // downward and left
        }
        if (col + 1 < cols && row + 2 < rows) {
            l.add(new Coords(col + 1, row + 2)); // downward and right
        }
        if (col + 2 < cols && row + 1 < rows) {
            l.add(new Coords(col + 2, row + 1)); // right and downward
        }
        return l;
    }

    /**
     * Perform the move
     * @param row
     *            destination y coord
     * @param col
     *            destination x coord
     * @param turnNr
     *            number of the move
     */
    private void takeTurn(final int row, final int col, final int turnNr) {
        solutionBoard[row][col] = turnNr;
        if (turnNr == cols * rows - 1) {
            solutionsCount++;
            printSolution();
            return;
        } else {
            final List<Coords> possibleMoves = getFields(col, row);
            for (final Coords c : possibleMoves) {
                if (solutionBoard[c.getRow()][c.getCol()] == NOT_VISITED) {
                    takeTurn(c.getRow(), c.getCol(), turnNr + 1);
                    // When we have found any solution or No move exists to above problem from current row and col,
                    // recursion method call chain will start returning. calling below line to reset square.
                    // on return we start reseting states to NOT_VISITED ,so that code could check if there is another
                    // possible solution out there from row, and col current position
                    // BACKTRACK
                    solutionBoard[c.getRow()][c.getCol()] = NOT_VISITED; // reset the square
                }
            }
        }
    }

    /**
     * Print out the solution
     */
    private void printSolution() {
        System.out.println("Solution #" + solutionsCount);
        for (int i = 0; i < solutionBoard.length; i++) {
            for (int j = 0; j < solutionBoard[i].length; j++) {
                System.out.print(solutionBoard[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * @return the solutionsCount
     */
    public int getSolutionsCount() {
        return solutionsCount;
    }

    /**
     * Represents coordinates
     */
    private class Coords {
        private final int col;
        private final int row;

        public Coords(final int x, final int y) {
            col = x;
            row = y;
        }

        /**
         * @return the x
         */
        public int getCol() {
            return col;
        }

        /**
         * @return the y
         */
        public int getRow() {
            return row;
        }

        @Override
        public String toString() {
            return "Coords [x=" + col + ", y=" + row + "]";
        }

    }
}
