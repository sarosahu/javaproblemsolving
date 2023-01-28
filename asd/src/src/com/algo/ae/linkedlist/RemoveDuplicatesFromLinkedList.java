package com.algo.ae.linkedlist;

/**
 * Remove Duplicates From Linked List
 *
 * You're given the head of a Singly Linked List whose nodes are in
 * sorted order with respect to their values. Write a function that
 * returns a modified version of the Linked List that doesn't contain
 * any nodes with duplicate values. The linked list should be modified
 * in place(i.e. you shouldn't create a brand new list), and the
 * modified linked list should still have its nodes sorted with respect
 * to their values.
 *
 * Each LinkedList node has an integer value as well as a next node
 * pointing to the next node in the list or to None / null if it's the
 * tail of the list.
 *
 * Sample Input:
 *
 * LinkedList = 1->1->3->4->4->4->5->6->6 // Head node with value 1
 *
 * Sample Output: 1->3->4->5->6 // the head node with value 1
 */
public class RemoveDuplicatesFromLinkedList {
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList) {
        // Write your code here.
        LinkedList listIter = linkedList;

        while (listIter != null && listIter.next != null) {
            if (listIter.value == listIter.next.value) {
                listIter.next = listIter.next.next;
            } else {
                listIter = listIter.next;
            }
        }
        return linkedList;
    }

    public static void main(String[] args) {
        LinkedList root = new LinkedList(1);
        root.next = new LinkedList(1);
        root.next.next = new LinkedList(3);
        root.next.next.next = new LinkedList(4);
        root.next.next.next.next = new LinkedList(4);
        root.next.next.next.next.next = new LinkedList(4);
        root.next.next.next.next.next.next = new LinkedList(5);
        root.next.next.next.next.next.next.next = new LinkedList(6);
        root.next.next.next.next.next.next.next.next = new LinkedList(6);
        System.out.println("Printing original linked list :");
        LinkedList curr = root;
        while (curr != null) {
            System.out.printf("%d -> ", curr.value);
            curr = curr.next;
        }
        System.out.println();
        removeDuplicatesFromLinkedList(root);
        System.out.println("Printing after removing duplicates :");
        curr = root;
        while (curr != null) {
            System.out.printf("%d -> ", curr.value);
            curr = curr.next;
        }
        System.out.println();
    }
}
