package com.nimbuzz.algo.linkedlist;

import static com.nimbuzz.algo.tree.BinaryTreeCreator.createBinaryTree;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import com.nimbuzz.algo.tree.TNode;

public class BinaryTreeToLinkedList {
    private static final Queue<TNode<Integer>> q = new ArrayBlockingQueue<TNode<Integer>>(99);

    public static void main(final String args[]) {
        final TNode<Integer> root = createBinaryTree();
        System.out.println(new BinaryTreeToLinkedList().traverse(root));
    }

    public LNode<Integer> traverse(final TNode<Integer> root) {
        q.offer(root);
        LNode<Integer> head = null;
        LNode<Integer> tail = null;
        while (!q.isEmpty()) {
            final TNode<Integer> top = q.poll();
            tail = visit(tail, top);
            if (head == null) {
                head = tail;
            }
            if (top.left() != null) {
                q.offer(top.left());
            }
            if (top.right() != null) {
                q.offer(top.right());
            }
        }
        return head;
    }

    private LNode<Integer> visit(final LNode<Integer> head, final TNode<Integer> node) {
        if (head == null) {
            return new LNode<Integer>(node.value(), null, null);
        }
        return head.addNode(new LNode<Integer>(node.value(), null, head));
    }
}
