package com.gaurav.thread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinPoolTest {

    private static final UncaughtExceptionHandler handler = new UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(final Thread t, final Throwable e) {
            System.err.println("Thread t " + t + " died because of Throwable e" + e);
        }
    };
    private final static boolean asyncMode = false;

    private static final ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(),
            ForkJoinPool.defaultForkJoinWorkerThreadFactory, handler, asyncMode);

    public static final void main(final String args[]) {
        final ForkJoinTask<Object> task;
        // pool.invoke(task);
    }
}
