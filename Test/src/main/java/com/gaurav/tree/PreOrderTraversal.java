package com.gaurav.tree;

/**
 * 
 * ROOT -> LEFT - > RIGHT
 * 
 * 
 * preorder(node)
 * 
 * if (node = null)
 * 
 * return
 * 
 * visit(node)
 * 
 * preorder(node.left)
 * 
 * preorder(node.right)
 * 
 * @author gkushwaha
 *
 */
public class PreOrderTraversal {
    public static void main(final String args[]) {
        final TNode<Integer> root = BinaryTreeCreator.createBinaryTree();
        new PreOrderTraversal().traverse(root);
    }

    public void traverse(final TNode<Integer> node) {
        if (node == null) {
            return;
        }
        visit(node);
        traverse(node.left());
        traverse(node.right());
    }

    private void visit(final TNode<Integer> node) {
        System.out.print(node.value() + " ");
    }
}
