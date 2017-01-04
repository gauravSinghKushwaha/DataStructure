package com.nimbuzz.algo.tree;


/**
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=700308
 * 
 * 
 * For above tree, the output is
 * 
 * 1 2 7 8 31
 * 
 * 
 * – print rightmost node of 1st level – print leftmost node of 2nd level – print rightmost node of 3rd level – print
 * leftmost node of 4th level – print rightmost node of 5th level
 * 
 * @author gkushwaha
 *
 */
public class ExtremeNodeInAlternateOrder {
    public static void main(final String args[]) {
        final TNode<Integer> root = BinaryTreeCreator.createBinaryTree();
        new ExtremeNodeInAlternateOrder().printInAlternateOrder(root, 0);
    }

    private void printInAlternateOrder(final TNode<Integer> root, int level) {

        if (level == 0) {
            System.out.println(root.value());
        }

        TNode<Integer> node = root;
        // go left
        if (level % 2 != 0) {
            for (int i = level; i > 0; i--) {
                if (node.left() != null) {
                    node = node.left();
                } else {
                    return;
                }
            }
        } else { // go right
            for (int i = level; i > 0; i--) {
                if (node.right() != null) {
                    node = node.right();
                } else {
                    return;
                }
            }
        }
        System.out.println(node.value());
        node = null;
        printInAlternateOrder(root, ++level);
    }
}
