package com.nimbuzz.algo.linkedlist;

import java.util.Stack;

public class IsPalindrome {
    public static void main(final String args[]) {
        final LNode<Integer> head =
                new LNode<Integer>(1, new LNode<Integer>(2, new LNode<Integer>(3, new LNode<Integer>(3,
                        new LNode<Integer>(2, new LNode<Integer>(1, null, null), null), null), null), null), null);
        // final LNode<Integer> head =
        // new LNode<Integer>(1, new LNode<Integer>(2, new LNode<Integer>(3, new LNode<Integer>(4,
        // new LNode<Integer>(3, new LNode<Integer>(2, new LNode<Integer>(1, null, null), null), null),
        // null), null), null), null);

        LinkedListCreator.print(head);
        System.out.println(isPalindrome(head));
    }

    private static boolean isPalindrome(final LNode<Integer> head) {
        final Stack<Integer> stack = new Stack<Integer>();
        LNode<Integer> fastPointer = head;
        LNode<Integer> slowPointer = head;
        while (fastPointer != null) {
            stack.push(slowPointer.value());
            if (fastPointer.next() != null) {
                fastPointer = fastPointer.next().next();
                slowPointer = slowPointer.next();
            } else {
                fastPointer = null;
            }
        }

        // slow pointer points to mid now
        while (slowPointer != null) {
            if (stack.pop() != slowPointer.value()) {
                return false;
            }
            slowPointer = slowPointer.next();
        }
        return true;
    }
}
