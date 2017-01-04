package com.gaurav.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockQueue {
    private volatile int count = 0;

    private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void main(final String args[]) {
        final ProducerConsumerBlockQueue producerConsumerBlockQueue = new ProducerConsumerBlockQueue();
        // new Thread(producerConsumerBlockQueue.new Producer()).start();
        new Thread(producerConsumerBlockQueue.new Consumer()).start();
    }

    private class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    final int add = ++count;
                    System.out.println("produced " + add);
                    queue.put(add);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    final Integer take = queue.take();
                    System.out.println("Consumed " + take);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
