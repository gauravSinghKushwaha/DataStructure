package com.gaurav.tree;

/**
 * http://algorithms.tutorialhorizon.com/construct-a-binary-tree-from-given-inorder-and-postorder-traversal/
 * 
 * 
 * @author gkushwaha
 *
 */
public class InorderPostOrderToTree {

    public Node makeBTree(final int[] inOrder, final int[] postOrder, final int iStart, final int iEnd,
                          final int postStart, final int postEnd) {
        if (iStart > iEnd) {
            return null;
        }
        // last element in post order is root
        final int rootValue = postOrder[postEnd];
        final Node root = new Node(rootValue);

        if (iStart == iEnd) {
            return root;
        }
        final int index = getInorderIndex(inOrder, iStart, iEnd, root.data);
        root.left = makeBTree(inOrder, postOrder, iStart, index - 1, postStart, postStart + index - (iStart + 1));
        root.right = makeBTree(inOrder, postOrder, index + 1, iEnd, postStart + index - iStart, postEnd - 1);
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
        final int[] inOrder = { 4, 2, 5, 1, 6, 3, 7 };
        final int[] postOrder = { 4, 5, 2, 6, 7, 3, 1 };
        final InorderPostOrderToTree i = new InorderPostOrderToTree();
        final Node x = i.makeBTree(inOrder, postOrder, 0, inOrder.length - 1, 0, postOrder.length - 1);
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
