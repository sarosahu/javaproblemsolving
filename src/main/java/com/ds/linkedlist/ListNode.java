package com.ds.linkedlist;

public class ListNode<E> {
    // Package access members;
    // List can access these directly
    public E data;
    public ListNode<E> next; // Reference to the next node in the list

    // Default Ctor
    public ListNode() {}

    // Ctor -- creates a ListNode that refers to object
    public ListNode(E data) {
        this(data, null);
    }

    // Ctor -- creates ListNode that refers to the specified
    // object to the current node and reference to the next ListNode
    public ListNode(E data, ListNode<E> nextNode) {
        this.data = data;
        this.setNext(nextNode);
    }

    public void setNext(ListNode<E> node) {
        this.next = node;
    }
    // Return the reference to the data in node
    public E getData() {
        return data;
    }

    // Return reference to the next node in list
    public ListNode<E> getNext() {
        return next;
    }
}
