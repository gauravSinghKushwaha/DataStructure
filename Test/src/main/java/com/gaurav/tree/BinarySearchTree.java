package com.gaurav.tree;

/**
 * Creates binary search tree
 *
 * Adding a node to BST log(N) for one node N logN for N nodes
 *
 * searching a node in BST in Log(N)
 *
 * @author gauravSingh
 *
 * @param <T>
 */
public final class BinarySearchTree<T extends Comparable<T>> {
    private static final BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

    public TNode<T> addNodeToBST(final TNode<T> root, final T element) {
        return addNodeToBST(root, new TNode<T>(element, null, null));
    }

    public TNode<T> addNodeToBST(final TNode<T> root, final TNode<T> element) {
        // first node THE root
        if (root == null) {
            return element;
        }
        final TNode<T> head = findPositionForNewNode(root, element);
        if (head == null) {
            return null;
        }
        if (head.value().compareTo(element.value()) < 0) {
            head.setRight(element);
        } else {
            head.setLeft(element);
        }
        return root;
    }

    private TNode<T> findPositionForNewNode(final TNode<T> root, final TNode<T> element) {
        // if (isLeaf(root)) { // A leaf
        // return root;
        // }

        if (element.value().compareTo(root.value()) < 0) {
            // if root has a child, search through, else return root
            if (root.left() != null) {
                return findPositionForNewNode(root.left(), element);
            }
            return root;
        } else if (element.value().compareTo(root.value()) > 0) {
            if (root.right() != null) {
                return findPositionForNewNode(root.right(), element);
            }
            return root;
        } else {
            throw new IllegalArgumentException(element.value() + " already exists");
        }
    }

    public TNode<T> searchBST(final TNode<T> root, final TNode<T> element) {
        if (root == null) {
            return null;
        }
        if (element.value().compareTo(root.value()) < 0) {
            return searchBST(root.left(), element);
        } else if (element.value().compareTo(root.value()) > 0) {
            return searchBST(root.right(), element);
        } else {
            return root;
        }
    }

    public static void main(final String args[]) {
        final TNode<Integer> root = createExampleBST();
        root.printTree();
        System.out.println(bst.searchBST(root, 22));
        System.out.println(bst.searchBST(root, 7));
        System.out.println(bst.searchBST(root, 3));
        System.out.println(bst.searchBST(root, 2));
        System.out.println(bst.searchBST(root, 43));
        System.out.println(bst.searchBST(root, 17));
        System.out.println(bst.searchBST(root, 101));
    }

    public TNode<T> searchBST(final TNode<T> root, final T element) {
        return searchBST(root, new TNode<T>(element, null, null));
    }

    private static TNode<Integer> createExampleBST() {
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
}
