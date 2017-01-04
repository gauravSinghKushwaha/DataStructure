package com.nimbuzz.algo.sort;

import static java.lang.System.out;

import java.util.Date;

public class BubbleSort {
    private static int seq = 100000;
    // private static int[] input = null;
    // private static int[] input = new int[] { 0, 3, 7, 8, 2, 5, 1, 9, 5, 4 };
    // private static int[] input = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private static int[] input = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    // private static int[] input = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    public static final Date startDate = new Date();

    private static int counter = 0;

    public static void main(final String args[]) {
        final Date inputEndTime = input();

        sort(input);

        out.println(" total time " + (new Date().getTime() - inputEndTime.getTime()) + " ms");
        // out.println(" output : " + printAr(input));
        out.println(" output length : " + input.length);
        out.println("Total Iteration Operations :: " + counter);
    }

    private static void sort(final int[] in) {
        final int end = in.length - 1;
        System.out.println("arr leng " + in.length + " end " + end);
        for (int i = 0; i < end; i++) {
            // System.out.println("i " + i + " in[i] " + in[i]);
            for (int j = end; j > i; j--) {
                // System.out.println("i " + i + " j" + j + " in[i] " + in[i] + " in[j] " + in[j]);
                if (in[i] > in[j]) {
                    final int temp = in[j];
                    in[j] = in[i];
                    in[i] = temp;
                }
                counter++;
            }
        }
    }

    private static Date input() {
        if (input == null) {
            input = new int[seq];
            for (int i = input.length - 1; i > 0; i--) {
                input[i] = i;
            }
        }
        final Date inputEndTime = new Date();
        out.println("Input totol time " + (inputEndTime.getTime() - startDate.getTime()) + " ms");
        return inputEndTime;
    }

    static String printAr(final int[] arr) {
        final StringBuilder builder = new StringBuilder("Ar :: ");
        for (final int i : arr) {
            builder.append(i).append(",");
        }
        return builder.toString();
    }
}
