package com.gaurav.tree;

import static com.gaurav.tree.BinaryTreeCreator.createBinaryTree;

public class CommonAncesstor<T> {
	private final BinaryTreeSearch<T> search;

	public CommonAncesstor() {
		this.search = new BinaryTreeSearch<T>();
	}

	public static void main(final String args[]) {
		final TNode<Integer> root = createBinaryTree();

		root.printTree();

		final CommonAncesstor<Integer> obj = new CommonAncesstor<Integer>();
		System.out.println("node1 " + 9 + " node2 " + 19 + " commonAncesstor "
				+ obj.findCommonAncesstor(root, 9, 19).value());
		System.out.println("node1 " + 9 + " node2 " + 11 + " commonAncesstor "
				+ obj.findCommonAncesstor(root, 9, 11).value());
		System.out.println("node1 " + 22 + " node2 " + 25 + " commonAncesstor "
				+ obj.findCommonAncesstor(root, 22, 25).value());
		System.out.println("node1 " + 24 + " node2 " + 31 + " commonAncesstor "
				+ obj.findCommonAncesstor(root, 24, 31).value());
		System.out.println("node1 " + 16 + " node2 " + 31 + " commonAncesstor "
				+ obj.findCommonAncesstor(root, 16, 31).value());
		System.out.println("node1 " + 8 + " node2 " + 11 + " commonAncesstor "
				+ obj.findCommonAncesstor(root, 8, 11).value());
	}

	public final TNode<T> findCommonAncesstor(final TNode<T> root,
			final TNode<T> node1, final TNode<T> node2) {
		return findCommonAncesstor(root, node1.value(), node2.value());
	}

	public final TNode<T> findCommonAncesstor(final TNode<T> root,
			final T node1, final T node2) {
		// if tree null or any of the node is root, return root itself
		if (root == null || node1 == null || node2 == null) {
			return null;
		}
		if (root.value().equals(node1) || root.value().equals(node2)) {
			return root;
		}
		final boolean n1OnLeft = search.isPresent(root.left(), node1);
		final boolean n2OnLeft = search.isPresent(root.left(), node2);

		// System.out.println("n1OnLeft " + n1OnLeft + " n2OnLeft " + n2OnLeft);
		if (n1OnLeft != n2OnLeft) { // We have found out ancesstor
			return root;
		} else {
			return findCommonAncesstor(n1OnLeft ? root.left() : root.right(),
					node1, node2);
		}
	}
}