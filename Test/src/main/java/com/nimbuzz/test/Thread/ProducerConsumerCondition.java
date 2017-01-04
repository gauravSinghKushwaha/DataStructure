package com.nimbuzz.test.Thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerCondition {

    private static final Lock lock = new ReentrantLock();
    Condition full = lock.newCondition();
    Condition empty = lock.newCondition();
    int count = 10;
    volatile int food = 0;

    java.util.List<Integer> list = new ArrayList();

    public static void main(final String args[]) {
        final ProducerConsumerCondition producerConsumerCondition = new ProducerConsumerCondition();
        new Thread(producerConsumerCondition.new Consumer()).start();
        new Thread(producerConsumerCondition.new Producer()).start();
    }

    private class Producer implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (true) {
                    if (list.size() == count) {
                        empty.await();
                    }
                    for (int i = 0; i < count; i++) {
                        final int e = ++food;
                        list.add(e);
                        System.out.println("produced " + e);
                        Thread.sleep(1000);
                    }
                    full.signal();
                }
            } catch (final Exception e) {
                System.err.println(e);

            } finally {
                lock.unlock();
            }
        }
    }

    private class Consumer implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                while (true) {
                    if (list.size() == 0) {
                        full.await();
                    }

                    final Iterator<Integer> itr = list.iterator();
                    while (itr.hasNext()) {
                        System.out.println("Consumer " + itr.next());
                        itr.remove();
                        Thread.sleep(1000);
                    }

                    empty.signal();
                }
            } catch (final Exception e) {
                System.err.println(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
