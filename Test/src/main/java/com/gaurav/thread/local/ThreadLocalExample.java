package com.gaurav.thread.local;

public class ThreadLocalExample {
    protected static ThreadLocal<Integer> COUNTER = new ThreadLocal<Integer>() {

        @Override
        protected Integer initialValue() {
            return 0;
        }

    };
    public static void main(final String args[]) {
        System.out.println("Counter initial value = " + COUNTER.get());
        COUNTER.set(1);
        new ThreadLocalExample().method();
    }

    public void method() {
        System.out.println(ThreadLocalExample.class.getName() + " method() counter = " + COUNTER.get());
        new ChildClass().method();
    }

}
