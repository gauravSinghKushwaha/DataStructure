package com.gaurav.pattern.matching;


/**
 * Input ABCDE output EDCBA
 * 
 * @author gkushwaha
 *
 */
public class reverseStringByRecurssion {
    public static void main(final String args[]) {
        System.out.println(reverseString("ABCDE"));
    }

    private static String reverseString(final String string) {
        final int length = string.length();
        if (length == 1) {
            return string;
        }
        return string.substring(length - 1, length) + reverseString(string.substring(0, length - 1));
    }
}
