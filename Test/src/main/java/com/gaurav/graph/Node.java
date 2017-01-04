package com.gaurav.graph;

import java.util.ArrayList;
import java.util.List;

public class Node<E> {
    private final E e;
    private final List<Node<E>> vertices;
    private boolean visited;

    public Node(final E e, final boolean visited) {
        this.e = e;
        this.vertices = new ArrayList<Node<E>>();
        this.visited = visited;
    }

    public void addEdge(final Node<E> node) {
        this.vertices.add(node);
    }

    public E getE() {
        return e;
    }

    public List<Node<E>> getVertices() {
        return vertices;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(final boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Node [e=" + e + ", visited=" + visited + "]";
    }

}
