package com.gaurav.tree;

import static com.gaurav.tree.BinaryTreeCreator.createBinaryTree;

import java.util.LinkedList;

public final class SearchBinaryTree<T> {

	/**
	 * If element is present then true else false
	 *
	 * @param root
	 * @param element
	 * @return
	 */
	public boolean isPresent(final TNode<T> root, final T element) {
		return isPresent(root, element, null);
	}

	/**
	 * If element is present then true else false
	 *
	 * list passed in as path would have path from root to element
	 *
	 * @param root
	 * @param element
	 * @return
	 */
	public boolean isPresent(final TNode<T> root, final T element,
			final LinkedList<TNode<T>> path) {
		if (root == null) {
			return false;
		}
		if (element.equals(root.value())) {
			if (path != null) {
				path.addFirst(root);
			}
			return true;
		}
		final boolean isPresentInLeft = isPresent(root.left(), element, path);
		if (isPresentInLeft && path != null) {
			path.addFirst(root);
		}
		final boolean isPresentInRight = isPresent(root.right(), element, path);
		if (isPresentInRight && path != null) {
			path.addFirst(root);
		}
		return isPresentInLeft || isPresentInRight;
	}

	/**
	 * Return the node having T value
	 *
	 * @param root
	 * @param element
	 * @return
	 */
	public TNode<T> searchElement(final TNode<T> root, final T element) {
		if (root == null) {
			return null;
		}
		if (element.equals(root.value())) {
			return root;
		}
		// search left
		final TNode<T> left = searchElement(root.left(), element);
		// search left
		final TNode<T> right = searchElement(root.right(), element);
		return left != null ? left : right;
	}

	/**
	 * Return the node having T value
	 *
	 * @param root
	 * @param element
	 * @return
	 */
	public TNode<T> searchNodeElement(final TNode<T> root,
			final TNode<T> element) {
		if (root == null || element == null) {
			return null;
		}
		return searchElement(root, element.value());
	}

	public TNode<T> searchRootElement(final TNode<T> root, final T element) {
		if (root == null) {
			return null;
		}
		if (root.left() != null && element.equals(root.left().value())
				|| root.right() != null && element.equals(root.right().value())) {
			return root;
		}
		// search left
		final TNode<T> left = searchRootElement(root.left(), element);
		// search left
		final TNode<T> right = searchRootElement(root.right(), element);
		return left != null ? left : right;
	}

	public static void main(final String arsg[]) {
		final SearchBinaryTree<Integer> obj = new SearchBinaryTree<Integer>();
		final TNode<Integer> root = createBinaryTree();
		root.printTree();
		final int element = 12;
		LinkedList<TNode<Integer>> path = new LinkedList<TNode<Integer>>();
		System.out.println("element " + element + " is Present ="
				+ obj.isPresent(root, element, path) + " path = " + path);
		path = new LinkedList<TNode<Integer>>();
		System.out.println("element " + 50 + " is Present ="
				+ obj.isPresent(root, 50, path) + " path = " + path);
		System.out.println("element " + element + " is Present ="
				+ obj.isPresent(root, element));
		System.out.println("element " + element + " node is "
				+ obj.searchElement(root, element));
		System.out.println(element + "'s root node is"
				+ obj.searchRootElement(root, element));
	}
}
