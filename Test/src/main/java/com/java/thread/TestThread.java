package com.java.thread;

public class TestThread {

    public static void main(final String args[]) {
        System.out.println(Thread.currentThread().getPriority() + " :: " + Thread.currentThread().getName());
        new Thread() {
            @Override
            public void run() {
                    System.out
                            .println(Thread.currentThread().getPriority() + " :: " + Thread.currentThread().getName());
                // Thread.currentThread().setDaemon(true);
                try {
                    Thread.sleep(10000);
                } catch (final InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                    System.out.println(Thread.currentThread().getPriority() + " :: " + Thread.currentThread().getName()
                            + ":: finished");

            }

        }.start();
        try {
            Thread.sleep(100);
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getPriority() + " :: " + Thread.currentThread().getName()
                + ":: finished");
    }
}
