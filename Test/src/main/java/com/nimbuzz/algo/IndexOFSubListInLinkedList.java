package com.nimbuzz.algo;

import static java.lang.String.valueOf;

/**
 * Find index of a sublist in a list
 * 
 * @author gkushwaha
 *
 */
public class IndexOFSubListInLinkedList {

    private static class LinkedListNode {
        private final String val;
        private final LinkedListNode next;

        public LinkedListNode(final int val, final LinkedListNode next) {
            this.val = valueOf(val);
            this.next = next;
        }
    }

    public static void main(final String args[]) {
        final LinkedListNode root =
                new LinkedListNode(1, new LinkedListNode(2, new LinkedListNode(3, new LinkedListNode(4,
                        new LinkedListNode(5, null)))));
        final LinkedListNode subList = new LinkedListNode(3, new LinkedListNode(4, new LinkedListNode(5, null)));

        printLinkedList(root);
        printLinkedList(subList);

        System.out.println(find(root, subList));
    }

    private static void printLinkedList(LinkedListNode root) {
        System.out.println();
        while (root != null) {
            System.out.print(" " + root.val);
            root = root.next;
        }
        System.out.println();
    }

    static int find(final LinkedListNode list, final LinkedListNode sublist) {

        if (list == null || sublist == null) {
            return -1;
        }

        final int listSize = getSize(list);
        final int sublistSize = getSize(sublist);
        final int diffSize = listSize - sublistSize;
        LinkedListNode tempSubList = sublist;
        LinkedListNode tempList = list;
        int index = 0;
        Search: while (tempSubList.next != null) {
            for (int i = 0; i < sublistSize; i++) {
                if (tempList == null) {
                    return -1;
                }
                if (!tempSubList.val.equals(tempList.val)) {
                    tempList = list;
                    for (int j = 0; j <= index; j++) {
                        tempList = tempList.next;
                    }
                    tempSubList = sublist;
                    ++index;
                    continue Search;
                }
                tempSubList = tempSubList.next;
                tempList = tempList.next;
            }
            // end of for loop means matched
            return index;
        }

        System.out.println(listSize + " " + sublistSize + " " + diffSize);
        System.out.println("Index " + index);
        return -1;
    }

    private static int getSize(LinkedListNode list) {
        int listSize = 1;
        do {
            listSize++;
            list = list.next;
        } while (list.next != null);
        return listSize;
    }
}
