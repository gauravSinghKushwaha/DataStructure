package com.gaurav.tree;

/**
 * http://www.geeksforgeeks.org/construct-tree-inorder-level-order-traversals/
 * 
 * @author gkushwaha
 *
 */
public class TreeFromInOrdrerAndLevelOrder {

    private class Node {
        int data;
        Node left, right;

        Node(final int item) {
            data = item;
            left = right = null;
        }

        public void setLeft(final Node left) {
            this.left = left;
        }

        public void setRight(final Node right) {
            this.right = right;
        }
    }

    Node root;

    Node buildTree(final int in[], final int level[]) {
        final Node startnode = null;
        return constructTree(startnode, level, in, 0, in.length - 1);
    }

    Node constructTree(Node startNode, final int[] levelOrder, final int[] inOrder, final int inStart, final int inEnd) {

        // if start index is more than end index
        if (inStart > inEnd) {
            return null;
        }

        if (inStart == inEnd) {
            return new Node(inOrder[inStart]);
        }

        boolean found = false;
        int index = 0;

        // it represents the index in inOrder array of element that
        // appear first in levelOrder array.
        for (int i = 0; i < levelOrder.length - 1; i++) {
            final int data = levelOrder[i];
            for (int j = inStart; j < inEnd; j++) {
                if (data == inOrder[j]) {
                    startNode = new Node(data);
                    index = j;
                    found = true;
                    break;
                }
            }
            if (found == true) {
                break;
            }
        }

        // elements present before index are part of left child of startNode.
        // elements present after index are part of right child of startNode.
        startNode.setLeft(constructTree(startNode, levelOrder, inOrder, inStart, index - 1));
        startNode.setRight(constructTree(startNode, levelOrder, inOrder, index + 1, inEnd));

        return startNode;
    }

    /* Utility function to print inorder traversal of binary tree */
    void printInorder(final Node node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    public void printPreOrder(final Node root) {
        if (root != null) {
            System.out.print("  " + root.data);
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }

    public void printPostOrder(final Node root) {
        if (root != null) {
            printPostOrder(root.left);
            printPostOrder(root.right);
            System.out.print("  " + root.data);
        }
    }

    // Driver program to test the above functions
    public static void main(final String args[]) {
        final TreeFromInOrdrerAndLevelOrder tree = new TreeFromInOrdrerAndLevelOrder();
        final int in[] = new int[] { 4, 8, 10, 12, 14, 20, 22 };
        final int level[] = new int[] { 20, 8, 22, 4, 12, 10, 14 };
        final Node node = tree.buildTree(in, level);
        /* Let us test the built tree by printing Inorder traversal */
        System.out.print("Inorder traversal of the constructed tree is ");
        System.out.println("IN-ORDER");
        tree.printInorder(node);
        System.out.println();
        System.out.println("PRE-ORDER");
        tree.printPreOrder(node);
        System.out.println();
        System.out.println("POST-ORDER");
        tree.printPostOrder(node);
    }
}
