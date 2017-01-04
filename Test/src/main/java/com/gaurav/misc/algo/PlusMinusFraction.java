package com.gaurav.misc.algo;

import static java.lang.Integer.parseInt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Probability of positive, zero and negative in a one dimensional array
 * 
 * input > size of array
 * 
 * array element with space beteween elemets
 * 
 * 5
 * 
 * 1 2 3 -4 0
 * 
 * output
 * 
 * 0.600000 0.200000 0.200000
 * 
 * @author gkushwaha
 *
 */
public class PlusMinusFraction {
    public static void main(final String[] args) {
        final Scanner scanIn = new Scanner(System.in);
        final String nextLine = scanIn.nextLine();
        final StringTokenizer stringTokenizer = new StringTokenizer(nextLine);
        final int x = parseInt(stringTokenizer.nextToken());
        // final int y = Integer.parseInt(stringTokenizer.nextToken());
        final int[] mine2 = inputArray(scanIn, x);
        printArray(mine2);
        printSum(mine2);
    }

    private static void printSum(final int[] mine2) {
        int posC = 0, negC = 0, zeroC = 0;
        for (int i = 0; i < mine2.length; i++) {
            if (mine2[i] == 0) {
                zeroC++;
            }
            if (mine2[i] < 0) {
                negC++;
            }
            if (mine2[i] > 0) {
                posC++;
            }
        }
        System.out.println("posC " + posC + " negC " + negC + " zeroC " + zeroC);
        System.out.println(new BigDecimal(posC).divide(new BigDecimal(mine2.length).setScale(6, RoundingMode.HALF_UP),
                6, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal(negC).divide(new BigDecimal(mine2.length).setScale(6, RoundingMode.HALF_UP),
                6, RoundingMode.HALF_UP));
        System.out.println(new BigDecimal(zeroC).divide(new BigDecimal(mine2.length).setScale(6, RoundingMode.HALF_UP),
                6, RoundingMode.HALF_UP));
    }

    private static int[] inputArray(final Scanner scanIn, final int x) {
        String nextLine;
        StringTokenizer stringTokenizer;
        final int[] mine2 = new int[x];

        nextLine = scanIn.nextLine();
        stringTokenizer = new StringTokenizer(nextLine);
        for (int i = 0; i < x; i++) {
            mine2[i] = parseInt(stringTokenizer.nextToken());
        }
        return mine2;
    }

    private static void printArray(final int[] mine2) {
        System.out.println();
        for (int i = 0; i < mine2.length; i++) {
            System.out.print(mine2[i] + " ");
        }
    }
}
