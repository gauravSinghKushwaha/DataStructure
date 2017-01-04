package com.gaurav.tree;

import static com.gaurav.linkedlist.LinkedListCreator.createLinkedList;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.gaurav.linkedlist.LNode;

/**
 * 
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=700289
 * 
 * http://www.geeksforgeeks.org/given-linked-list-representation-of-complete-tree-convert-it-to-linked-representation/
 * 
 * 
 * Given Linked List Representation of Complete Binary Tree, construct the Binary tree. A complete binary tree can be
 * represented in an array in the following approach.
 * 
 * If root node is stored at index i, its left, and right children are stored at indices 2*i+1, 2*i+2 respectively.
 * Suppose tree is represented by a linked list in same way, how do we convert this into normal linked representation of
 * binary tree where every node has data, left and right pointers? In the linked list representation, we cannot directly
 * access the children of the current node unless we traverse the list.
 * 
 * 
 * We are mainly given level order traversal in sequential access form. We know head of linked list is always is root of
 * the tree. We take the first node as root and we also know that the next two nodes are left and right children of
 * root. So we know partial Binary Tree. The idea is to do Level order traversal of the partially built Binary Tree
 * using queue and traverse the linked list at the same time. At every step, we take the parent node from queue, make
 * next two nodes of linked list as children of the parent node, and enqueue the next two nodes to queue.
 * 
 * 1. Create an empty queue.
 * 
 * 2. Make the first node of the list as root, and enqueue it to the queue.
 * 
 * 3. Until we reach the end of the list, do the following.
 * 
 * ………a. Dequeue one node from the queue. This is the current parent.
 * 
 * ………b. Traverse two nodes in the list, add them as children of the current parent.
 * 
 * ………c. Enqueue the two nodes into the queue.
 * 
 * 
 * @author gkushwaha
 *
 */
public class LinkedListToBinaryTree {
    // to keep parent nodes
    private static final Queue<TNode<Integer>> q = new ArrayBlockingQueue<TNode<Integer>>(99);

    public static void main(final String args[]) {
        final LNode<Integer> linkedList = createLinkedList(20);
        new LinkedListToBinaryTree().convert(linkedList);
    }

    private TNode<Integer> convert(LNode<Integer> linkedList) {
        // q.offer(linkedList);
        final TNode<Integer> bRoot = new TNode<Integer>(linkedList.value(), null, null);
        q.offer(bRoot);
        while (linkedList != null && !linkedList.isTail()) {
            linkedList = linkedList.next();
            final TNode<Integer> poll = q.poll();
            final TNode<Integer> leftTop = new TNode<Integer>(linkedList.value(), null, null);
            linkedList = linkedList.next();
            final TNode<Integer> rightTop = new TNode<Integer>(linkedList.value(), null, null);
            poll.setLeft(leftTop);
            poll.setRight(rightTop);
            q.offer(leftTop);
            q.offer(rightTop);
        }
        System.out.println(bRoot);
        return bRoot;
    }
}
