package com.java.thread;

public class TestWait {

    private static final Object obj = new Object();

    public static void main(final String args[]) throws InterruptedException {
        new Thread(new Task()).start();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            try {
                method1();
                method2();
            } catch (final InterruptedException e) {
                System.err.println(e);
            }
        }

        public synchronized void method1() throws InterruptedException {
            System.out.println("TestWait >> method1 waiting");
            wait(900);
            System.out.println("TestWait >> method1 waiting done");
        }

        public void method2() throws InterruptedException {
            synchronized (obj) {
                System.out.println("TestWait >> method2 waiting");
                obj.wait(900);
                System.out.println("TestWait >> method2 waiting done");
            }
        }
    }
}
