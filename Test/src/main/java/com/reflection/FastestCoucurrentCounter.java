package com.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import sun.misc.Unsafe;

public class FastestCoucurrentCounter {
    public static void main(final String args[]) throws Exception {
        final Unsafe unsafe = getUnsafe();
        for (int i = 0; i < 100000; i++) { // 100000 threads
            new Thread() {
                @Override
                public void run() {
                    try {
                        final CASCounter casCounter = new CASCounter(unsafe);
                        casCounter.increment();
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }

            }.start();
        }
    }

    private static Unsafe getUnsafe() throws NoSuchMethodException, InstantiationException, IllegalAccessException,
    InvocationTargetException {
        final Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        final Unsafe unsafe = constructor.newInstance();
        return unsafe;
    }
}

/**
 * Faster then Synchronization , Lock API
 * 
 * @author gkushwaha
 *
 */
class CASCounter implements Counter {
    private volatile long counter = 0;
    private final Unsafe unsafe;
    private final long offset;

    public CASCounter(final Unsafe unsafe) throws Exception {
        this.unsafe = unsafe;
        offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
    }

    @Override
    public void increment() {
        long before = counter;
        while (!unsafe.compareAndSwapLong(this, offset, before, before + 1l)) {
            before = counter;
        }
    }

    @Override
    public long getCounter() {
        return counter;
    }
}