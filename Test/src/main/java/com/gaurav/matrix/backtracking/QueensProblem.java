package com.gaurav.matrix.backtracking;

/**
 * N queens problem
 * 
 * @author Pavel Micka
 */
public class QueensProblem {

    public static void main(final String args[]) {
        new QueensProblem().solve(8);
    }

    private int solutionsCount = 0;

    /**
     * Check that no queen can attack another
     * 
     * @param array
     *            chessboard
     * @param x
     *            x coordinate where the new queen will be placed
     * @param y
     *            y coordinate where the new queen will be placed
     * @return true if the the model after queen placement is consistent, false otherwise
     */
    private boolean checkConsistency(final boolean[][] array, final int x, final int y) {
        // horizontally
        for (int i = 0; i < array[y].length; i++) {
            if (i != x && array[y][i] == true) {
                return false;
            }
        }
        // vertically
        for (int i = 0; i < array.length; i++) {
            if (i != y && array[i][x] == true) {
                return false;
            }
        }
        // diagonally left bottom to right top
        int i = 1;
        while (x + i < array[y].length && y - i >= 0) {
            if (array[y - i][x + i]) {
                return false;
            }
            i++;
        }
        i = 1;
        while (x - i >= 0 && y + i < array.length) {
            if (array[y + i][x - i]) {
                return false;
            }
            i++;
        }
        // diagonally left top, right bottom
        i = 1;
        while (x - i >= 0 && y - i >= 0) {
            if (array[y - i][x - i]) {
                return false;
            }
            i++;
        }
        i = 1;
        while (x + i < array[y].length && y + i < array.length) {
            if (array[y + i][x + i]) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * @return the solutionsCount
     */
    public int getSolutionsCount() {
        return solutionsCount;
    }

    /**
     * Print out the chessboard
     * 
     * @param array
     *            chessboard
     */
    private void printArray(final boolean[][] array) {
        System.out.println("Reseni #" + solutionsCount);
        for (int i = 0; i < array.length; i++) {
            System.out.print("\n|");
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j]) {
                    System.out.print("Q|");
                } else {
                    System.out.print(" |");
                }
            }
        }
        System.out.println("\n---------------------");
    }

    /**
     * Solves and prints out solution of the n queens problem
     * 
     * @param size
     *            size of the problem
     */
    public void solve(final int size) {
        final boolean[][] array = new boolean[size][size];
        solveQueensProblem(array, 0, 0, true, 0);
        solveQueensProblem(array, 0, 0, false, 0);
    }

    /**
     * Solves the n queens problem
     * 
     * @param array
     *            chessboard
     * @param x
     *            x coordinate where to place the current queen
     * @param y
     *            y coordinate where to place the current queen
     * @param set
     *            true if the queen should be placed, false otherwise
     * @param queensCount
     *            how many queens are already placed
     */
    private void solveQueensProblem(final boolean[][] array, int x, int y, final boolean set, int queensCount) {
        array[y][x] = set;
        // queen could be attacked
        if (set && !checkConsistency(array, x, y)) {
            return;
        }
        // set the queen
        if (set) {
            queensCount++;
        }

        // solution found , print array
        if (queensCount == array.length) {
            solutionsCount++;
            printArray(array);
            return; // solution found
        }

        x = x + 1; // increment x
        final int overflow = x == array[y].length ? 1 : 0;
        if (overflow == 1) {// if x overflowed
            x = 0; // set to 0
            y += 1; // increment y
        }

        if (y >= array.length) {
            return; // end of the problem, all decisions have been made
        }
        // lets make both decisions
        solveQueensProblem(array, x, y, true, queensCount);
        solveQueensProblem(array, x, y, false, queensCount);

    }
}
