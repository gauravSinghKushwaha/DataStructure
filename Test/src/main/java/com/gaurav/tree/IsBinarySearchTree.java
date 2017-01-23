package com.gaurav.tree;

/**
 * https://www.hackerrank.com/challenges/ctci-is-binary-search-tree?h_r=next-challenge&h_v=zen
 *
 * @author gkushwaha
 *
 */
public class IsBinarySearchTree {

	public static void main(final String args[]) {
		final TNode<Integer> root = BinaryTreeCreator.creatBST();
		final IsBinarySearchTree isBinarySearchTree = new IsBinarySearchTree();
		System.out.println(isBinarySearchTree.checkBST(root));
	}

	boolean checkBST(final TNode<Integer> root) {
		return checkBST(root, 0, 10000);
	}

	boolean checkBST(final TNode<Integer> root, final int minValue,
			final int maxValue) {
		if (root == null) {
			return true;
		}

		if (root.value() < minValue || root.value() > maxValue) {
			return false;
		}

		return checkBST(root.left(), minValue, root.value() - 1)
				&& checkBST(root.right(), root.value() + 1, maxValue);
	}

}
