package com.gaurav.tree;

public class TreeHeight {
    public static int getHeight(final TNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left()), getHeight(root.right())) + 1;
    }

    public static void main(final String arsg[]) {
        System.out.println("height =" + getHeight(BinaryTreeCreator.createBinaryTree()));
    }
}
