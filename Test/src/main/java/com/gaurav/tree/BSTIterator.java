package com.gaurav.tree;

import java.util.Stack;

/**
 * BST Iterator , works in O(1), with space complexity of O(h). h being the height
 *
 * returns elements in sorted order
 *
 * @author gauravSingh
 * @param <T>
 *
 */
public class BSTIterator<T> implements Iterator<TNode<T>> {

    private final Stack<TNode<T>> stack;

    public static void main(final String args[]) {
        final TNode<Integer> root = creatBST();
        root.printTree();
        final BSTIterator<Integer> iterator = new BSTIterator<Integer>(root);
        while (iterator.hasNext()) {
            System.out.println(iterator.next().value());
        }
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

    public BSTIterator(final TNode<T> root) {
        stack = new Stack<TNode<T>>();
        stack.setSize(new TreeHeight<T>().getHeight(root));
        populateStack(root);
    }

    private void populateStack(TNode<T> root) {
        while (root != null) {
            stack.push(root);
            root = root.left();
        }
    }

    @Override
    public boolean hasNext() {
        return stack.peek() != null;
    }

    @Override
    public TNode<T> next() {
        final TNode<T> element = stack.pop();
        if (element.right() != null) {
            populateStack(element.right());
        }
        return element;
    }
}