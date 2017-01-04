package com.gaurav.recurrsion;

/**
 * http://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
 * 
 * @author gkushwaha
 *
 */
public class Fibonacci {

    public static void main(final String args[]) {
        System.out.println(new Fibonacci().fib(0));
    }

    int fib(final int N) {
        if (N <= 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }

        return fib(N - 2) + fib(N - 1);
    }
}
