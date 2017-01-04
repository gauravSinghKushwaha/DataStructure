package com.gaurav.utf8;

public class UTF8 {
    public static void main(final String[] args) {
        // See http://www.fileformat.info/info/unicode/char/1f4a9/index.htm
        final String poo =
                "😊☺️🎌🎌😀😀🚶💪😁😥😍😆💨🍉₹✂️🌸🌍⭐️🌦❄️🍓🍅🍯🍖🍟🍛🍶🏇🎭🚜🚞⏲📹🖥☦️💓🈺🚱🌐❎🚼⏸🇦🇺🇧🇬🇩🇲🇵🇫🇱";

        System.out.println(poo);
        // Length of chars doesn't equals the "real" length, that is: the number of actual codepoints
        System.out.println(poo.length() + " vs " + poo.codePointCount(0, poo.length()));

        // Iterating over all chars
        for (int i = 0; i < poo.length(); ++i) {
            final char c = poo.charAt(i);
            // If there's a char left, we chan check if the current and the next char
            // form a surrogate pair
            if (i < poo.length() - 1 && Character.isSurrogatePair(c, poo.charAt(i + 1))) {
                // if so, the codepoint must be stored on a 32bit int as char is only 16bit
                final int codePoint = poo.codePointAt(i);
                // show the code point and the char
                System.out.println(String.format("%6d:%s", codePoint, new String(new int[] { codePoint }, 0, 1)));
                ++i;
            }
            // else this can only be a "normal" char
            else {
                System.out.println(String.format("nor %6d:%s", (int) c, c));
            }
        }

        // constructing a string constant with two \\u unicode escape sequences
        System.out.println("\ud83d\udca9".equals("💩"));
    }
}