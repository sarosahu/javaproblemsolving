package com.ds.doublylinkedlist;

public class ListNode<E> {
    private E data;
    private ListNode<E> prev;
    private ListNode<E> next;

    public ListNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public void setData(E data) {
        this.data = data;
    }

    public E getData() {
        return this.data;
    }

    public void setNext(ListNode<E> node) {
        this.next = node;
    }

    public ListNode<E> getNext() {
        return this.next;
    }

    public void setPrev(ListNode<E> node) {
        this.prev = node;
    }

    public ListNode<E> getPrev() {
        return this.prev;
    }
}
