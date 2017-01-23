package com.gaurav.tree;

import static com.gaurav.tree.BinaryTreeCreator.creatBST;

import java.util.Stack;

/**
 * BST Iterator , works in O(1), with space complexity of O(h). h being the
 * height
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