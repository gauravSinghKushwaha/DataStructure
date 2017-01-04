package com.nimbuzz.algo;

import static java.lang.Integer.valueOf;

/**
 * 
 Find Maximum number possible by doing at-most K swaps
 * 
 * Given a positive integer, find maximum integer possible by doing at-most K swap operations on its digits.
 * 
 * Examples:
 * 
 * Input: M = 254, K = 1 Output: 524
 * 
 * Input: M = 254, K = 2 Output: 542
 * 
 * Input: M = 68543, K = 1 Output: 86543
 * 
 * Input: M = 7599, K = 3 Output: 9975
 * 
 * Input: M = 76543, K = 1 Output: 76543
 * 
 * Input: M = 129814999, K = 4 Output: 999914821
 * 
 * 
 * 
 * @author gkushwaha
 *
 */
public class MaxNumberViaSwappingDigits {

    public static void main(final String args[]) {

        new MaxNumberViaSwappingDigits().getMaxNumber("687999", 2);
    }

    private void getMaxNumber(String numberStr, final int digitsToSwap) {
        String max = numberStr;
        for (int i = 0; i < digitsToSwap; i++) {
            final char digitToBeSwapped = numberStr.charAt(i);
            for (int j = i + 1; j < numberStr.length(); j++) {
                final char digitsToBeSwappedWith = numberStr.charAt(j);
                final StringBuilder numberStringBuilder = new StringBuilder(numberStr);
                numberStringBuilder.replace(i, i + 1, digitsToBeSwappedWith + "");
                numberStringBuilder.replace(j, j + 1, digitToBeSwapped + "");
                if (valueOf(numberStringBuilder.toString()) > valueOf(max)) {
                    max = numberStringBuilder.toString();
                }
            }
            numberStr = max;
        }
        System.out.println(max);
    }
}
