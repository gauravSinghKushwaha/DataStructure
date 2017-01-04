package com.inner;

public class Super {
    private final int superI;
    private static final int staticK = 10;

    public Super(final int superI) {
        this.superI = superI;
        System.out.println("Constructor Super");
    }

    public static void staticMethod() {
        System.out.println("staticMethod");
    }

    public void method() {
        System.out.println("method");
    }

    private static class StaticInner {

        private StaticInner() {
            System.out.println("Constructor StaticInner");
        }

        public static void staticMethod() {
            System.out.println("StaticInner --> staticMethod " + staticK);

        }

        public void method() {
            System.out.println("StaticInner --> method");

        }
    }

    private class Inner {

        private Inner() {
            System.out.println("Constructor Inner");
        }

        // public static void staticMethod() {
        // System.out.println("Inner --> staticMethod" + staticK);
        //
        // }

        public void method() {
            class MethodClass {
                public void methodMethod() {
                    System.out.println("Inner --> method ->> methodMethod " + superI + " " + staticK);
                }
            }
            System.out.println("Inner --> method" + superI + " " + staticK);
            new MethodClass().methodMethod();
        }
    }

    public static void main(final String args[]) {
        new StaticInner().method();
        StaticInner.staticMethod();
        final Inner inner = new Super(1).new Inner();
        inner.method();
    }
}
