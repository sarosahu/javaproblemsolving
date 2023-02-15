package com.algo.ae.linkedlist;

/**
 * Zip Linked List
 *
 * You're given the head of a Singly Linked List of arbitrary length k. Write a
 * function that zips the Linked List in place (i.e. doesn't create a brand new list)
 * and returns its head.
 *
 * A Linked List is zipped if its nodes are in the following order, where k is the
 * length of the linked List.
 *
 * 1st node -> kth node -> 2nd node -> (k-1)th node -> 3rd node -> (k - 2)th node -> ...
 *
 * Each LinkedList node has an integer value as well as a next node pointing to the
 * next node in the list or to None / null if it's the tail of the list.
 *
 * You can assume that the input Linked List will always have at least one node; in
 * other words, the head will never be None / null.
 *
 * Sample Input:
 *
 * linkedList = 1 -> 2 -> 3 -> 4 -> 5 -> 6
 *
 * Sample Output: 1 -> 6 -> 2 -> 5 -> 3 -> 4
 */
public class ZipLinkedList {
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // O(n) time | O(1) space -- n is length of the linked list.
    public LinkedList zipLinkedList(LinkedList linkedList) {
        // Write your code here.
        LinkedList head = linkedList;
        if (head == null || head.next == null || head.next.next == null) {
            return linkedList;
        }
        LinkedList iter = linkedList;
        LinkedList fastIter = linkedList;

        while (fastIter.next != null && fastIter.next.next != null) {
            iter = iter.next;
            fastIter = fastIter.next.next;
        }
        LinkedList newHead = iter.next;
        iter.next = null;
        iter = head;
        if (fastIter.next != null) {
            fastIter = fastIter.next;
        }
        // We now have 2nd half of list i.e. starting from
        // newHead till fastIter and let's reverse this list.
        newHead = reverseLinkedList(newHead);

        while (newHead != null) {
            LinkedList secondListNext = newHead.next;
            LinkedList firstListNext = iter.next;
            iter.next = newHead;
            newHead.next = firstListNext;
            newHead = secondListNext;
            iter = firstListNext;
        }
        return head;
    }

    public LinkedList reverseLinkedList(LinkedList head) {
        LinkedList curr = head, prev = null;
        while (curr != null) {
            LinkedList nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    public static void main(String[] args) {

    }
}
