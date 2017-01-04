package com.nimbuzz.algo.backtracking;

public class ShortestPathInLandmines {
    private static final int SIZE = 10;

    public static void main(final String args[]) {
        final ShortestPathInLandmines shortestPathInLandmines = new ShortestPathInLandmines();
        final int[][] maze = createMazeWithLandMines();
        System.out.println("MAZE :: ");
        shortestPathInLandmines.printSolution(maze);
        final int[][] result = createSolutionMaze();
        if (shortestPathInLandmines.findShortestPath(maze, 4, 0, result)) {
            System.out.println("SOLUTION --");
            shortestPathInLandmines.printSolution(result);
        }
    }

    private boolean findShortestPath(final int[][] maze, final int startRow, final int startCol, final int[][] result) {
        // System.out.println("i " + i + " J " + j);
        // printSolution(result);
        // reached end
        if (startCol == SIZE - 1) {
            result[startRow][startCol] = 1;
            return true;
        }
        // could not find any safe position to start in first column
        if (startRow == SIZE - 1 && startCol == 0) {
            System.out.println("No Solution");
        }
        // if current position safe
        if (isSafe(maze, startRow, startCol)) {
            result[startRow][startCol] = 1;
            // move right
            if (findShortestPath(maze, startRow, startCol + 1, result)) {
                printSolution(result);
                return true;
            }
            // move down
            if (findShortestPath(maze, startRow + 1, startCol, result)) {
                printSolution(result);
                return true;
            }
            // BACKTRACK
            result[startRow][startCol] = 0;
        }
        return false;
    }

    private boolean isSafe(final int[][] maze, final int i, final int j) {
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
        if (i + 1 < SIZE && maze[i + 1][j] == 0) {
            return false;
        }

        return true;
    }

    /*
     * A utility function to print solution matrix sol[N][N]
     */
    void printSolution(final int sol[][]) {
        System.out.println("Solution");
        System.out.println("");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] createSolutionMaze() {
        final int result[][] = new int[10][10];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result[i][j] = 0;
            }
        }
        return result;
    }

    /**
     * O is landmines, all the 8 surroundings 0 are fatal, avoid those
     */
    private static int[][] createMazeWithLandMines() {
        final int mat[][] =
            { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 1, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 } };

        return mat;
    }
}
