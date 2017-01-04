package com.gaurav.matrix;

import java.util.Scanner;
import java.util.StringTokenizer;

public class DifferenceBetweenDiagonal {
    public static void main(final String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        final Scanner scanIn = new Scanner(System.in);
        final String nextLine = scanIn.nextLine();
        final StringTokenizer stringTokenizer = new StringTokenizer(nextLine);
        final int x = Integer.parseInt(stringTokenizer.nextToken());
        // final int y = Integer.parseInt(stringTokenizer.nextToken());
        final int y = x;
        final int[][] mine2 = inputArray(scanIn, x, y);
        // printArray(mine2);
        printSum(mine2);
    }

    private static void printSum(final int[][] mine2) {
        int colSum1 = 0, colSum2 = 0;
        final int len = mine2.length - 1;
        for (int i = 0; i < mine2.length; i++) {
            // System.out.println("len " + len + " i " + i + " colSum1 " + colSum1 + " colSum2 " + colSum2);
            colSum1 = colSum1 + mine2[i][i];
            colSum2 = colSum2 + mine2[i][len - i];
        }
        System.out.println("len " + len + " colSum1 " + colSum1 + " colSum2 " + colSum2);
        System.out.println(Math.abs(colSum1 - colSum2));
    }

    private static int[][] inputArray(final Scanner scanIn, final int x, final int y) {
        String nextLine;
        StringTokenizer stringTokenizer;
        final int[][] mine2 = new int[x][y];

        for (int j = 0; j < x; j++) {
            nextLine = scanIn.nextLine();
            stringTokenizer = new StringTokenizer(nextLine);
            for (int i = 0; i < y; i++) {
                mine2[j][i] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        return mine2;
    }

    private static void printArray(final int[][] mine2) {
        for (int i = 0; i < mine2.length; i++) {
            for (int j = 0; j < mine2[i].length; j++) {
                System.out.print(mine2[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

}
