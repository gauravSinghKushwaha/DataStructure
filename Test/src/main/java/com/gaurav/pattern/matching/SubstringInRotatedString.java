package com.gaurav.pattern.matching;

/**
 * 
 * ABCDE is rotated to CDEAB , seach for ABC in this...return true
 * 
 * 
 * @author gkushwaha
 *
 */
public class SubstringInRotatedString {

    public static void main(final String args[]) {
        System.out.println(isSubstring());
    }

    private static boolean isSubstring() {
        final String s1 = "ABCDE";
        final String s2 = "ABC";
        if (s1.concat(s1).contains(s2)) {
            return true;
        }
        return false;
    }


}
