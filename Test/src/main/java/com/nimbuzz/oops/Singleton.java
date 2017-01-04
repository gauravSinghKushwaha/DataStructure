package com.nimbuzz.oops;

public class Singleton {

    private static Object instance;

    protected Singleton() {

    }

    public static final Object getInstance() {
        if (instance == null) {
            instance = new Object();
        }
        return instance;
    }
}
