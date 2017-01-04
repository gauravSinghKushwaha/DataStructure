package com.nimbuzz.algo.tree;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 
 * http://www.geeksforgeeks.org/find-a-number-in-minimum-steps/
 * 
 * @author gkushwaha
 *
 */
public class NumberInMinSteps {
    static boolean found = false;
    static int attempt = 0;
    static Queue<TNode<Integer>> q = new ArrayBlockingQueue<TNode<Integer>>(100);

    public static void main(final String args[]) {
        new TNode<Integer>(0, null, null);
        final TNode<Integer> root = createTree(null, 9, 0);
        LevelOrderTraversal.traverse(root);
        System.out.println(findNumberViaLevelOrderTraverse(root, 13));
    }

    private static TNode<Integer> createTree(TNode<Integer> root, final int level, int currLevel) {
        TNode<Integer> realRoot = null;
        if (currLevel < level) {
            if (root == null) {
                root = new TNode<Integer>(0, null, null);
                realRoot = root;
            }
            final TNode<Integer> left = new TNode<Integer>(root.value() - (currLevel + 1), null, null);
            final TNode<Integer> right = new TNode<Integer>(root.value() + (currLevel + 1), null, null);
            root.setLeft(left);
            root.setRight(right);
            createTree(root.left(), level, ++currLevel);
            createTree(root.right(), level, ++currLevel);
        }
        return realRoot;
    }

    public static String findNumberViaLevelOrderTraverse(final TNode<Integer> node, final int value) {
        int count = 0;
        final StringBuilder str = new StringBuilder();
        q.offer(node);
        while (!q.isEmpty()) {
            final TNode<Integer> top = q.poll();
            str.append(top.value()).append(" , ");
            count = count + 1;
            if (value == top.value()) {
                return count + "";
            }
            if (top.left() != null) {
                q.offer(top.left());
            }
            if (top.right() != null) {
                q.offer(top.right());
            }
        }
        return "";
    }

}
