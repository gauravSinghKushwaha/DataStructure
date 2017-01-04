package com.gaurav.matrix;

/**
 * Given a gold mine of n*m dimension. Each field in this mine contains an integer which is amount of gold in tons.
 * Initially miner is in first column but could be at any row i. He can move only (right ->, right up /, right down \).
 * Find out maximum amount of gold he can collect and path followed by him.
 * 
 * @author gkushwaha
 *
 */
public class GoldMine {

    private int[][] goldMine = null; // Gold mine field

    public GoldMine(final int[][] goldMine) {
        this.goldMine = goldMine;
    }

    public void getMaxGold() {
        if (goldMine == null || goldMine.length == 0) {
            return;
        }
        final int rowLength = goldMine.length;
        final int colLength = goldMine[0].length;
        final int[][] goldMineTable = new int[rowLength][colLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                goldMineTable[i][j] = 0;
            }
        }
        System.out.println("rowLength " + rowLength + " colLength " + colLength);
        for (int i = 0; i < rowLength; i++) {
            int startRow = i;
            int sum = 0;
            for (int startCol = 0; startCol < colLength; startCol++) {
                sum = sum + goldMine[startRow][startCol];
                final int up = startRow - 1;
                final int down = startRow + 1;
                int rightUp = 0;
                int rightDown = 0;
                final int nextCol = startCol + 1;
                if (up >= 0 && nextCol < colLength) {
                    rightUp = goldMine[up][nextCol];
                }
                if (down < rowLength && nextCol < colLength) {
                    rightDown = goldMine[down][nextCol];
                }
                System.out.println("sum " + sum + " up " + up + " down " + down + " rightUp " + rightUp + " rightDown "
                        + rightDown + " nextCol " + nextCol + " startCol " + startCol);
                if (rightDown > rightUp) {
                    startRow = down;
                } else {
                    startRow = up;
                }
            }
            System.out.println("");
            System.out.println("starting from row " + i + " sum " + sum);
            System.out.println("");
        }
    }

    public static void main(final String[] args) {

        final int[][] mine = { { 1, 3, 1, 5 }, { 2, 2, 4, 1 }, { 5, 0, 2, 3 }, { 0, 6, 1, 2 } };

        final GoldMine goldMine = new GoldMine(mine);
        goldMine.getMaxGold();
    }

}