package com.nimbuzz.algo.matrix;

import static java.lang.Integer.parseInt;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * https://www.hackerearth.com/problem/algorithm/gold-mines-10/
 * 
 * There is a rectangular grid of gold mine. The grid has R rows and C columns. So it has R*C cells in total. The rows
 * are numbered from 1 to R and the columns are numbered from 1 to C. The top most row has number 1, the row next to it
 * has number 2 and so on. Similarly, the left most column has number 1, the column next to it has number 2 and so on.
 * Each cell in the grid has a unique coordinate which is (x, y) where x is the row number and y is the column number of
 * that particular cell.
 * 
 * Each cell in the grid has certain amount of gold in it. Total gold in a sub rectangle of the grid is the sum of all
 * units of gold contained in the cells that are inside the rectangle. Your task is to find the total gold in the given
 * sub rectangle.
 * 
 * A sub rectangle of the grid is defined by four integers x1, y1, x2 and y2. A cell (x, y) belongs to the sub rectangle
 * if and only if x1 <= x <= x2 and y1 <= y <=y2
 * 
 * Input First line of the input contains two space separated integers, R and C. It is followed by R lines, each line
 * has C space separated integers. Each integer denotes the units of gold present in that particular cell. Next line has
 * number Q, it is followed by Q queries each query in a single line. Each query is four space separated integers x1,
 * y1, x2 and y2.
 * 
 * Output For each query, you have to print a single number the total gold contained in that sub rectangle.
 * 
 * Constraints 1 <= R <= 1000 1 <= C <= 1000 1 <= x1 <= x2 <= R 1 <= y1 <= y2 <= C Amount of gold in each cell is an
 * integer from 0 to 10^6
 * 
 * @author gkushwaha
 *
 */
public class GoldMine2 {
    private int[][] goldMine = null; // Gold mine field

    public GoldMine2(final int[][] goldMine) {
        this.goldMine = goldMine;
    }

    public void getMaxGold2(final int i1, final int j1, final int i2, final int j2) {
        if (goldMine == null || goldMine.length == 0) {
            return;
        }
        final int rowLength = goldMine.length;
        final int colLength = goldMine[0].length;
        int sum = 0;
        System.out.println("Input i1 =" + i1 + " ,j1 =" + j1 + " ,i2 =" + i2 + " ,j2 =" + j2 + " ,rowLength  ="
                + rowLength + " ,colLength =" + colLength);
        if (i1 >= 0 && i1 < rowLength && i2 >= 0 && i2 < rowLength && j1 >= 0 && j1 < colLength && j2 >= 0
                && j2 < colLength) {
            System.out.println("Wrong input ");
        }
        if (i1 == i2) { // horrzontal rectangle
            sum = sumHorrizontal(i1, j1, j2);
        } else if (j1 == j2) { // vertical
            sum = sumVertical(i1, j1, i2);
        } else {
            for (int i = i1; i <= i2; i++) {
                sum = sum + sumHorrizontal(i, j1, j2);
            }
        }
        System.out.println("sum " + sum);
    }

    private int sumVertical(final int i1, final int j1, final int i2) {
        int sum = 0;
        for (int i = i1 - 1; i < i2; i++) {
            sum = sum + goldMine[i][j1 - 1];
        }
        return sum;
    }

    private int sumHorrizontal(final int i1, final int j1, final int j2) {
        int sum = 0;
        for (int j = j1 - 1; j < j2; j++) {
            final int i = i1 - 1;
            System.out.println("sum =" + sum + " i " + i + " j=" + j);
            sum = sum + goldMine[i][j];
        }
        return sum;
    }

    public static void main(final String[] args) {
        final Scanner scanIn = new Scanner(System.in);
        final int[][] mine = { { 2, 8, 9, 7 }, { 5, 8, 1, 7 }, { 5, 7, 3, 5 }, { 4, 8, 7, 4 } };
        final GoldMine2 goldMine = new GoldMine2(mine);
        // final int[][] inputGoldMine = inputGoldMine(scanIn);
        // final GoldMine2 goldMine3 = new GoldMine2(inputGoldMine);
        // goldMine3.getMaxGold2(i1, j1, i2, j2);
        printArray(mine);
        System.out.println("Enter rectangle cordinates : ");
        final String nextLine = scanIn.nextLine();
        final StringTokenizer stringTokenizer = new StringTokenizer(nextLine);
        final int i1 = parseInt(stringTokenizer.nextToken());
        final int j1 = parseInt(stringTokenizer.nextToken());
        final int i2 = parseInt(stringTokenizer.nextToken());
        final int j2 = parseInt(stringTokenizer.nextToken());

        scanIn.close();

        /* Output 31 */
        // final int i1 = 1;
        // final int j1 = 2;
        // final int i2 = 4;
        // final int j2 = 2;

        /* Output 14 */
        // final int i1 = 1;
        // final int j1 = 4;
        // final int i2 = 2;
        // final int j2 = 4;

        /* Output 47 */
        // final int i1 = 1;
        // final int j1 = 1;
        // final int i2 = 4;
        // final int j2 = 2;

        /* Output 7 */
        // final int i1 = 2;
        // final int j1 = 4;
        // final int i2 = 2;
        // final int j2 = 4;

        goldMine.getMaxGold2(i1, j1, i2, j2);
    }

    private static int[][] inputGoldMine(final Scanner scanIn) {
        System.out.println("Enter size of array  here : ");
        String nextLine = scanIn.nextLine();
        StringTokenizer stringTokenizer = new StringTokenizer(nextLine);
        final int x = parseInt(stringTokenizer.nextToken());
        final int y = parseInt(stringTokenizer.nextToken());
        final int[][] mine2 = new int[x][y];

        System.out.println("Enter elements of array y elements separated by space : ");

        for (int j = 0; j < x; j++) {
            nextLine = scanIn.nextLine();
            stringTokenizer = new StringTokenizer(nextLine);
            for (int i = 0; i < y; i++) {
                mine2[j][i] = parseInt(stringTokenizer.nextToken());
            }
        }

        return mine2;
    }

    static void printArray(final int[][] arr) {
        System.out.println("");
        System.out.println("arr >>");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
