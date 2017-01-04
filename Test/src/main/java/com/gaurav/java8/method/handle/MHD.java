package com.gaurav.java8.method.handle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MHD {
    public static void main(final String[] args) throws Throwable {
        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mh =
                lookup.findStatic(Math.class, "pow", MethodType.methodType(double.class, double.class, double.class));
        mh = MethodHandles.insertArguments(mh, 1, 10d);
        System.out.printf("2^10 = %d%n", (int) mh.invoke(2.0));
    }
}