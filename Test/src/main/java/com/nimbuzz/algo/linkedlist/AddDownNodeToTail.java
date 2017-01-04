package com.nimbuzz.algo.linkedlist;

import static com.nimbuzz.algo.linkedlist.LinkedListCreator.print;

/**
 * The linked list node has additional pointer the down node. remove down node and add those to tail
 * 
 * Input
 * 
 * <code>
   0-> 1 -> 2 - > 3 -> 4 -> 5 -> 6
       |               |
       7               8->9
       |
       10 -> 11
 * </code>
 * 
 * output
 * 
 * <code>
   0->1->2->3->4->5->6->7->8->9->10->11
 * </code>
 * 
 * @author gkushwaha
 *
 */
public class AddDownNodeToTail {

    public static void main(final String args[]) {

        final LNode<Integer> head = LinkedListCreator.createLinkedList(6);
        print(head);

        // creating input
        setDownNode(head);
        print(head);

        // add down node to tail of linked list
        addDownToTail(head);
        print(head);
    }

    private static LNode<Integer> addDownToTail(LNode<Integer> head) {
        while (head.next() != null) {
            if (head.down() != null) {
                head.addToTail(head.down());
                head.setDown(null);
            }
            head = head.next();
        }
        return head;
    }

    private static void setDownNode(LNode<Integer> head) {
        while (head.next() != null) {
            if (head.value() == 1) {
                final LNode<Integer> down10 = new LNode<Integer>(10, null, null);
                final LNode<Integer> next11 = new LNode<Integer>(11, null, down10);
                down10.setNext(next11);
                final LNode<Integer> down7 = new LNode<Integer>(7, null, null);
                down7.setDown(down10);
                head.setDown(down7);
            }
            if (head.value() == 4) {
                final LNode<Integer> down8 = new LNode<Integer>(8, null, null);
                down8.setNext(new LNode<Integer>(9, null, down8));
                head.setDown(down8);
            }
            head = head.next();
        }
    }

}
