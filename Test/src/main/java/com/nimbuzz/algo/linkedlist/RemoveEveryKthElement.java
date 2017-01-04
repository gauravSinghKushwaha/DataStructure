package com.nimbuzz.algo.linkedlist;

/**
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=700297
 * 
 * 
 * Input >> linked list 1 2 3 4 5 6 7 8 to delete every 3rd . out put 1 2 4 5 7 8
 * 
 * 
 * * Input >> linked list 1 2 3 4 5 6 7 8 to delete every 2nd . out put 1 3 5 7
 * 
 * @author gkushwaha
 *
 */
public class RemoveEveryKthElement {

    public static void main(final String args[]) {
        final LNode<Integer> root = LinkedListCreator.createLinkedList(10);
        System.out.println(root);
        new RemoveEveryKthElement().removeElement(root, 3);
        System.out.println(root);
    }

    private void removeElement(final LNode<Integer> root, final int k) {
        LNode<Integer> tmp = root;
        int i = 1;
        do {
            ++i;
            tmp = tmp.next();
            System.out.println(tmp);
            if (i % k == 0) {
                tmp = tmp.remove(tmp);
            }
        } while (!tmp.isTail());
    }
}
