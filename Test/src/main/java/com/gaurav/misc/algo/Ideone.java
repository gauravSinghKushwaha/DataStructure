package com.gaurav.misc.algo;

class Ideone {
    private static final char space = ' ';

    // private static final char space2 = '$';

    public static void main(final String[] args) {
        final String s = "ABC";
        final char[] str = s.toCharArray();
        final int length = str.length;
        final char[] buffer = new char[5 * length];
        buffer[0] = str[0];
        getCombinations(str, buffer, 1, 1, length);
    }

    public static void getCombinations(final char[] s, final char[] buffer, final int i, final int j, final int length) {
        if (i == length) {
            // while (j < buffer.length) {
            // buffer[j] = space;
            // j++;
            // }
            System.out.println(buffer);
            return;
        }
        buffer[j] = s[i];
        getCombinations(s, buffer, i + 1, j + 1, length);

        buffer[j] = space;
        buffer[j + 1] = s[i];
        getCombinations(s, buffer, i + 1, j + 2, length);

        // buffer[j] = space2;
        // buffer[j + 1] = s[i];
        // getCombinations(s, buffer, i + 1, j + 2, length);

        return;
    }
}