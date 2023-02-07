package com.algo.ae.linkedlist;

/**
 * Node Swap
 *
 * Write a function that takes in the head of a Singly Linked List, swaps every pair
 * of adjacent nodes in place(i.e. doesn't create a brand new list), and returns its
 * new head.
 *
 * If the input linked list has an odd number of nodes, its final node should remain
 * same.
 *
 * Each LinkedList node has an integer value as well as a next node pointing to
 * the next node in the list or to None/null if it's the tail of the list.
 *
 * You can asssume that the input Linked List will always have at least one node;
 * in other words, the head will never be None/null.
 *
 * Sample Input:
 * head = 0 -> 1 -> 2 -> 3 -> 4 -> 5 // the head node with value 0
 *
 * Sample Output: 1 -> 0 -> 3 -> 2 -> 5 -> 4 // the head node with value 1
 */
public class NodeSwap {
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // This is not really a node swap, but value swap
    // Time : O(N), space: O(1)
    public LinkedList nodeSwap(LinkedList head) {
        // Write your code here.
        LinkedList curr = head;
        while (curr != null) {
            if (curr.next == null) {
                break;
            }
            int temp = curr.value;
            curr.value = curr.next.value;
            curr.next.value = temp;
            curr = curr.next.next;
        }
        return head;
    }

    // This is actual node swap
    // Time: O(N), Space: O(1)
    public LinkedList nodeSwapL(LinkedList head) {
        // Write your code here.
        // Loop approach
        LinkedList tempNode = new LinkedList(0);
        tempNode.next = head;
        LinkedList prevNode = tempNode;

        while (prevNode.next != null && prevNode.next.next != null) {
            LinkedList firstNode = prevNode.next;
            LinkedList secondNode = prevNode.next.next;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;
            prevNode.next = secondNode;

            prevNode = firstNode;
        }
        return tempNode.next;
    }

    // Recursive approach
    // Time: O(N), Space: O(N)
    public LinkedList nodeSwapR(LinkedList head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedList nextNode = head.next;
        head.next = nodeSwapR(head.next.next);
        nextNode.next = head;

        return nextNode;
    }
}
