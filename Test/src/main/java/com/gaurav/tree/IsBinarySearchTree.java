package com.gaurav.tree;

/**
 * https://www.hackerrank.com/challenges/ctci-is-binary-search-tree?h_r=next-challenge&h_v=zen
 * 
 * @author gkushwaha
 *
 */
public class IsBinarySearchTree {
    static class Node {
        int data;
        Node left;
        Node right;
    }

    public static void main(final String args[]) {
        final Node root = new Node();
        final Node left = new Node();
        final Node right = new Node();
        final Node leftLeft = new Node();
        final Node leftRight = new Node();
        final Node rightLeft = new Node();
        final Node rightRight = new Node();

        root.data = 11;
        left.data = 6;
        right.data = 19;
        leftLeft.data = 4;
        leftRight.data = 8;
        rightLeft.data = 17;
        rightRight.data = 43;

        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        right.left = rightLeft;
        right.right = rightRight;

        final IsBinarySearchTree isBinarySearchTree = new IsBinarySearchTree();
        System.out.println(isBinarySearchTree.checkBST(root));
    }

    boolean checkBST(final Node root) {
        return checkBST(root, 0, 10000);
    }

    boolean checkBST(final Node root, final int minValue, final int maxValue) {
        if (root == null) {
            return true;
        }

        if (root.data < minValue || root.data > maxValue) {
            return false;
        }

        return checkBST(root.left, minValue, root.data - 1) && checkBST(root.right, root.data + 1, maxValue);
    }

}
