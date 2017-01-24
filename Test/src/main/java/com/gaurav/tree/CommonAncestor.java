package com.gaurav.tree;

import static com.gaurav.tree.BinaryTreeCreator.createBinaryTree;
import static java.lang.System.out;

/**
 * Finds common ancesstor for node n1 and n2 recursively in O(Nlogn)
 *
 * @author gauravSingh
 *
 * @param <T>
 */
public class CommonAncestor<T> {
    private final SearchBinaryTree<T> search;

    public CommonAncestor() {
	this.search = new SearchBinaryTree<T>();
    }

    public static void main(final String args[]) {
	final TNode<Integer> root = createBinaryTree();

	root.printTree();

	final CommonAncestor<Integer> obj = new CommonAncestor<Integer>();
	out.println("node1 " + 9 + " node2 " + 19 + " commonAncesstor "
		+ obj.findCommonAncesstor(root, 9, 19).value());
	out.println("node1 " + 9 + " node2 " + 11 + " commonAncesstor "
		+ obj.findCommonAncesstor(root, 9, 11).value());
	out.println("node1 " + 22 + " node2 " + 25 + " commonAncesstor "
		+ obj.findCommonAncesstor(root, 22, 25).value());
	out.println("node1 " + 24 + " node2 " + 31 + " commonAncesstor "
		+ obj.findCommonAncesstor(root, 24, 31).value());
	out.println("node1 " + 16 + " node2 " + 31 + " commonAncesstor "
		+ obj.findCommonAncesstor(root, 16, 31).value());
	out.println("node1 " + 8 + " node2 " + 11 + " commonAncesstor "
		+ obj.findCommonAncesstor(root, 8, 11).value());
	out.println("node1 " + 22 + " node2 " + 23 + " commonAncesstor "
		+ obj.findCommonAncesstor(root, 22, 23).value());
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
	if (root.left().value().equals(node1)
		|| root.left().value().equals(node2)
		|| root.right().value().equals(node1)
		|| root.right().value().equals(node2)) {
	    return root;
	}
	final boolean n1OnLeft = search.isPresent(root.left(), node1);
	final boolean n2OnLeft = search.isPresent(root.left(), node2);

	// out.println("root =" + root + "n1OnLeft =" + n1OnLeft + " n2OnLeft ="
	// + n2OnLeft);
	if (n1OnLeft != n2OnLeft) { // We have found out ancesstor
	    return root;
	} else {
	    return findCommonAncesstor(n1OnLeft ? root.left() : root.right(),
		    node1, node2);
	}
    }
}