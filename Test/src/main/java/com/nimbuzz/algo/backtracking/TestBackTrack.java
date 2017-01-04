package com.nimbuzz.algo.backtracking;

public class TestBackTrack {
    boolean printNumber(int i) {
        if (i == 0) {
            return true;
        }
        System.out.println("i " + i);
        if (i != 3) {
            printNumber(--i);
        }
        return false;
    }

    public static void main(final String args[]) {
        final TestBackTrack testBackTrack = new TestBackTrack();
        final int i = 5;
        if (testBackTrack.printNumber(i)) {
            System.out.println(i);
        }
        testBackTrack.printNumber(i);
        testBackTrack.printNumber(i);
    }
}
