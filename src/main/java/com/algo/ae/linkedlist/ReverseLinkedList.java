package com.algo.ae.linkedlist;

/**
 * Reverse a Linked List
 *
 * Write a function that takes in the head of a Singly Linked List, reverses
 * the list in place (i.e. doesn't create a brand new list), and returns its
 * new head.
 *
 * Each LinkedList node has an integer value as well as a next node pointing to
 * the next node in the list or to None/null if it's the tail of the list.
 *
 * You can asssume that the input Linked List will always have at least one node;
 * in other words, the head will never be None/null.
 *
 * Sample Input:
 * head = 0 -> 1 -> 2 -> 3 -> 4 -> 5
 *
 * Sample Output: 5 -> 4 -> 3 -> 2 -> 1 -> 0
 */
public class ReverseLinkedList {

    // Time : O(n), Space : O(1)
    public static LinkedList reverseLinkedListL(LinkedList head) {
        // Write your code here.
        LinkedList iter = head;
        LinkedList prev = null;
        while (iter != null) {
            LinkedList nextNode = iter.next;
            iter.next = prev;
            prev = iter;
            iter = nextNode;
        }
        return prev;
    }

    // Recursive approach.
    // Time : O(n) | Space: O(n)
    public static LinkedList reverseLinkedListR(LinkedList head) {
        return reverseHelper(null, head);
    }

    public static LinkedList reverseHelper(LinkedList prev,
                                           LinkedList curr)
    {
        if (curr == null) {
            return prev;
        }
        LinkedList nextNode = curr.next;
        curr.next = prev;
        return reverseHelper(curr, nextNode);
    }

    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }

    public static LinkedList reverseLinkedListR2(LinkedList head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedList newHead = reverseLinkedListR2(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        LinkedList node = new LinkedList(1);
        node.next = new LinkedList(2);
        node.next.next = new LinkedList(3);
        node.next.next.next = new LinkedList(4);
        node.next.next.next.next = new LinkedList(5);

        LinkedList newHead = reverseLinkedListL(node);
        LinkedList p = newHead;
        while (p != null) {
            System.out.print(p.value + "-->");
            p = p.next;
        }
        System.out.print("NULL");
    }
}
