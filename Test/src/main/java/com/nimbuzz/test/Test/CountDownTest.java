package com.nimbuzz.test.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * SAME THREAD CALLLING COUNT-DOWN N NUMBER OF TIMES.....AND COUNT GETTING LOWER
 * 
 * @author gkushwaha
 *
 */

public class CountDownTest {

    public static void main(final String args[]) {
        final CountDownLatch latch = new CountDownLatch(5);

        new Thread(() -> {
            try {
                latch.await();
            } catch (final Exception e) {
                System.err.println(e);
            }
            System.out.println("broken");
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                latch.countDown();
            }
        }).start();
    }
}
