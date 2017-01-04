package com.gaurav.thread;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrrierTest {
    public static void main(final String args[]) {
        final CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("barrier reached"));

        new Thread(() -> {
            try {
                barrier.await();
            } catch (final Exception e) {
                System.err.println(e);
            }
            System.out.println("thread-1");
        }).start();

        new Thread(() -> {
            try {
                barrier.await();
            } catch (final Exception e) {
                System.err.println(e);
            }
            System.out.println("thread-2");
        }).start();

        new Thread(() -> {
            try {
                barrier.await();
            } catch (final Exception e) {
                System.err.println(e);
            }
            System.out.println("thread-3");
        }).start();

        new Thread(() -> {
            try {
                barrier.await();
            } catch (final Exception e) {
                System.err.println(e);
            }
            System.out.println("thread-4");
        }).start();

        new Thread(() -> {
            try {
                barrier.await();
            } catch (final Exception e) {
                System.err.println(e);
            }
            System.out.println("thread-5");
        }).start();
    }
}
