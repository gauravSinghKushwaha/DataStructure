package com.nimbuzz.test.Test;

public class SuperClass {
    private final int a;
    int b;
    public int c;
    public static final int d = 4;

    public SuperClass(final int a, final int b, final int c) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private void callChildClassMethod() {
        final ChildClass childClass = new ChildClass();
        childClass.someMethod();
        System.out.println(childClass.e + "" + ChildClass.f);
    }

    private void callStaticChildClass() {
        final StaticChildClass staticChildClass = new StaticChildClass();
        staticChildClass.someMethod();
        System.out.println(StaticChildClass.h + "" + staticChildClass.g);
    }
    public static void main(final String args[]) {
        final SuperClass superClass = new SuperClass(1, 2, 3);
        superClass.callChildClassMethod();
        new SuperClass(1, 2, 3).callStaticChildClass();
        final SuperClass.StaticChildClass c = new SuperClass.StaticChildClass();
        final SuperClass.ChildClass c2 = superClass.new ChildClass();
    }

    private static final class StaticChildClass {
        private final int g = 7;
        static final int h = 8;
        private void someMethod() {
            System.out.println(d);
        }
    }

    private final class ChildClass {
        private final int e = 5;
        static final int f = 6;
        private void someMethod() {
            System.out.println(a + "" + b + "" + c);
        }
    }
}
