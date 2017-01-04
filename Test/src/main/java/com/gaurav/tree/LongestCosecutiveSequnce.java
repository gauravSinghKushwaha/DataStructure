package com.gaurav.tree;

import java.util.ArrayList;

/**
 * 
 * http://www.geeksforgeeks.org/maximum-consecutive-increasing-path-length-in-binary-tree/
 * 
 * http://www.geeksforgeeks.org/longest-consecutive-sequence-binary-tree/
 * 
 * @author gkushwaha
 *
 */
public class LongestCosecutiveSequnce {
    public static void main(final String args[]) {

        final TNode<Integer> leftLeft= new TNode<Integer>(3, null, null);;
        final TNode<Integer> rightLeft = new TNode<Integer>(5, null, null);
        final TNode<Integer> rightRightLeft = new TNode<Integer>(7, null, null);
        final TNode<Integer> rightRight = new TNode<Integer>(6, rightRightLeft, null);
        final TNode<Integer> left = new TNode<Integer>(2, leftLeft, null);
        final TNode<Integer> right = new TNode<Integer>(4, rightLeft, rightRight);
        final TNode<Integer> root = new TNode<Integer>(1, left, right);

        System.out.println("********* PRE ********");
        new PreOrderTraversal().traverse(root);
        System.out.println();
        System.out.println("********** IN ************");
        new InOrderTraversal().traverse(root);
        System.out.println();
        System.out.println("*********** POST ***********");
        new PostOrderTraversal().traverse(root);
        System.out.println();
        LCP(root, root.value(), new ArrayList<Integer>());
    }

    private static void LCP(final TNode<Integer> root, final int expectedValue, ArrayList<Integer> arrayList) {
        if (root == null) {
            return;
        }
        int newValue = root.value() + 1;

        // it's sequence
        if (root.value() == expectedValue) {
            arrayList.add(root.value());
            newValue = expectedValue + 1;
        } else {
            System.out.println(arrayList);
            arrayList = new ArrayList<Integer>();
            arrayList.add(root.value());
        }
        LCP(root.left(), newValue, arrayList);
        LCP(root.right(), newValue, arrayList);
        // System.out.println(arrayList);
    }

}
