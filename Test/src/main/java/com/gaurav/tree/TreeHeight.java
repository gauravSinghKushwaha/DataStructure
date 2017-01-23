package com.gaurav.tree;

public class TreeHeight<T> {
	public int getHeight(final TNode<T> root) {
		if (root == null) {
			return 0;
		}
		return Math.max(getHeight(root.left()), getHeight(root.right())) + 1;
	}

	public static void main(final String arsg[]) {
		System.out.println("height ="
				+ new TreeHeight<Integer>().getHeight(BinaryTreeCreator
						.createBinaryTree()));
	}
}
