package com.nimbuzz.oops;


public class BalanceIndexInArray {

    public static void main(final String aegs[]) {
        final int[] A = { 1, 0, 0, 1, 1, 1 };
        // final int[] A = { 1, 0, 0, 1, 1 };
        // final int[] A = { 5, 5, 1, 7, 2, 3, 5 };
        // final int[] A = { -1, 3, -4, 5, 1, -6, 2, 1 };
        // System.out.println(new BalanceIndexInArray().solution1(A));
        // System.out.println(new BalanceIndexInArray().solution2(5, A));
        System.out.println(new BalanceIndexInArray().solution3(A));
    }

    public int solution1(final int[] A) {
        long sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = sum + A[i];
        }
        long sum2 = 0;
        for (int i = 0; i < A.length; i++) {
            sum2 = sum2 + A[i];
            if (sum - sum2 == sum2 - A[i]) {
                return i;
            }
        }
        return -1;
    }

    public int solution2(final int X, final int[] A) {
        if (null == A || A.length <= 0) {
            return -1;
        }
        final int totalCount = A.length;
        int runningXCount = 0;
        int remainingNonXCount = 0;
        for (int i = 0; i < totalCount; i++) {
            if (A[i] == X) {
                runningXCount = runningXCount + 1;
            } else {
                remainingNonXCount = totalCount - runningXCount - i + 1;
            }
            if (remainingNonXCount == runningXCount) {
                return i;
            }
        }
        return -1;
    }

    public int[] solution3(final int[] A) {
        if (null == A || A.length <= 0) {
            return new int[] {};
        }
        final int length = A.length - 1;
        int number = 0;
        for (int i = length; i >= 0; i--) {
            number = (int) (number + A[i] * Math.pow(-2, i));
        }

        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1 && i + 1 < length && A[i + 1] == 0) {
                A[i] = 1;
                A[i + 1] = 1;
                i = i + 1;
            } else if (A[i] == 1 && i + 1 < length && A[i + 1] == 1) {
                A[i] = 1;
                A[i + 1] = 0;
                i = i + 1;
            }
        }
        return A;
    }

}
