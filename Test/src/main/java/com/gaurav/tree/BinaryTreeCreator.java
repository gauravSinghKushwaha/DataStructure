package com.gaurav.tree;


/**
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=700308
 *
 *
 * @author gkushwaha
 *
 */
public class BinaryTreeCreator {
	public static TNode<Integer> creatBST(){
		final TNode<Integer> leftLeft = new TNode<Integer>(4,null,null);
		final TNode<Integer> leftRight = new TNode<Integer>(8,null,null);
		final TNode<Integer> rightLeft = new TNode<Integer>(17,null,null);
		final TNode<Integer> rightRight = new TNode<Integer>(43,null,null);
		final TNode<Integer> right = new TNode<Integer>(19,rightLeft,rightRight);
		final TNode<Integer> left = new TNode<Integer>(6,leftLeft,leftRight);
		final TNode<Integer> root = new TNode<Integer>(11,left,right);
		return root;
	}

	public static TNode<Integer> createBinaryTree() {
		final TNode<Integer> node31 = new TNode<Integer>(31, null, null);
		final TNode<Integer> node30 = new TNode<Integer>(30, null, null);
		final TNode<Integer> node29 = new TNode<Integer>(29, null, null);
		final TNode<Integer> node28 = new TNode<Integer>(28, null, null);
		final TNode<Integer> node27 = new TNode<Integer>(27, null, null);
		final TNode<Integer> node26 = new TNode<Integer>(26, null, null);
		final TNode<Integer> node25 = new TNode<Integer>(25, null, null);
		final TNode<Integer> node24 = new TNode<Integer>(24, null, null);
		final TNode<Integer> node23 = new TNode<Integer>(23, null, null);
		final TNode<Integer> node22 = new TNode<Integer>(22, null, null);
		final TNode<Integer> node21 = new TNode<Integer>(21, null, null);
		final TNode<Integer> node20 = new TNode<Integer>(20, null, null);
		final TNode<Integer> node19 = new TNode<Integer>(19, null, null);
		final TNode<Integer> node18 = new TNode<Integer>(18, null, null);
		final TNode<Integer> node17 = new TNode<Integer>(17, null, null);
		final TNode<Integer> node16 = new TNode<Integer>(16, null, null);
		final TNode<Integer> node15 = new TNode<Integer>(15, node30, node31);
		final TNode<Integer> node14 = new TNode<Integer>(14, node28, node29);
		final TNode<Integer> node13 = new TNode<Integer>(13, node26, node27);
		final TNode<Integer> node12 = new TNode<Integer>(12, node24, node25);
		final TNode<Integer> node11 = new TNode<Integer>(11, node22, node23);
		final TNode<Integer> node10 = new TNode<Integer>(10, node20, node21);
		final TNode<Integer> node9 = new TNode<Integer>(9, node18, node19);
		final TNode<Integer> node8 = new TNode<Integer>(8, node16, node17);
		final TNode<Integer> node7 = new TNode<Integer>(7, node14, node15);
		final TNode<Integer> node6 = new TNode<Integer>(6, node12, node13);
		final TNode<Integer> node5 = new TNode<Integer>(5, node10, node11);
		final TNode<Integer> node4 = new TNode<Integer>(4, node8, node9);
		final TNode<Integer> node3 = new TNode<Integer>(3, node6, node7);
		final TNode<Integer> node2 = new TNode<Integer>(2, node4, node5);
		return new TNode<Integer>(1, node2, node3);
	}
}
