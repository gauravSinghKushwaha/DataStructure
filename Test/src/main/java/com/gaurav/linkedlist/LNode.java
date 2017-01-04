package com.gaurav.linkedlist;


public final class LNode<E> {
    private final E value;
    private LNode<E> next;
    private LNode<E> prev;
    private LNode<E> down;

    public E value() {
        return value;
    }

    public LNode<E> next() {
        return next;
    }

    public LNode<E> prev() {
        return prev;
    }

    public LNode<E> down() {
        return down;
    }

    public void setNext(final LNode<E> next) {
        this.next = next;
    }

    public void setPrev(final LNode<E> prev) {
        this.prev = prev;
    }

    public void setDown(final LNode<E> down) {
        this.down = down;
    }

    public LNode(final E value, final LNode<E> next, final LNode<E> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
        this.down = null;
    }

    public boolean isHead() {
        return prev() == null;
    }

    public boolean isTail() {
        return next() == null;
    }

    /**
     * Add a node next to this node
     * 
     * @param node
     * @return
     */
    public LNode<E> addNode(final LNode<E> node) {
        node.prev = this;
        this.next = node;
        return this.next;
    }

    /**
     * Add a node next to this node
     * 
     * @param node
     * @return
     */
    public LNode<E> addToTail(final LNode<E> node) {
        LNode<E> tail = this;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
        return tail.addNode(node);
    }

    public LNode<E> remove(final LNode<E> node) {
        if (!node.isTail()) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        } else {
            node.prev.next = null;
        }
        return node.prev;
    }

    @Override
    public String toString() {
        return "Node [value=" + value + ", next=" + next + "]";
    }

}
