package com.nimbuzz.algo.tree;

import static com.nimbuzz.algo.linkedlist.LinkedListCreator.createLinkedList;

import com.nimbuzz.algo.linkedlist.LNode;
import com.nimbuzz.algo.linkedlist.LinkedListCreator;

public class SortedLinkedListToBST {
    public static void main(final String args[]) {
        final LNode<Integer> head = createLinkedList(4);
        final SortedLinkedListToBST sortedLinkedListToBST = new SortedLinkedListToBST();
        final int size = sortedLinkedListToBST.count(head);
        LinkedListCreator.print(head);
        System.out.println("LNode count " + size);
        final TNode<Integer> root = sortedLinkedListToBST.convertToBST(head, 0, size - 1);
        root.printTree();
    }

    private TNode<Integer> convertToBST(final LNode<Integer> linkedList, final int start, final int end) {
        System.out.println("currNode = " + linkedList.value() + " start= " + start + " end = " + end);
        if (start == 0 && end <= 0 || start == end) {
            System.out.println("recurr terminates");
            return new TNode<Integer>(linkedList.value(), null, null);
        }

        final int mid = (end + start) / 2;

        final TNode<Integer> left = convertToBST(getNode(linkedList, start), start, mid - 1);
        final TNode<Integer> right = convertToBST(getNode(linkedList, mid + 1), mid + 1, end);

        final LNode<Integer> midElem = getNode(linkedList, mid);
        System.out.println(" mid = " + mid + " midElem = " + midElem.value());

        System.out.println("left= " + left.value() + " right= " + right.value() + " root= " + midElem.value());
        final TNode<Integer> tNode = new TNode<Integer>(midElem.value(), left, right);
        return tNode;
    }

    private LNode<Integer> getNode(LNode<Integer> linkedList, final int start) {
        int count = 0;
        while (!linkedList.isTail() && count != start) {
            count = count + 1;
            linkedList = linkedList.next();
        }
        return linkedList;
    }

    private int count(LNode<Integer> head) {
        int count = 1;
        while (!head.isTail()) {
            count = count + 1;
            head = head.next();
        }
        return count;
    }

}
