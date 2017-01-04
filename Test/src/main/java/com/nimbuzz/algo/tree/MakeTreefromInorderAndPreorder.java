package com.nimbuzz.algo.tree;


/**
 * http://algorithms.tutorialhorizon.com/make-a-binary-tree-from-given-inorder-and-preorder-traveral/
 * 
 * http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 * 
 * 
 * @author gkushwaha
 *
 */
public class MakeTreefromInorderAndPreorder {
    public static int preOrderIndex = 0;

    public Node makeBTree(final int[] inOrder, final int[] preOrder, final int iStart, final int iEnd) {
        if (iStart > iEnd) {
            return null;
        }
        // picking up next node from pre-order...
        final Node root = new Node(preOrder[preOrderIndex]);
        preOrderIndex++;

        if (iStart == iEnd) {
            return root;
        }
        final int index = getInorderIndex(inOrder, iStart, iEnd, root.data);
        root.left = makeBTree(inOrder, preOrder, iStart, index - 1);
        root.right = makeBTree(inOrder, preOrder, index + 1, iEnd);
        return root;
    }

    public int getInorderIndex(final int[] inOrder, final int start, final int end, final int data) {
        for (int i = start; i <= end; i++) {
            if (inOrder[i] == data) {
                return i;
            }
        }
        return -1;
    }

    public void printINORDER(final Node root) {
        if (root != null) {
            printINORDER(root.left);
            System.out.print("  " + root.data);
            printINORDER(root.right);
        }
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

    public static void main(final String[] args) throws java.lang.Exception {
        final int[] inOrder = { 2, 5, 6, 10, 12, 14, 15 };
        final int[] preOrder = { 10, 5, 2, 6, 14, 12, 15 };
        final MakeTreefromInorderAndPreorder i = new MakeTreefromInorderAndPreorder();
        final Node x = i.makeBTree(inOrder, preOrder, 0, inOrder.length - 1);
        System.out.println("Constructed Tree : ");
        System.out.println("IN-ORDER");
        i.printINORDER(x);
        System.out.println();
        System.out.println("PRE-ORDER");
        i.printPreOrder(x);
        System.out.println();
        System.out.println("POST-ORDER");
        i.printPostOrder(x);
    }

    class Node {
        int data;
        Node left;
        Node right;

        public Node(final int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}

