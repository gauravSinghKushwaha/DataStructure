package com.nimbuzz.oops;

public class SingletonClient {

    @SuppressWarnings("static-access")
    public static void main(final String args[]) {
        final Object instance1 = Singleton.getInstance();
        final Object instance2 = Singleton.getInstance();
        new Singleton();
        final Object instance3 = Singleton.getInstance();
        System.out.println(instance1.equals(instance2));
        System.out.println(instance1 == instance2);
        System.out.println(instance1.equals(Singleton.getInstance()));
    }
}
