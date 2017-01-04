package com.gaurav.misc.algo;

import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks
 * 
 * @author gkushwaha
 *
 */
public class QueueWithTwoStacks {
    public static class MyQueue<T> {
        Stack<T> stackNewestOnTop = new Stack<T>();
        Stack<T> stackOldestOnTop = new Stack<T>();

        public void enqueue(final T value) {
            stackNewestOnTop.push(value);
            // add will shift other entries one index upwards...as stack implementation is based on vector
            stackOldestOnTop.add(0, value);
        }

        public T peek() {
            return stackOldestOnTop.peek();
        }

        public T dequeue() {
            final int index = stackNewestOnTop.size() - 1;
            final T pop = stackOldestOnTop.pop();
            // remove will shift other entries one index downwards...as stack implementation is based on vector
            stackNewestOnTop.removeElementAt(index);
            return pop;
        }
    }

    public static void main(final String[] args) {
        final MyQueue<Integer> queue = new MyQueue<Integer>();

        final Scanner scan = new Scanner(System.in);
        final int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            final int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}
