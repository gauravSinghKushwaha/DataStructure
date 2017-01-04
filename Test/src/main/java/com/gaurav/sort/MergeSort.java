package com.gaurav.sort;

import static java.util.Arrays.copyOfRange;

import java.util.Date;

public class MergeSort {

    private static int seq = 100000;
    private static int[] input = new int[seq];
    public static final Date startDate = new Date();

    private static int counter = 0;

    public static void main(final String args[]) {
        for (int i = 0; i < input.length; i++) {
            input[i] = i;
        }
        final Date inputEndTime = new Date();
        System.out.println("Input  time " + (inputEndTime.getTime() - startDate.getTime()));
        final int[] output = mergeAndSort(input);
        System.out.println("Merge total time " + (new Date().getTime() - inputEndTime.getTime()));
        // System.out.println(" output : " + printAr(output));
        System.out.println("Total Iteration Operations :: " + counter);
    }

    private static int[] mergeAndSort(final int[] input) {
        final int length = input.length;
        if (length == 1) {
            return input;
        }
        final int[] a1 = mergeAndSort(copyOfRange(input, 0, length / 2));
        final int[] a2 = mergeAndSort(copyOfRange(input, length / 2, length));
        // System.out.println("Input::" + printAr(input) + " input a1: " + printAr(a1) + "input a2: " + printAr(a2));
        return merge(a1, a2);
    }

    private static int[] merge(final int[] a1, final int[] a2) {
        // System.out.println(" a1 : " + printArray(a1) + " a2 : " + printArray(a2));
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

    static String printAr(final int[] arr) {
        final StringBuilder builder = new StringBuilder(arr.toString() + " :: ");
        for (final int i : arr) {
            builder.append(i).append(",");
        }
        return builder.toString();
    }

}
