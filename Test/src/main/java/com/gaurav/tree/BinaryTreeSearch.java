package com.gaurav.tree;

import static com.gaurav.tree.BinaryTreeCreator.createBinaryTree;

public final class BinaryTreeSearch<T> {

	/**
	 * If element is present then true else false
	 *
	 * @param root
	 * @param element
	 * @return
	 */
	public boolean isPresent(final TNode<T> root, final T element) {
		if (root == null) {
			return false;
		}
		if (element.equals(root.value())) {
			return true;
		}
		return isPresent(root.left(), element)
				|| isPresent(root.right(), element);
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
		final BinaryTreeSearch<Integer> obj = new BinaryTreeSearch<Integer>();
		final TNode<Integer> root = createBinaryTree();
		final int element = 12;
		System.out.println("element " + element + " is Present ="
				+ obj.isPresent(root, element));
		System.out.println("element " + element + " node is "
				+ obj.searchElement(root, element));
		System.out.println(element + "'s root node is"
				+ obj.searchRootElement(root, element));
	}
}
