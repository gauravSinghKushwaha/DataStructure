package com.gaurav.thread.local;

import static java.lang.System.out;

public class ChildClass2 {
    public void method() {
        final ThreadLocal<Integer> counter = ThreadLocalExample.COUNTER;
        out.println(ChildClass2.class.getName() + " method() " + " counter = " + counter.get());
        counter.set(2);
    }
}
