package com.nimbuzz.algo.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListGotCycle {


    public static void main(final String args[]) {
        final LNode<Integer> head = LinkedListCreator.createLinkedList(10);
        LinkedListCreator.print(head);
        createCycle(head);
        System.out.println("hasCycleUsingSet => " + hasCycleUsingSet(head));
        System.out.println("hasCycle => " + hasCycle(head));

    }

    /**
     * 10th next is 5th...cycle goes from 5 6 7 8 9 10 5 6 7 8 9 10 5 .........
     * 
     * @param head
     */
    private static void createCycle(LNode<Integer> head) {
        int i = 0;
        LNode<Integer> temp = null;
        while (head.next() != null) {
            head = head.next();
            i++;
            if (i == 5) {
                temp = head;
            }
        }
        head.addNode(temp);
    }

    /**
     * Maintaing two pointers, fast >> goes two steps at a time, slow goes one step at a time. If they meet, there is
     * cycle
     * 
     * @param head
     * @return
     */
    static boolean hasCycle(final LNode<Integer> head) {
        LNode<Integer> fastPointer = head;
        LNode<Integer> slowPointer = head;
        int count = 0;
        while (fastPointer.next() != null) {
            count++;
            fastPointer = fastPointer.next().next();
            slowPointer = slowPointer.next();
            if (fastPointer != null && fastPointer.equals(slowPointer)) {
                System.out.println("count " + count);
                printCycleStartNode(head, fastPointer);
                return true;
            }
        }

        return false;
    }

    private static void printCycleStartNode(final LNode<Integer> head, final LNode<Integer> fastPointer) {
        // getting node where cycle starts
        // setting slow pointer to head and moving forward, moving faste pointer as well. now this time they should meet
        // at cycle start
        final LNode<Integer> slowPointer = head;
        final LNode<Integer> cycleStartNode = getCycleStartNode(slowPointer, fastPointer);
        System.out.println("cycleStartNode >> " + cycleStartNode.value());
    }

    private static LNode<Integer> getCycleStartNode(LNode<Integer> slowPointer, LNode<Integer> fastPointer) {
        while (fastPointer.next() != null) {
            fastPointer = fastPointer.next();
            slowPointer = slowPointer.next();
            if (fastPointer != null && fastPointer.equals(slowPointer)) {
                return fastPointer;
            }
        }
        return null;
    }

    static boolean hasCycleUsingSet(LNode<Integer> head) {
        if (head == null) {
            return false;
        }
        final Set<LNode<Integer>> visited = new HashSet<LNode<Integer>>();
        do {
            if (visited.contains(head)) {
                return true;
            }
            visited.add(head);
            head = head.next();
        } while (head.next() != null);

        return false;
    }
}
