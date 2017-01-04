package com.gaurav.graph;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class DFS {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(final String args[]) {
        final GraphCreator graphCreator = new GraphCreator();
        final Node<Integer> root = graphCreator.createGraph();
        final DFS dfs = new DFS();
        dfs.traverse(root);
        dfs.print();
    }

    private final Stack<Node<Integer>> stack = new Stack<Node<Integer>>();

    private void print() {
        System.out.println("size : " + stack.size() + " Operaiotns " + count + " ->> " + stack);
    }

    private void traverse(final Node<Integer> root) {
        count.incrementAndGet();
        if (!root.isVisited()) {
            root.setVisited(true);
            System.out.println(root);
        }
        root.getVertices().forEach(e -> {
            if (!e.isVisited()) {
                stack.push(e);
            }
        });
        if (stack.size() > 0) {
            traverse(stack.pop());
        }
    }
}
