package com.java.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierUsingWait {
    private static final Object obj = new Object();
    private static volatile AtomicInteger waitCount = new AtomicInteger(0);
    private final int maxCount;

    public CyclicBarrierUsingWait(final int maxCount) {
        this.maxCount = maxCount;
    }

    public void await() {
        synchronized (obj) {
            try {
                final int incrementAndGet = waitCount.incrementAndGet();
                System.out.println(" waitCount " + incrementAndGet);
                if (incrementAndGet >= maxCount) {
                    obj.notifyAll();
                } else {
                    obj.wait();
                }
            } catch (final InterruptedException e) {
                System.err.println(e);
            }
        }
    }

    public static void main(final String args[]) throws InterruptedException {
        final CyclicBarrierUsingWait cyclicBarrierUsingWait = new CyclicBarrierUsingWait(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new Task(cyclicBarrierUsingWait), i + " ").start();
        }

    }

    private static class Task implements Runnable {
        CyclicBarrierUsingWait cyclicBarrierUsingWait;

        public Task(final CyclicBarrierUsingWait cyclicBarrierUsingWait) {
            this.cyclicBarrierUsingWait = cyclicBarrierUsingWait;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " waiting");
            cyclicBarrierUsingWait.await();
            System.out.println(Thread.currentThread().getName() + " starts again");
        }

    }

}
