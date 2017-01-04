package com.gaurav.misc.algo;

import static java.lang.Math.max;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 */
public class LongestCommonSubsequence {

    public int lcs(final char str1[], final char str2[], final int len1, final int len2) {

        if (len1 < 0 || len2 < 0) {
            return 0;
        }
        if (str1[len1] == str2[len2]) {
            return 1 + lcs(str1, str2, len1 - 1, len2 - 1);
        } else {
            return max(lcs(str1, str2, len1 - 1, len2), lcs(str1, str2, len1, len2 - 1));
        }
    }

    public int lcsDynamic(final char str1[], final char str2[]) {

        final int temp[][] = new int[str1.length + 1][str2.length + 1];
        int max = 0;
        for (int i = 1; i < temp.length; i++) {
            for (int j = 1; j < temp[i].length; j++) {
                final int currentI = i - 1;
                final int currentJ = j - 1;
                if (str1[currentI] == str2[currentJ]) {
                    temp[i][j] = temp[currentI][currentJ] + 1;
                } else {
                    temp[i][j] = max(temp[i][currentJ], temp[currentI][j]);
                }
                if (temp[i][j] > max) {
                    max = temp[i][j];
                }
            }
        }
        return max;

    }

    public static void main(final String args[]) {
        final LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        final String str1 = "ABCDGHLQR";
        final String str2 = "AEDPHR";

        final int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
        System.out.println(lcs.lcs(str1.toCharArray(), str2.toCharArray(), str1.length() - 1, str2.length() - 1));
        System.out.print(result);
    }

}