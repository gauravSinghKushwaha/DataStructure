package com.java.thread.local;

import static java.lang.System.out;

public class ChildClass {
    public void method() {
        final ThreadLocal<Integer> counter = ThreadLocalExample.COUNTER;
        out.println(ChildClass.class.getName() + " method() " + " counter = " + counter.get());
        counter.set(2);
        new ChildClass2().method();
    }
}
