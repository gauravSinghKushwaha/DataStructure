package com.nimbuzz.algo;

/**
 * Print Consecutive count and digit for a number
 * 
 * @author gkushwaha
 *
 */
public class CountAndPrintingDigits {

    static String LookAndSay(final String start, int n) {

        if (n == 0) {
            return start;

        }
        int count = 0;
        String temp = "";
        for (int i = 0; i < start.length(); i++) {
            final char currChar = start.charAt(i);
            if (i == 0) {
                count = 1;
            } else {
                final char previousChar = start.charAt(i - 1);
                if (currChar == previousChar) {
                    count++;
                } else {
                    temp = temp + count + previousChar;
                    count = 1;
                }
            }
            if (i == start.length() - 1 || start.length() == 1) {
                temp = temp + count + currChar;
            }
        }

        return LookAndSay(temp, --n);

    }

    public static void main(final String args[]) {
        System.out.println(LookAndSay("11", 1));
        System.out.println(LookAndSay("11", 2));
        System.out.println(LookAndSay("11", 3));
        System.out.println(LookAndSay("11", 4));
    }
}
