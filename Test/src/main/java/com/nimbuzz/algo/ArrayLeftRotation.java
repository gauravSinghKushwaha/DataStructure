package com.nimbuzz.algo;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/ctci-array-left-rotation
 * 
 * @author gkushwaha
 *
 */
public class ArrayLeftRotation {




    @SuppressWarnings("resource")
    public static void main(final String args[]) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int k = in.nextInt();
        final int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        System.out.println(printAr(rotate(a, k)));
    }

    private static int[] rotate(final int[] arr, final int i) {
        final int len = arr.length;
        final int res[] = new int[len];
        for (int j = 0; j < len; j++) {
            int newPos;
            if (j < i) {
                newPos = len + j - i;
            } else {
                newPos = j - i;
            }
            res[newPos] = arr[j];
        }
        return res;
    }

    private static String printAr(final int[] arr) {
        final StringBuilder builder = new StringBuilder();
        for (final int i : arr) {
            builder.append(i).append("");
        }
        return builder.toString();
    }
}
