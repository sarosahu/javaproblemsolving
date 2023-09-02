package com.algo.ae.linkedlist;

/**
 * Shift Linked List
 *
 * Write a function that takes in the head of a Singly Linked List and an integer k,
 * shifts the list in place (i.e. don't create a brand new list) by k positions, and
 * returns its new head.
 *
 * Shifting a Linked List means moving its nodes forward or backward and wrapping
 * them aournd the list where appropriate. For example, shifting a Linked List
 * forward by one position would make its tail become the new head of the linked list.
 *
 * Whether nodes are moved forward or backward is determined by whether k is positive
 * or negative.
 *
 * Each LinkedList node has an integer value as well as a next node pointing to
 * the next node in the list or to None/null if it's the tail of the list.
 *
 * You can asssume that the input Linked List will always have at least one node;
 * in other words, the head will never be None/null.
 *
 * Sample Input:
 * head = 0 --> 1 --> 2 --> 3 --> 4 --> 5
 * k = 2
 *
 * Sample Output:
 * 4 --> 5 --> 0 --> 1 --> 2 --> 3 // the new head node with value 4
 */
public class ShiftLinkedList {
    public static LinkedList shiftLinkedList(LinkedList head, int k) {
        // Write your code here.
        if (k == 0) {
            return head;
        }
        // We always have head node exist i.e. head is always not null.
        int size = 1;
        LinkedList listIter = head;
        while (listIter.next != null) {
            ++size;
            listIter = listIter.next;
        }

        int offset = Math.abs(k) % size;
        if (offset == 0) {
            return head;
        }
        int newTailPosition = k > 0 ? size - offset : offset;

        LinkedList newTail = head;
        for (int i = 1; i < newTailPosition; ++i) {
            newTail = newTail.next;
        }

        LinkedList newHead = newTail.next;
        newTail.next = null;
        listIter.next = head;

        return newHead;

    }

    static LinkedList shiftListForward(LinkedList head, int k) {
        LinkedList leader = head;
        for (int i = 0; i < k; ++i) {
            leader = leader.next;
        }
        LinkedList follower = head;
        while (leader.next != null) {
            leader = leader.next;
            follower = follower.next;
        }
        LinkedList newHead = follower.next;
        leader.next = head;
        follower.next = null;
        return newHead;
    }

    static LinkedList shiftListBackward(LinkedList head, int k) {
        if (k < 0) {
            k *= -1;
        }
        LinkedList iter = head;
        for (int i = 1; i < k; ++i) {
            iter = iter.next;
        }
        LinkedList newHead = iter.next;
        iter.next = null;
        iter = newHead;
        while (iter.next != null) {
            iter = iter.next;
        }
        iter.next = head;
        return newHead;
    }

    static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            next = null;
        }
    }
}
