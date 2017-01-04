package com.nimbuzz.algo;

import static java.lang.Math.max;

/**
 * Date 08/01/2014
 * 
 * @author Tushar Roy
 *
 *         Given a string find longest palindromic subsequence in this string.
 *
 *         Time complexity - O(n2) Space complexity - O(n2
 *
 *         Youtube link - https://youtu.be/_nCsPn7_OgI
 *
 *         References http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
 */
public class LongestPalindromicSubsequence {

    /**
     * 
     * https://www.youtube.com/watch?v=_nCsPn7_OgI
     * 
     * DYNAMIC PROGRAMMING
     * 
     * @param str
     * @return
     */
    public int calculate1(final char[] str) {
        final int length = str.length;
        final int T[][] = new int[length][length];
        // creating a matrix of length * length with default value as 1
        for (int i = 0; i < length; i++) {
            T[i][i] = 1;
        }
        for (int l = 2; l <= length; l++) {
            final int iMax = length - l + 1;
            // 1...5
            // 2...5
            // 3...5
            // .......
            for (int i = 0; i < iMax; i++) {
                final int j = i + l - 1;
                System.out.println("length =" + length + " iMax = " + iMax + " j= " + j);
                // first element
                if (l == 2 && str[i] == str[j]) {
                    T[i][j] = 2;
                } else if (str[i] == str[j]) {
                    T[i][j] = T[i + 1][j - 1] + 2;
                } else {
                    T[i][j] = max(T[i + 1][j], T[i][j - 1]);
                }
            }
        }
        return T[0][length - 1];
    }

    public int calculateRecursive(final char str[], final int start, final int len) {
        if (len == 1) {
            return 1;
        }
        if (len == 0) {
            return 0;
        }
        if (str[start] == str[start + len - 1]) {
            return 2 + calculateRecursive(str, start + 1, len - 2);
        } else {
            return max(calculateRecursive(str, start + 1, len - 1), calculateRecursive(str, start, len - 1));
        }
    }

    public static void main(final String args[]) {
        final LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        final String str = "agbdba";
        final int r1 = lps.calculateRecursive(str.toCharArray(), 0, str.length());
        final int r2 = lps.calculate1(str.toCharArray());
        System.out.print(r1 + " " + r2);
    }

}