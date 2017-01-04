package com.gaurav.unsafe;

import static java.lang.Math.pow;
import static java.lang.System.nanoTime;

import java.lang.reflect.Field;

public class LazySetLong {

    private static final long valueOffset;
    static long offset;
    static sun.misc.Unsafe unsafe;
    volatile long o;

    static {
        try {
            final Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (sun.misc.Unsafe) field.get(null);
            valueOffset = unsafe.objectFieldOffset(LazySetLong.class.getDeclaredField("o"));
        } catch (final Exception e) {
            throw new AssertionError(e);
        }
    }

    /**
     * @param o
     *            It is only really useful where the field is volatile, and is thus expected to change unexpectedly.
     * @throws NoSuchFieldException
     */
    public void lazySet(final long o) throws NoSuchFieldException {
        unsafe.putOrderedLong(this, valueOffset, o);
    }

    private long callPutOrderedLong(final long times) throws NoSuchFieldException {

        final long l = times / 2;

        final long start = nanoTime();

        for (long i = 0; i < l; i++) {

            lazySet(o + 1);
        }

        return nanoTime() - start;

    }

    private long setTheVolatileDirectly(final long times) {

        final long l = times / 2;

        final long start = nanoTime();

        for (long i = 0; i < l; i++) {
            o++;
        }

        return nanoTime() - start;

    }

    public static void main(final String... args) throws NoSuchFieldException {

        final LazySetLong obj = new LazySetLong();

        for (int pwr = 2; pwr < 11; pwr++) {
            final long i = (long) pow(9, pwr);
            final long time1 = obj.callPutOrderedLong(i);
            final long time2 = obj.setTheVolatileDirectly(i);
            System.out
            .printf("Performing %,d loops, callPutOrderedLong() took %.3f us and setting the volatile directly took %.3f us on average, ratio=%.1f%n",
                    i, time1 / 1e3, time2 / 1e3, (double) time1 / time2);
        }

        System.out.println("\nJust printing work so that it is not optimized out, work=" + obj.o);

    }

}