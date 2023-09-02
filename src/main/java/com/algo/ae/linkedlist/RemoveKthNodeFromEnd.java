package com.algo.ae.linkedlist;

/**
 * Remove Kth Node From End
 *
 * Write a function that takes in the head of a singly linked list and an
 * integer k and removes the kth node from the end of the list.
 *
 * The removal should be done in place, meaning that the original data
 * structure should be mutated (no new structure should be created).
 *
 * Furthermore, the input head of the linked list should remain the head
 * of the linked list after the removal is done, even if the head is the
 * node that's supposed to be removed. In other words, if the head is the
 * node that's supposed to be removed, your function should simply mutate
 * its value and next pointer.
 *
 * Note that your function doesn't need to return anything.
 *
 * You can assume that the input linked list will always have at least 2
 * nodes and, more specifically, at least k nodes.
 *
 * Each LinkedList node has an integer "value" as well as a next node
 * pointing to the next node in the list or to None / null if it's the
 * tail of the list.
 *
 * Sample Input:
 * head = 0->1->2->3->4->5->6->7->8->9 // the head node with value 0
 * k = 4
 *
 * Sample output:
 * 0->1->2->3->4->5->7->8->9
 */
public class RemoveKthNodeFromEnd {
    public static LinkedList removeKthNodeFromEnd(LinkedList head, int k) {
        // Write your code here.
        LinkedList iter1 = head, iter2 = head;
        int counter = 1;
        for (; counter <= k && iter2 != null; ++counter) {
            iter2 = iter2.next;
        }
        if (counter < k) {
            return head;
        }

        // Head needs to be removed.
        if (iter2 == null) {
            // There is only one node.
            if (iter1.next == null) {
                head = null;
            } else {
                iter1.value = iter1.next.value;
                iter1.next = iter1.next.next;
            }
            return head;
        }
        while (iter2.next != null) {
            iter1 = iter1.next;
            iter2 = iter2.next;
        }
        LinkedList toRemoveNode = iter1.next;
        iter1.next = toRemoveNode.next;
        toRemoveNode.next = null;
        return head;
    }

    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LinkedList root = new LinkedList(1);
        root.next = new LinkedList(2);
        root.next.next = new LinkedList(3);
        root.next.next.next = new LinkedList(4);
        // Print the linked list
        LinkedList curr = root;
        while (curr != null) {
            System.out.printf("%d -> ", curr.value);
            curr = curr.next;
        }
        int k = 1;
        root = removeKthNodeFromEnd(root, k);
        System.out.println("Linked list after removing " + k + " node from last");
        curr = root;
        while (curr != null) {
            System.out.printf("%d -> ", curr.value);
            curr = curr.next;
        }
    }
}
