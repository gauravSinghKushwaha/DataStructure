package com.nimbuzz.algo;

import static java.lang.System.out;
import static java.util.Arrays.copyOfRange;

import java.util.Date;

public class Inversions {
    private static int seq = 10000000;
    private static int[] input = null;
    // private static int[] input = new int[] { 3, 4, 1, 0, 6 };
    // private static int[] input = new int[] { 9, 4, 0, 1, 3, 5, 6, 2, 8, 4, 3, 1 };
    public static final Date startDate = new Date();

    private static int counter = 0;

    public static void main(final String args[]) {
        final Date inputEndTime = input();

        final int[] output = sort(input);

        // inversion(input);

        out.println("Merge total time " + (new Date().getTime() - inputEndTime.getTime()) + " ms");
        // .println(" output : " + printAr(output));
        out.println("Total Iteration Operations :: " + counter);
    }

    private static Date input() {
        if (input == null) {
            input = new int[seq];
            for (int i = 0; i < input.length; i++) {
                input[i] = i;
            }
        }
        final Date inputEndTime = new Date();
        out.println("Input totol time " + (inputEndTime.getTime() - startDate.getTime()) + " ms");
        return inputEndTime;
    }

    private static int[] sort(final int[] input) {
        final int length = input.length;
        if (length == 1) {
            return input;
        }
        final int[] a1 = sort(copyOfRange(input, 0, length / 2));
        final int[] a2 = sort(copyOfRange(input, length / 2, length));
        final int[] sortedArray = merge(a1, a2);
        // out.println("In:" + printAr(input) + " a1:" + printAr(a1) + " a2:" + printAr(a2) + " sorted:"
        // + printAr(sortedArray));
        return sortedArray;
    }

    private static int[] merge(final int[] a1, final int[] a2) {
        // System.out.println(" a1 : " + printAr(a1) + " a2 : " + printAr(a2));
        final int a2Length = a2 != null ? a2.length : 0;
        final int a1Length = a1 != null ? a1.length : 0;
        final int length = a1Length + a2Length;
        // System.out.println(" Len1 : " + a1Length + " len2 : " + a2Length + " leng : " + length);
        final int[] output = new int[length];
        int a1i = 0;
        int a2i = 0;
        for (int i = 0; i < length; i++) {
            counter++;
            if (a1i < a1Length && a2i < a2Length) {
                if (a1[a1i] < a2[a2i]) {
                    output[i] = a1[a1i];
                    a1i++;
                } else {
                    printInv(a2[a2i], a1, a1i);
                    output[i] = a2[a2i];
                    a2i++;
                }
            } else {
                if (a1i < a1Length) {
                    output[i] = a1[a1i];
                    a1i++;
                } else {
                    output[i] = a2[a2i];
                    a2i++;
                }
            }
        }
        // System.out.println(" output : " + printArray(output));
        return output;
    }

    private static void printInv(final int a, final int[] a2, final int a2i) {
        for (int i = a2i; i < a2.length; i++) {
            System.out.println("{" + a2[i] + "," + a + "}");
        }
    }

    static String printAr(final int[] arr) {
        final StringBuilder builder = new StringBuilder("Ar :: ");
        for (final int i : arr) {
            builder.append(i).append(",");
        }
        return builder.toString();
    }
}
