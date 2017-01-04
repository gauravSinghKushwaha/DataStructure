package com.nimbuzz.algo.sort;

import static java.lang.System.out;

import java.util.Date;

/**
 * 1668928 -- merge 704982704 -- bubble 704882705 -- insertion 1409865409 -- selection 1668928 -- quick sort
 * 
 * @author gkushwaha
 *
 */
public class QuickSort {
    private static int seq = 100000;
    private static int[] input = null;
    // private static int[] input = new int[] { 0, 3, 7, 8, 2, 5, 1, 9, 5, 4 };
    // private static int[] input = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    // private static int[] input = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
    public static final Date startDate = new Date();

    private static int counter = 0;

    public static void main(final String args[]) {
        final Date inputEndTime = input();

        quickSort(input, 0, input.length - 1);

        out.println(" total time " + (new Date().getTime() - inputEndTime.getTime()) + " ms");
        // out.println(" output : " + printAr(input));
        out.println("Total Iteration Operations :: " + counter);
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

    static void quickSort(final int arr[], final int left, final int right) {

        final int index = partition(arr, left, right);

        if (left < index - 1) {
            quickSort(arr, left, index - 1);
        }

        if (index < right) {
            quickSort(arr, index, right);
        }

    }

    private static int partition(final int[] input, int start, int end) {
        final int pivotPos = (start + end) / 2;
        final int pivot = input[pivotPos];// pivot element
        // System.out.println("Ar :" + printAr(input) + " pivot :" + pivot + " start :" + start + " end :" + end);
        while (start <= end) {
            counter++;
            // System.out.println("s :" + start + " e :" + end + " input[s]:" + input[start] + " input[e]:" + input[end]
            // + " piv :" + pivot);
            // System.out.println("s : Ar :" + printAr(input));
            if (input[start] < pivot) {
                start++;
                continue;
            }
            if (input[end] > pivot) {
                end--;
                continue;
            }
            if (start <= end) { // swap with input [end]
                // System.out.println("swp S:" + start + " e :" + end + " input[s]:" + input[start] + " input[e]:"
                // + input[end] + " piv :" + pivot + "Ar :" + printAr(input));
                final int temp = input[end];
                input[end] = input[start];
                input[start] = temp;
                start++;
                end--;
                // System.out.println("swp E:" + start + " e :" + end + " input[s]:" + input[start] + " input[e]:"
                // + input[end] + " piv :" + pivot + "Ar :" + printAr(input));
            }
        }
        // System.out.println("end  Ar :" + printAr(input));
        // System.out.println("s :" + start + " e :" + end + " input[s]:" + input[start] + " input[e]:" + input[end]);
        return start;
    }

    static String printAr(final int[] arr) {
        final StringBuilder builder = new StringBuilder("Ar :: ");
        for (final int i : arr) {
            builder.append(i).append(",");
        }
        return builder.toString();
    }
}
