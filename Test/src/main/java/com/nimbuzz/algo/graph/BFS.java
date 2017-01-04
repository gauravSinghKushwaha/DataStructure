package com.nimbuzz.algo.graph;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BFS {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(final String args[]) {
        final GraphCreator graphCreator = new GraphCreator();
        final Node<Integer> root = graphCreator.createGraph();
        final BFS bfs = new BFS();
        bfs.traverse(root);
        bfs.print();
    }

    private final Queue<Node<Integer>> queue = new ArrayBlockingQueue<Node<Integer>>(999);

    private void print() {
        System.out.println("size : " + queue.size() + " Operaiotns " + count + " ->> " + queue);
    }

    private void traverse(final Node<Integer> root) {
        count.incrementAndGet();
        if (!root.isVisited()) {
            root.setVisited(true);
            System.out.println(root);
        }
        root.getVertices().forEach(e -> {
            if (!e.isVisited()) {
                queue.offer(e);
            }
        });
        if (queue.size() > 0) {
            traverse(queue.poll());
        }
    }
}
