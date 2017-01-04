package com.gaurav.misc.algo;

/**
 * A permutation, also called an “arrangement number” or “order,” is a rearrangement of the elements of an ordered list
 * S into a one-to-one correspondence with S itself. A string of length n has n! permutation. Source:
 * Mathword(http://mathworld.wolfram.com/Permutation.html)
 * 
 * Below are the permutations of string ABC. ABC, ACB, BAC, BCA, CAB, CBA
 * 
 * http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 * 
 * @author gkushwaha
 *
 */
public class Permutations {
    void swap(char x, char y) {
        char temp;
        temp = x;
        x = y;
        y = temp;
    }

    /*
     * Function to print permutations of string This function takes three parameters: 1. String 2. Starting index of the
     * string 3. Ending index of the string.
     */
    void permute(final char str[], final int l, final int r) {
        int i;
        if (l == r) {
            System.out.println(str);
        } else {
            for (i = l; i <= r; i++) {
                swap(str[l], str[i]);
                permute(str, l + 1, r);
                swap(str[l], str[i]); // backtrack
            }
        }
    }

    public static void main(final String args[]) {
        final char str[] = { 'A', 'B', 'C' };
        final int n = str.length;
        // new Permutations().permute(str, 0, n - 1);

        final String input = "ABC";
        final int length = input.length();
        getPermutations(input, 0, length);
    }

    private static void getPermutations(final String input, final int i, final int length) {
        if (i == length) {
            System.out.println(input);
        }
        for (int j = i; j < length; j++) {
            swapTwo(input, i, j);
            getPermutations(input, i + 1, length);
            swapTwo(input, i, j);
        }
    }

    private static void swapTwo(String input, final int i, final int j) {
        final char[] charArray = input.toCharArray();
        final char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        input = new String(charArray);

    }
}
