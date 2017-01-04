package com.gaurav.java8.collection;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class LambdaScopeTest {

    private final int x = 0;

    class FirstLevel {

        private final  int x = 1;

        void methodInFirstLevel(final int x) {

            // The following statement causes the compiler to generate
            // the error "local variables referenced from a lambda expression
            // must be final or effectively final" in statement A:
            //
            // x = 99;

            final Consumer<Integer> myConsumer = (y) -> {
                System.out.println("x = " + x); // Statement A
                System.out.println("y = " + y);
                System.out.println("this.x = " + this.x);
                System.out.println("LambdaScopeTest.this.x = " + LambdaScopeTest.this.x);
            };

            myConsumer.accept(x);

        }
    }

    static void invoke(final Runnable r) {
        r.run();
    }

    static <T> T invoke(final Callable<T> c) throws Exception {
        return c.call();
    }

    public static void main(final String... args) throws Exception {
        final LambdaScopeTest st = new LambdaScopeTest();
        final LambdaScopeTest.FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(23);

        //
        final String s = invoke(() -> {
            System.out.println("Should call callable");
            return "done";
        });

        invoke(() -> {
            System.out.println("Should call runnable");
        });
    }
}
