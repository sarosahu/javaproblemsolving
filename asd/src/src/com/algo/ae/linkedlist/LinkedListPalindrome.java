package com.algo.ae.linkedlist;

/**
 * Linked List Palindrome
 *
 * Write a function that takes in the head of a Singly Linked List and returns a
 * boolean representing whether the linked list's nodes form palindrome. Your
 * function shouldn't make use of any auxiliary data structure.
 *
 * A palindrome is usually defined as a string that is written the same forward
 * and backward. For a linked list's nodes to form a palindrome, their values
 * must be the same when read from left to right and from right to left. Note
 * that single-character strings are palindrome, which means that single-node
 * linked lists form palindromes.
 *
 * Each LinkedList node has an integer value as well as a next node pointing to
 * the next node in the list or to None/null if it's the tail of the list.
 *  *
 * You can asssume that the input Linked List will always have at least one node;
 * in other words, the head will never be None/null.
 *
 * Sample Input:
 * head = 0 -> 1 -> 2 -> 2 -> 1 -> 0  // the head node with value 0
 *
 * Sample Output: true
 */
public class LinkedListPalindrome {
    // This is an input class. Do not edit.
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private LinkedList forwardIterNode;

    public boolean linkedListPalindromeR(LinkedList head) {
        forwardIterNode = head;
        return recurseList(head);
    }

    public boolean recurseList(LinkedList currentNode) {
        if (currentNode == null) {
            return true;
        }
        if (!recurseList(currentNode.next)) {
            return false;
        }
        if (currentNode.value != forwardIterNode.value) {
            return false;
        }
        forwardIterNode = forwardIterNode.next;

        return true;
    }


    public boolean linkedListPalindromeL(LinkedList head) {
        // Write your code here.
        LinkedList slow = head, fast = head;
        LinkedList prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slow == fast) {
            return true;
        }
        LinkedList nextHalfHead = null;
        if (fast != null) {
            nextHalfHead = slow.next;
        } else {
            nextHalfHead = slow;
        }
        prev.next = null;
        nextHalfHead = reverseLinkedList(nextHalfHead);
        boolean isPalindrom = checkPalindrom(head, nextHalfHead);
        nextHalfHead = reverseLinkedList(nextHalfHead);
        prev.next = nextHalfHead;
        return isPalindrom;
    }

    public boolean checkPalindrom(LinkedList h1, LinkedList h2) {
        while (h1 != null && h2 != null) {
            if (h1.value != h2.value) {
                return false;
            }
            h1 = h1.next;
            h2 = h2.next;
        }
        return true;
    }

    public LinkedList reverseLinkedList(LinkedList head) {
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
}
