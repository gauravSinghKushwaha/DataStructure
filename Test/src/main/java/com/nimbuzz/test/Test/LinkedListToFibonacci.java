package com.nimbuzz.test.Test;

/**
 * @author gkushwaha
 * 
 */
public class LinkedListToFibonacci {
    Node<Integer> head;
    Node<Integer> fib;
    Node<Integer> rest;

    public Node<Integer> getHead() {
        return head;
    }

    public Node<Integer> getFib() {
        return fib;
    }

    public Node<Integer> getRest() {
        return rest;
    }

    public void setHead(final Node<Integer> head) {
        this.head = head;
    }

    public void setFib(final Node<Integer> fib) {
        this.fib = fib;
    }

    public void setRest(final Node<Integer> rest) {
        this.rest = rest;
    }

    public static void main(final String args[]) {
        final LinkedListToFibonacci test = new LinkedListToFibonacci();
        test.traverseAndGetFibElements(test.input());
    }

    /**
     * Method traverse the Linked list and create two list fib and rest having fibonacci elements and rest of the list
     * respectively.
     * 
     * It run time complexity is O(n)
     * 
     * @param node
     * 
     * @param test
     */
    private void traverseAndGetFibElements(final Node<Integer> node) {
        Node<Integer> tempNode = node;
        while (tempNode != null) {
            final Node<Integer> immediatePrevNode = tempNode.getPrevNode();
            if (immediatePrevNode != null && immediatePrevNode.getPrevNode() != null) {// starts with 3
                if (tempNode.getValue() != immediatePrevNode.getValue() + immediatePrevNode.getPrevNode().getValue()) {
                    // Creating a new list for rest of the element
                    addToRest(tempNode);
                    if (null != tempNode.getNextNode()) {// deleting node
                        immediatePrevNode.setNextNode(tempNode.getNextNode());
                        tempNode.nextNode.setPrevNode(immediatePrevNode);
                    }
                } else {
                    addToFib(tempNode);
                }
            } else {// first two elements
                addToFib(tempNode);
            }
            tempNode = tempNode.getNextNode();
        }
        // priting out put here
        System.out.println("Input List ");
        printOnCosole(getHead());
        System.out.println("FIB List ");
        printOnCosole(getFib());
        System.out.println("Rest of the elements from List ");
        printOnCosole(getRest());
    }

    private void addToRest(final Node<Integer> node) {
        if (getRest() == null) {
            setRest(new Node<Integer>(node.getValue()));
        } else {
            getRest().add(node.getValue());
        }
    }

    private void addToFib(final Node<Integer> node) {
        if (getFib() == null) {
            setFib(new Node<Integer>(node.getValue()));
        } else {
            getFib().add(node.getValue());
        }
    }

    /**
     * @param head
     * @return
     */
    private void printOnCosole(final Node<Integer> head) {
        Node<Integer> tempNode = head;
        while (tempNode != null) {
            System.out.println(tempNode.getValue() + " Next "
                    + (tempNode.getNextNode() != null ? tempNode.getNextNode().getValue() : "N/A") + " Prev "
                    + (tempNode.getPrevNode() != null ? tempNode.getPrevNode().getValue() : "N/A"));
            tempNode = tempNode.getNextNode();
        }
    }

    /**
     * @return
     * @return
     */
    private Node<Integer> input() {
        head = new Node<Integer>(1);
        head.add(2);
        head.add(3);
        head.add(4);
        head.add(5);
        head.add(6);
        head.add(7);
        head.add(8);
        head.add(9);
        setHead(head);
        return head;
    }
}

class Node<T> {
    T value;
    Node<T> nextNode;
    Node<T> prevNode;
    Node<T> lastNode;
    transient int length = 0;

    public T getValue() {
        return this.value;
    }

    public void setValue(final T value) {
        this.value = value;
    }

    public Node<T> getNextNode() {
        return this.nextNode;
    }

    public void setNextNode(final Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public Node<T> getLastNode() {
        return this.lastNode;
    }

    public void setLastNode(final Node<T> lastNode) {
        this.lastNode = lastNode;
    }

    public void setPrevNode(final Node<T> prevNode) {
        this.prevNode = prevNode;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    public Node(final T value) {
        super();
        this.value = value;
        length = length + 1;
    }

    public Node<T> add(final T newNodeValue) {
        final Node<T> newNode = new Node<T>(newNodeValue);
        if (this.nextNode == null) {
            this.nextNode = newNode;
            newNode.prevNode = this;
        }

        if (this.lastNode != null) {
            this.lastNode.nextNode = newNode;
            newNode.prevNode = this.lastNode;
        }
        this.lastNode = newNode;
        length = length + 1;
        return this;
    }

    public Node<T> getPrevNode() {
        return this.prevNode;
    }

    public int getLength() {
        return this.length;
    }

}
