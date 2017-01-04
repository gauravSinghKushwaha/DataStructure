package com.gaurav.misc.algo;


public class Base10ToBase7 {

    public static void main(final String args[]){
        System.out.println(convert(7));
        System.out.println(convert(7792875));
    }

    static String convert(long input) {
        String.valueOf(input);
        final StringBuilder outPut = new StringBuilder();

        while (input >= 7) {
            final int mod = (int) (input % 7);
            input = input / 7;
            outPut.append(getBase7Character(mod));
        }

        outPut.append(getBase7Character((int) input));
        return new StringBuilder(outPut).reverse().toString();
    }

    private static Character getBase7Character(final int base7Index) {
        Character base7Char = '0';
        switch (base7Index) {
            case 0:
                base7Char = '0';
                break;
            case 1:
                base7Char = 'a';
                break;
            case 2:
                base7Char = 't';
                break;
            case 3:
                base7Char = 'l';
                break;
            case 4:
                base7Char = 's';
                break;
            case 5:
                base7Char = 'i';
                break;
            case 6:
                base7Char = 'n';
                break;
        }
        return base7Char;
    }
}
