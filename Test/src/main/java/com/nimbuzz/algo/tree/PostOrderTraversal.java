package com.nimbuzz.algo.tree;

/**
 * 
 * LEFT -> RIGHT -> ROOT
 * 
 * 
 * preorder(node)
 * 
 * if (node = null)
 * 
 * return
 * 
 * preorder(node.left)
 * 
 * preorder(node.right)
 * 
 * visit(node)
 * 
 * @author gkushwaha
 *
 */
public class PostOrderTraversal {
    public static void main(final String args[]) {
        final TNode<Integer> root = BinaryTreeCreator.createBinaryTree();
        new PostOrderTraversal().traverse(root);
    }

    public void traverse(final TNode<Integer> node) {
        if (node == null) {
            return;
        }
        traverse(node.left());
        traverse(node.right());
        visit(node);
    }

    private void visit(final TNode<Integer> node) {
        System.out.print(node.value() + " ");
    }
}
