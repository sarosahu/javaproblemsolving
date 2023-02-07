package com.algo.ae.linkedlist;

/**
 * Rearrange Linked List
 *
 * Write a function that takes in the head of a Singly Linked List and an integer k,
 * rearranges the list in place(i.e. doesn't create a brand new list) around nodes
 * with value k, and returns its new head.
 *
 * Rearranging a Linked List around nodes with value k means moving all nodes with
 * a value smaller than k before all nodes with value k and moving all nodes with a
 * value greater than k after all nodes with value k.
 *
 * All moved nodes should maintain their original relative ordering if possible.
 *
 * Note that the linked list should be rearranged even if it doesn't have any
 * nodes with value k.
 * Each LinkedList node has an integer value as well as a next node pointing to
 * the next node in the list or to None/null if it's the tail of the list.
 *
 * You can asssume that the input Linked List will always have at least one node;
 * in other words, the head will never be None/null.
 *
 * Sample Input:
 * head = 3 -> 0 -> 5 -> 2 -> 1 -> 4 (the head node with value 3)
 * k = 3
 *
 * Sample Output:
 * 0 -> 2 -> 1 -> 3 -> 5 -> 4 (new head with value 0)
 */
public class RearrangeLinkedList {
    public static LinkedList rearrangeLinkedList(LinkedList head, int k) {
        LinkedList small = new LinkedList(-1);
        LinkedList large = new LinkedList(-1);
        LinkedList equal = new LinkedList(-1);

        LinkedList smallIter = small, largeIter = large, equalIter = equal;
        LinkedList curr = head;

        while (curr != null) {
            if (curr.value < k) {
                smallIter.next = curr;
                smallIter = smallIter.next;
            } else if (curr.value > k) {
                largeIter.next = curr;
                largeIter = largeIter.next;
            } else {
                equalIter.next = curr;
                equalIter = equalIter.next;
            }
            curr = curr.next;
        }

        largeIter.next = null;
        if (equal.next == null) {
            smallIter.next = large.next;
            return small.next;
        }
        smallIter.next = equal.next;
        equalIter.next = large.next;

        return small.next;
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
