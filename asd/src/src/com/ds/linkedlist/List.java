package com.ds.linkedlist;

import java.util.NoSuchElementException;

public class List<E> {
    private ListNode<E> firstNode;
    private ListNode<E> lastNode;
    private String name;
    private int size;

    // Ctor -- creates empty list with "list" as the name
    public List() {
        this("list");
    }
    public List(String listName) {
        this.name = listName;
        this.firstNode = this.lastNode = null;
    }

    public int getSize() {
        return this.size;
    }
    // insert item at front of List
    public void insertAtFront(E insertItem) {
        if (isEmpty()) {
            this.firstNode = this.lastNode = new ListNode<E>(insertItem);
        } else {
            this.firstNode = new ListNode<>(insertItem, this.firstNode);
        }
        ++this.size;
    }

    public ListNode<E> getFirstNode() {
        return this.firstNode;
    }

    public ListNode<E> getLastNode() {
        return this.lastNode;
    }

    public void insertAtBack(E insertItem) {
        if (isEmpty()) {
            this.firstNode = this.lastNode = new ListNode<>(insertItem);
        } else {
            //this.lastNode.next = new ListNode<>(insertItem);
            //this.lastNode = this.lastNode.next;
            /**
             * Date: 15th Feb, 2022.
             * Commented above 2 lines, added below logic.
             */
            ListNode<E> node = new ListNode<>(insertItem);
            this.lastNode.setNext(node);
            this.lastNode = this.lastNode.getNext();
        }
        ++this.size;
    }

    // Remove first node from list
    public E removeFromFront() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(name + " is empty!");
        }

        E removedItem = firstNode.data;

        // Update references firstNode and lastNode
        if (firstNode == lastNode) {
            firstNode = lastNode = null;
        } else {
            //ListNode<E> prev = firstNode;
            //firstNode = firstNode.next;
            //prev = null;
            /**
             * Date 15th Feb, 2022
             * Commented above 3 lines, added below logic
             */
            this.firstNode = this.firstNode.getNext();
        }
        --this.size;
        return removedItem;
    }

    // Remove last node from List
    public E removeFromBack() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException(name + " is empty!");
        }

        E removedItem = lastNode.data;

        // update references firstNode and lastNode
        if (firstNode == lastNode) {
            firstNode = lastNode = null;
        } else {
            ListNode<E> current = firstNode;
            while (current.next != lastNode) {
                current = current.next;
            }
            lastNode = current;
            //current.next = null;
            lastNode.setNext(null);
        }
        --this.size;
        return removedItem;
    }

    // Determine if list is empty; return true if so
    public boolean isEmpty() {
        return firstNode == null;
    }

    // output list contents
    public void print() {
        if (isEmpty()) {
            System.out.printf("Empty %s%n", name);
        }

        System.out.printf("The %s is: ", name);
        ListNode<E> current = firstNode;
        while (current != null) {
            System.out.printf("%s ", current.data);
            current = current.next;
        }
        System.out.println();
    }

    public void setFirstNode(ListNode<E> node) {
        this.firstNode = node;
    }

    public void setLastNode(ListNode<E> node) {
        this.lastNode = node;
    }

    public static void main(String[] args) {
        List<Integer> list = new List<>();

        // insert integers in list
        list.insertAtFront(-1);
        list.print();
        list.insertAtFront(0);
        list.print();
        list.insertAtBack(1);
        list.print();
        list.insertAtBack(5);
        list.print();

        // Remove objects from list; print after each removal
        try {
            int removedItem = list.removeFromFront();
            System.out.printf("%n%d removed%n", removedItem);
            list.print();

            removedItem = list.removeFromFront();
            System.out.printf("%n%d removed%n", removedItem);
            list.print();

            removedItem = list.removeFromBack();
            System.out.printf("%n%d removed%n", removedItem);
            list.print();

            removedItem = list.removeFromBack();
            System.out.printf("%n%d removed%n", removedItem);
            list.print();
        }
        catch (NoSuchElementException noSuchElementException) {
            noSuchElementException.printStackTrace();
        }
    }
}
