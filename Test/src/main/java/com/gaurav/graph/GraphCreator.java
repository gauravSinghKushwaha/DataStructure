package com.gaurav.graph;

public class GraphCreator {

    public static void main(final String args[]) {
        final GraphCreator graphTraversor = new GraphCreator();
        graphTraversor.createGraph();
        graphTraversor.printGraph();
    }

    private final Node<Integer> node1 = new Node<Integer>(1, false);
    private final Node<Integer> node10 = new Node<Integer>(10, false);
    private final Node<Integer> node11 = new Node<Integer>(11, false);
    private final Node<Integer> node12 = new Node<Integer>(12, false);
    private final Node<Integer> node2 = new Node<Integer>(2, false);
    private final Node<Integer> node3 = new Node<Integer>(3, false);
    private final Node<Integer> node4 = new Node<Integer>(4, false);
    private final Node<Integer> node5 = new Node<Integer>(5, false);
    private final Node<Integer> node6 = new Node<Integer>(6, false);
    private final Node<Integer> node7 = new Node<Integer>(7, false);
    private final Node<Integer> node8 = new Node<Integer>(8, false);
    private final Node<Integer> node9 = new Node<Integer>(9, false);

    public Node<Integer> createGraph() {
        node1.addEdge(node2);
        node1.addEdge(node4);
        node1.addEdge(node5);

        node2.addEdge(node1);
        node2.addEdge(node3);

        node3.addEdge(node1);

        node4.addEdge(node1);
        node4.addEdge(node7);

        node5.addEdge(node1);
        node5.addEdge(node6);
        node5.addEdge(node8);
        node5.addEdge(node9);

        node6.addEdge(node5);
        node6.addEdge(node10);

        node7.addEdge(node4);
        node7.addEdge(node8);
        node7.addEdge(node11);
        node7.addEdge(node12);

        node8.addEdge(node5);
        node8.addEdge(node7);

        node9.addEdge(node5);

        node10.addEdge(node6);

        node11.addEdge(node7);

        node12.addEdge(node7);

        return node1;
    }

    public void printGraph() {
        printNode(node1);
        printNode(node2);
        printNode(node3);
        printNode(node4);
        printNode(node5);
        printNode(node6);
        printNode(node7);
        printNode(node8);
        printNode(node9);
        printNode(node10);
        printNode(node11);
        printNode(node12);
    }

    public void printNode(final Node<Integer> node) {
        System.out.println(node);
        node.getVertices().forEach(e -> System.out.println(e));
    }

}
