package com.gaurav.bit;

import java.util.BitSet;
import java.util.Date;

public class TestBit {

    private static int SIZE = 63;

    public static void main(final String args[]) {
        // -141 meme 1139277824
        // -144

        // booleanArray();
        // bitSet();
        // -3951 meme 391643136
        // -4805

        // final long l = 9223372036854775807l;
        final long l = -9223372036854775807l;
        // final long l = -9l;
        final int i = 2147483647;// 10
        final int j = 4;// 100
        final int COUNT_BITS = Integer.SIZE - 3;

        System.out.println(-1L << COUNT_BITS);
        System.out.println(0L << COUNT_BITS);
        System.out.println(1L << COUNT_BITS);
        System.out.println(2L << COUNT_BITS);
        System.out.println(3L << COUNT_BITS);
        System.out.println(4L << COUNT_BITS);

        System.out.println(Long.toBinaryString(-1L << COUNT_BITS));
        System.out.println(Long.toBinaryString(0L << COUNT_BITS));
        System.out.println(Long.toBinaryString(1L << COUNT_BITS));
        System.out.println(Long.toBinaryString(-1L << COUNT_BITS | 0));
        System.out.println(Long.toBinaryString((-1L << COUNT_BITS | 0) & (1L << COUNT_BITS) - 1));
        System.out.println(Long.toBinaryString(2L << COUNT_BITS));
        System.out.println(Long.toBinaryString(3L << COUNT_BITS));
        System.out.println(Long.toBinaryString(4L << COUNT_BITS));

        System.out.println(1L >>> 1);
        System.out.println(-1L >>> 1);
        System.out.println(2L >>> 1);
        System.out.println(3L >>> 1);
        System.out.println(4L >>> 1);
        System.out.println(1L << 65);
        System.out.println("BITWISE SHIFT for " + l);
        System.out.println("Long.toBinarytring(l) " + Long.toBinaryString(l));
        System.out.println("Long.toBinaryString(l).length() " + Long.toBinaryString(l).length());
        System.out.println("Long.lowestOneBit(l) " + Long.lowestOneBit(l));
        System.out.println("Long.highestOneBit(l) " + Long.highestOneBit(l));
        System.out.println("Long.numberOfLeadingZeros(l) " + Long.numberOfLeadingZeros(l));
        System.out.println("Long.numberOfTrailingZeros(l) " + Long.numberOfTrailingZeros(l));
        System.out.println("Long.bitCount(l) " + Long.bitCount(l));
        System.out.println(127l >> 6);
        System.out.println(100000 >> 6);
        System.out.println(1l << 100000);
        System.out.println(Integer.parseInt("10000", 2));

        System.out.println("BITWISE OPERATOR");
        System.out.println("Integer.toBinaryString(i) " + Integer.toBinaryString(i));
        System.out.println("Integer.toBinaryString(j) " + Integer.toBinaryString(j));
        System.out.println("Integer.toBinaryString(~i) " + Integer.toBinaryString(~i));
        System.out.println("~i " + ~i);
        System.out.println("Integer.toBinaryString(i & j) " + Integer.toBinaryString(i & j));
        System.out.println("Integer.toBinaryString(i | j) " + Integer.toBinaryString(i | j));
        System.out.println("Integer.toBinaryString(i ^ j) " + Integer.toBinaryString(i ^ j));


        int number = 8; //0000 1000
        System.out.println("Original number : " + number + ":: " + Integer.toBinaryString(number));

        //left shifting bytes with 1 position
        number = number << 33;

        //equivalent of multiplication of 2
        System.out.println("value of number after left shift: " + number + ":: " + Integer.toBinaryString(number));

        number = -8;
        System.out.println("Original number Neg: " + number + ":: " + Integer.toBinaryString(number));

        //right shifting bytes with sign 1 position
        number = number >> 1;

        //equivalent of division of 2
        System.out.println("value of number after right shift with sign: " + number + ":: "
                + Integer.toBinaryString(number));

        number = -8;
        //right shifting bytes without sign 1 position
        number = number >>> 1;

        //equivalent of division of 2
        System.out.println("value of number after right shift with sign: " + number + ":: "
                + Integer.toBinaryString(number));



    }

    private static void bitSet() {
        final BitSet set = new BitSet(SIZE);

        final long startTime = new Date().getTime();
        for (int i = 0; i < SIZE; i++) {
            set.set(1);
        }
        System.out.println(startTime - new Date().getTime() + " meme " + Runtime.getRuntime().totalMemory());

        for (int i = 0; i < SIZE; i++) {
            set.get(i);
        }
        System.out.println(startTime - new Date().getTime());
    }

    private static void booleanArray() {
        final boolean[] array = new boolean[SIZE];
        final long startTime = new Date().getTime();
        for (int i = 0; i < SIZE; i++) {
            array[i] = true;
        }
        System.out.println(startTime - new Date().getTime() + " meme " + Runtime.getRuntime().totalMemory());

        for (int i = 0; i < SIZE; i++) {
        }
        System.out.println(startTime - new Date().getTime());
    }

}
