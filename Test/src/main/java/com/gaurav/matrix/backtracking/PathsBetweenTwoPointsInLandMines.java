package com.gaurav.matrix.backtracking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * http://www.geeksforgeeks.org/longest-possible-route-in-a-matrix-with-hurdles/
 * 
 * Given an M x N matrix, with a few hurdles arbitrarily placed, calculate the length of longest possible route possible
 * from source to destination within the matrix. We are allowed to move to only adjacent cells which are not hurdles.
 * The route cannot contains any diagonal moves and a location once visited in a particular path cannot be visited
 * again.
 * 
 * For example, longest path with no hurdles from source to destination is highlighted for below matrix. The length of
 * the path is 24.
 * 
 * Matrix
 * 
 * The idea is to use Backtracking. We start from the source cell of the matrix, move forward in all four allowed
 * directions and recursively checks if they leads to the solution or not. If destination is found, we update the value
 * of longest path else if none of the above solutions work we return false from our function.
 * 
 * 
 * @author gkushwaha
 *
 */
public class PathsBetweenTwoPointsInLandMines {
    private static final int ROWS = 3;
    private static final int COLS = 10;

    public static void main(final String args[]) {
        final AtomicInteger step = new AtomicInteger(0);
        final PathsBetweenTwoPointsInLandMines longestPathInLandmines = new PathsBetweenTwoPointsInLandMines();
        final int[][] maze = createMazeWithLandMines();
        System.out.println("MAZE :: ");
        longestPathInLandmines.printArray(maze);
        final int[][] result = createSolutionMaze();
        longestPathInLandmines.findlongestPath(maze, result, 0, 0, (ROWS - 1) / 2, (COLS - 1) / 2, 0, step);
    }

    private Solution findlongestPath(final int[][] maze, final int[][] result, final int startRow, final int startCol,
                                     final int endRow, final int endCol, int numberOfSteps, final AtomicInteger steps) {
        // System.out.println("i " + startRow + " J " + startCol);
        // reached destination
        if (startCol == endCol && startRow == endRow) {
            result[startRow][startCol] = steps.incrementAndGet();
            final Solution solution = new Solution(numberOfSteps, result, true);
            return solution;
        }
        // could not find any safe position to start in first column
        if (startRow >= ROWS || startCol >= COLS || startCol < 0 || startRow < 0) {
            // System.out.println("No Solution");
            return new Solution(numberOfSteps, result, false);
        }
        // cell already visited
        if (result[startRow][startCol] >= 1) {
            // System.out.println("do Not try Solution, Cell already visited");
            return new Solution(numberOfSteps, result, false);
        }
        // if current position safe
        if (isSafe(maze, startRow, startCol)) {
            result[startRow][startCol] = steps.incrementAndGet();
            // move left
            Solution path =
                    findlongestPath(maze, result, startRow, startCol - 1, endRow, endCol, ++numberOfSteps, steps);
            if (path.solved) { // if solved backtrack
                System.out.println(path);
                result[startRow][startCol] = 0;
            }

            // move right
            path = findlongestPath(maze, result, startRow, startCol + 1, endRow, endCol, ++numberOfSteps, steps);
            if (path.solved) {// if solved backtrack
                System.out.println(path);
                result[startRow][startCol] = 0;
            }

            // move up
            path = findlongestPath(maze, result, startRow - 1, startCol, endRow, endCol, ++numberOfSteps, steps);
            if (path.solved) {// if solved backtrack
                System.out.println(path);
                result[startRow][startCol] = 0;
            }

            // move down
            path = findlongestPath(maze, result, startRow + 1, startCol, endRow, endCol, ++numberOfSteps, steps);
            if (path.solved) {// if solved backtrack
                System.out.println(path);
                result[startRow][startCol] = 0;
            }

        }
        // // if not solved backtrack
        result[startRow][startCol] = 0;
        return new Solution(numberOfSteps, result, false);
    }

    /**
     * only 0 is invalid
     * 
     * @param maze
     * @param i
     * @param j
     * @return
     */
    private boolean isSafe(final int[][] maze, final int i, final int j) {
        if (maze[i][j] == 0) {
            return false;
        }
        return true;
    }

    /**
     * all the cells near mine 0 are not valid
     * 
     * @param maze
     * @param i
     * @param j
     * @return
     */
    private boolean isSafeFromMine(final int[][] maze, final int i, final int j) {
        // its a mine
        if (maze[i][j] == 0) {
            return false;
        }

        // LEFT
        if (j - 1 > 0 && maze[i][j - 1] == 0) {
            return false;
        }

        // RIGHT
        if (j + 1 > 0 && maze[i][j + 1] == 0) {
            return false;
        }

        // TOP
        if (i - 1 > 0 && maze[i - 1][j] == 0) {
            return false;
        }

        // BOTTOM
        if (i + 1 < ROWS && maze[i + 1][j] == 0) {
            return false;
        }

        return true;
    }

    /*
     * A utility function to print solution matrix sol[N][N]
     */
    void printArray(final int sol[][]) {
        System.out.println();
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print("    " + sol[i][j] + "    ");
            }
            System.out.println();
        }
    }

    private static int[][] createSolutionMaze() {
        final int result[][] = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                result[i][j] = 0;
            }
        }
        return result;
    }

    /**
     * O is landmines, all the 8 surroundings 0 are fatal, avoid those
     */
    private static int[][] createMazeWithLandMines() {
        if (ROWS < 10) {
            return new int[][] { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 1, 1, 0, 1, 1, 0, 1 },
                    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
        } else {
            return new int[][] { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
                    { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
                    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
                    { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
                    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 } };
        }
    }

    private final class Solution {
        final int attempts;
        final int[][] result;
        final boolean solved;

        public Solution(final int attempts, final int[][] result, final boolean solved) {
            this.attempts = attempts;
            this.result = result;
            this.solved = solved;
        }

        @Override
        public String toString() {
            printArray(result);
            return "Solution [attempts=" + attempts + ", solved=" + solved + "]";
        }

    }
}
