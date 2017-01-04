package com.nimbuzz.algo.tree;

import static com.nimbuzz.algo.tree.LevelOrderTraversal.traverse;

/**
 * 
 * http://www.geeksforgeeks.org/flip-binary-tree/
 * 
 * @author gkushwaha
 *
 */
public class FlipBinaryTree {

    public static void main(final String args[]) {

        // final TNode<Integer> root =
        // new TNode<Integer>(1, new TNode<Integer>(2, null, null), new TNode<Integer>(3, new TNode<Integer>(4,
        // null, null), new TNode<Integer>(5, null, null)));

        final TNode<Integer> l =
                new TNode<Integer>(2, new TNode<Integer>(4, null, null), new TNode<Integer>(5, null, null));

        final TNode<Integer> r =
                new TNode<Integer>(3, new TNode<Integer>(6, null, null), new TNode<Integer>(7, null, null));

        final TNode<Integer> root = new TNode<Integer>(1, l, r);

        traverse(root);
        System.out.println();

        final TNode<Integer> rootV = flipBinaryTree(root);
        System.out.println();
        traverse(rootV);

        final String s = "Dollly";
        s.concat("HEllo");
        final StringBuffer s1 = new StringBuffer("Amit");
        final StringBuffer s2 = new StringBuffer("Amit");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }

    private static TNode<Integer> flipBinaryTree(TNode<Integer> root) {
        if (root == null) {
            return null;
        }
        if (root.left() == null && root.right() == null) {
            return root;
        }
        final TNode<Integer> parent = root;
        final TNode<Integer> newRoot = flipBinaryTree(root.left());

        System.out.println("parent-value " + parent.value() + " newRoot " + newRoot.value());

        // temp
        final TNode<Integer> top = root;
        final TNode<Integer> n = new TNode<Integer>(root.value(), root.left(), top);
        root = n;
        return newRoot;

    }

}
