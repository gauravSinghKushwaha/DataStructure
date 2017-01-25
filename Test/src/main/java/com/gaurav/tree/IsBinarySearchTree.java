package com.gaurav.tree;

/**
 * https://www.hackerrank.com/challenges/ctci-is-binary-search-tree?h_r=next-challenge&h_v=zen
 *
 * @author gkushwaha
 *
 */
public class IsBinarySearchTree {

    public static void main(final String args[]) {
        final TNode<Integer> root = creatBST();
        final IsBinarySearchTree isBinarySearchTree = new IsBinarySearchTree();
        System.out.println(isBinarySearchTree.checkBST(root));
    }

    private static TNode<Integer> creatBST() {
        final BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        final TNode<Integer> root = bst.addNodeToBST(null, new TNode<Integer>(11, null, null));
        bst.addNodeToBST(root, new TNode<Integer>(4, null, null));
        bst.addNodeToBST(root, new TNode<Integer>(8, null, null));
        bst.addNodeToBST(root, new TNode<Integer>(17, null, null));
        bst.addNodeToBST(root, new TNode<Integer>(43, null, null));
        bst.addNodeToBST(root, new TNode<Integer>(19, null, null));
        bst.addNodeToBST(root, new TNode<Integer>(6, null, null));
        bst.addNodeToBST(root, 20);
        bst.addNodeToBST(root, 22);
        bst.addNodeToBST(root, 3);
        bst.addNodeToBST(root, 7);
        bst.addNodeToBST(root, 100);
        bst.addNodeToBST(root, 50);
        return root;
    }

    boolean checkBST(final TNode<Integer> root) {
        return checkBST(root, 0, 10000);
    }

    boolean checkBST(final TNode<Integer> root, final int minValue, final int maxValue) {
        if (root == null) {
            return true;
        }

        if (root.value() < minValue || root.value() > maxValue) {
            return false;
        }

        return checkBST(root.left(), minValue, root.value() - 1) && checkBST(root.right(), root.value() + 1, maxValue);
    }

}
