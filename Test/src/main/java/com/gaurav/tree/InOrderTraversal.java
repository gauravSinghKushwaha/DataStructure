package com.gaurav.tree;

/**
 * 
 * LEFT -> ROOT -> RIGHT
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
 * visit(node)
 * 
 * preorder(node.right)
 * 
 * @author gkushwaha
 *
 */
public class InOrderTraversal {
    public static void main(final String args[]) {
        final TNode<Integer> root = BinaryTreeCreator.createBinaryTree();
        new InOrderTraversal().traverse(root);
    }

    public void traverse(final TNode<Integer> node) {
        if (node == null) {
            return;
        }
        traverse(node.left());
        visit(node);
        traverse(node.right());
    }

    private void visit(final TNode<Integer> node) {
        System.out.print(node.value() + " ");
    }
}
