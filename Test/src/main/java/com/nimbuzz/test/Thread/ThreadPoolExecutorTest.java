package com.nimbuzz.test.Thread;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    public static void main(final String args[]) {
        final long keepAliveTime = 5;
        final int maximumPoolSize = 5;
        final int corePoolSize = 5;
        final TimeUnit unit = TimeUnit.SECONDS;
        final BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(100);
        final ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);

        for (int i = 0; i < 10; i++) {
            final Runnable command = new Command(i);
            System.out.println("QUEUE SIZE :: " + executor.getQueue().size() + " getCompletedTaskCount "
                    + executor.getCompletedTaskCount() + " getTaskCount " + executor.getTaskCount()
                    + " getActiveCount "
                    + executor.getActiveCount());
            executor.execute(command);
        }
    }

}

class Command implements Runnable {
    int seq;

    public Command(final int seq) {
        super();
        this.seq = seq;
    }

    @Override
    public void run() {
        System.out.println("seq::" + seq);
        throw new NoSuchElementException("thrown");
    }
}