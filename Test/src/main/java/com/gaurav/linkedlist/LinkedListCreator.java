package com.gaurav.linkedlist;

import static java.lang.System.out;

/**
 * Creates linked list . head starts with 1 and tails ends with 10
 * 
 * @author gkushwaha
 *
 */
public final class LinkedListCreator {
    public static final LNode<Integer> createLinkedList(final int limit) {
        final LNode<Integer> head = new LNode<Integer>(0, null, null);
        addNodes(limit, head);
        return head;
    }

    private static void addNodes(final int limit, final LNode<Integer> head) {
        LNode<Integer> tmp = head;
        for (int i = 1; i <= limit; i++) {
            tmp = tmp.addNode(new LNode<Integer>(i, null, tmp));
        }
    }

    public static void main(final String args[]) {
        print(createLinkedList(10));

    }

    public static void print(LNode<Integer> head) {
        out.println();
        while (head != null) {
            out.println(head.value());
            head = head.next();
        }
    }
}
