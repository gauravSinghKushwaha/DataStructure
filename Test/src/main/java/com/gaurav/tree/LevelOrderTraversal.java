package com.gaurav.tree;

import static com.gaurav.tree.BinaryTreeCreator.createBinaryTree;
import static java.lang.System.out;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Level order traveral...should print 1 , 2 ,3 ,4 ,5 ,6.....
 * 
 * @author gkushwaha
 *
 */
public class LevelOrderTraversal {
    private static final Queue<TNode<Integer>> q = new ArrayBlockingQueue<TNode<Integer>>(99);
    public static void main(final String args[]) {
        final TNode<Integer> root = createBinaryTree();
        traverse(root);
    }

    public static void traverse(final TNode<Integer> node) {
        q.offer(node);
        while (!q.isEmpty()) {
            final TNode<Integer> top = q.poll();
            visit(top);
            if(top.left()!=null) {
                q.offer(top.left());
            }
            if(top.right()!=null) {
                q.offer(top.right());
            }
        }
    }

    private static void visit(final TNode<Integer> node) {
        out.println(node.value());
    }
}
