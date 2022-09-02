package com.ds.queue;

import com.ds.linkedlist.List;

import java.util.NoSuchElementException;

public class Queue<E> {
    private List<E> queueList;

    // Ctor
    public Queue() {
        queueList = new List<E>("queue");
    }

    // Size
    public int size() {
        return this.queueList.getSize();
    }

    // Add object to queue
    public void enqueue(E object) {
        queueList.insertAtBack(object);
    }

    // remove object from queue
    public E dequeue() throws NoSuchElementException {
        return queueList.removeFromFront();
    }

    public E front() throws NoSuchElementException {
        return queueList.getFirstNode().getData();
    }

    // Determine if queue is empty
    public boolean isEmpty() {
        return queueList.isEmpty();
    }

    // output queue contents
    public void print() {
        queueList.print();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();

        // Use enqueue method
        queue.enqueue(-1);
        queue.print();

        queue.enqueue(0);
        queue.print();

        queue.enqueue(1);
        queue.print();

        queue.enqueue(5);
        queue.print();

        // Remove objects from queue
        boolean continueLoop = true;

        while (continueLoop) {
            try {
                int removedItem = queue.dequeue();
                System.out.printf("%n%d dequeued%n", removedItem);
                queue.print();
            }
            catch (NoSuchElementException noSuchElementException) {
                continueLoop = false;
                noSuchElementException.printStackTrace();
            }
        }
    }
}
