package com.gaurav.tree;

import static com.gaurav.tree.BinaryTreeCreator.createBinaryTree;
import static java.lang.String.valueOf;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * unlike {@link CommonAncestor} we search only once for node1 and node in
 * binary tree. Reduces time complexity from O(NLogN) to O(N)
 *
 * we get respective paths from root (in linkedList), we compare paths and
 * return nodes where path starts differing
 *
 * e.g. node1 >> path >> A>B>C>D e.g. node2 >> path >> A>B>E>F , the common
 * ancestor should be B
 *
 *
 * @author gauravSingh
 *
 * @param <T>
 */
public class CommonAncestorUsingLinkedList<T> {
    private final SearchBinaryTree<T> search;

    public CommonAncestorUsingLinkedList() {
	this.search = new SearchBinaryTree<T>();
    }

    public static void main(final String args[]) {
	final TNode<Integer> root = createBinaryTree();

	root.printTree();

	final CommonAncestorUsingLinkedList<Integer> obj = new CommonAncestorUsingLinkedList<Integer>();
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
	System.out.println("node1 " + 22 + " node2 " + 23 + " commonAncesstor "
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
	final LinkedList<TNode<T>> pathN1 = new LinkedList<TNode<T>>();
	final LinkedList<TNode<T>> pathN2 = new LinkedList<TNode<T>>();

	search.isPresent(root, node1, pathN1);
	search.isPresent(root, node2, pathN2);

	if (pathN1.size() < 0 || pathN2.size() < 0) {
	    return null;
	}

	// System.out.println("pathN1 " + listToStringPath(pathN1) +
	// "\n pathN2 ="
	// + listToStringPath(pathN2));

	final ListIterator<TNode<T>> listIteratorN1 = pathN1.listIterator();
	final ListIterator<TNode<T>> listIteratorN2 = pathN2.listIterator();
	TNode<T> elemN1 = listIteratorN1.next();
	TNode<T> elemN2 = listIteratorN2.next();
	TNode<T> previous = null;
	while (listIteratorN1.hasNext() && listIteratorN2.hasNext()
		&& elemN1.equals(elemN2)) {
	    previous = elemN1;
	    elemN1 = listIteratorN1.next();
	    elemN2 = listIteratorN2.next();
	}

	return previous;
    }

    @SuppressWarnings("unused")
    private String listToStringPath(final LinkedList<TNode<T>> pathN2) {
	final StringBuilder str = new StringBuilder();
	for (final TNode<T> tNode : pathN2) {
	    str.append(tNode.value()).append(">>");
	}
	return valueOf(str.subSequence(0, str.length() - 2));
    }
}
