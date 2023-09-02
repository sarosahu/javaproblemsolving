package com.algo.ae.linkedlist;

/**
 * Find Loop
 *
 * Write a function that takes in the head of a Singly Linked List that contains
 * a loop (in other words, the list's tail node points to some node in the list
 * instead of None/null). The function should return the node (the actual node
 * --not just the value) from which the loop originates in constant space.
 *
 * Each LinkedList node has an integer value as well as a next node pointing to
 * the next node in the list.
 *
 * Sample Input:
 * head= 0 --> 1 -->2 --> 3 --> 4 --> 5 --> 6
 *                              ^           |
 *                              |           v
 *                              9 <-- 8 <-- 7
 *
 * Sample Output: the node with value 4
 * 4 --> 5 --> 6
 * ^           |
 * |           v
 * 9 <-- 8 <-- 7
 */
public class FindLoop {
    public static LinkedList findLoop(LinkedList head) {
        // Write your code here.
        LinkedList first = head.next;
        if (head.next == null) {
            return null;
        }
        LinkedList second = head.next.next;
        while (first != second && second.next != null) {
            first = first.next;
            second = second.next.next;
        }
        if (first == second) {
            first = head;
            while (first != second) {
                first = first.next;
                second = second.next;
            }
            return first;
        }
        return null;
    }

    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }
}
