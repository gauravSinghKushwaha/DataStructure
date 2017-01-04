package com.nimbuzz.algo.pattern;

import static java.lang.Math.abs;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/ctci-making-anagrams
 * 
 * @author gkushwaha
 *
 */
public class NumberOfCharactersToBeDeltedForAnagram {
    public static int numberNeeded(final String first, final String second) {
        final int freq[] = new int[26];
        // ASCII of 'a' is 97, so we subtract 97 from every character to get relative position of characters in array.
        // e.g. a at 0 . b at 1...
        for (int i = 0; i < first.length(); i++) {
            final char c = first.charAt(i);
            ++freq[c - 'a'];
        }
        for (int i = 0; i < second.length(); i++) {
            final char c = second.charAt(i);
            --freq[c - 'a'];
        }
        int count = 0;
        for (int i = 0; i < freq.length; i++) {
            count = count + abs(freq[i]);
        }
        return count;
    }

    public static void main(final String[] args) {
        @SuppressWarnings("resource")
        final Scanner in = new Scanner(System.in);
        final String a = in.next();
        final String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
