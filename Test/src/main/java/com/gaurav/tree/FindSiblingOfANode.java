package com.gaurav.tree;

/**
 * Find siblings <code>
 * 
           3

        4       5

    7    6  8     11
 * 
 * </code>
 * 
 * if input is 6 return 7
 * 
 * if input is 8 return 11
 * 
 * @author gkushwaha
 *
 */
public class FindSiblingOfANode<T> {

    /**
     * Returns sibling node
     */
    public TNode<T> findSibling(final TNode<T> root, final T element) {
        if (element == null || root == null) {
            return null;
        }
        // Parent node having element as immediate left of right child
        final TNode<T> parentNode = new SearchElementNode<T>().searchRootElement(root, element);

        if (parentNode == null) {
            return null;
        }
        if (parentNode.left().value().equals(element)) {
            return parentNode.right();
        } else {
            return parentNode.left();
        }
    }

    public static void main(final String args[]) {
        final TNode<Integer> root = BinaryTreeCreator.createBinaryTree();
        final FindSiblingOfANode<Integer> obj = new FindSiblingOfANode<Integer>();
        System.out.println("Sibling of 12 =" + obj.findSibling(null, 12));
        System.out.println("Sibling of 12 =" + obj.findSibling(root, 12));
        System.out.println("Sibling of 26 =" + obj.findSibling(root, 26));
        System.out.println("Sibling of 2 =" + obj.findSibling(root, 2));
        System.out.println("Sibling of 1 =" + obj.findSibling(root, 1));
        System.out.println("Sibling of 6 =" + obj.findSibling(root, 6));
        System.out.println("Sibling of 8 =" + obj.findSibling(root, 8));
    }

}
