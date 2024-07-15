package com.algo.epi.linkedlist;

import com.ds.linkedlist.ListNode;

/**
 * EPI 8.2: LinkedList
 * Reverse a single linked list.
 */
public class ReverseLinkedList {
    public static ListNode<Integer> reverseLinkedList(ListNode<Integer> head) {
        ListNode<Integer> iter = head;
        ListNode<Integer> prev = null;
        while (iter != null) {
            ListNode<Integer> next = iter.next;
            iter.next = prev;
            prev = iter;
            iter = next;
        }
        return prev;
    }

    public static ListNode<Integer> reverseLinkedListR(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode<Integer> newHead = reverseLinkedListR(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void printList(ListNode<Integer> head) {
        System.out.println("Printing linked list:");
        ListNode<Integer> iter = head;
        while (iter != null) {
            System.out.printf("%d --> ", iter.data);
            iter = iter.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListNode<Integer> head1 = new ListNode<>(2,
                new ListNode<>(7,
                        new ListNode<>(9,
                                new ListNode<>(20,
                                        new ListNode<>(25)))));
        printList(head1);
        ListNode<Integer> newHead = reverseLinkedList(head1);
        printList(newHead);

        ListNode<Integer> head2 = new ListNode<>(1,
                new ListNode<>(2,
                        new ListNode<>(3,
                                new ListNode<>(4,
                                        new ListNode<>(5)))));
        printList(head2);
        newHead = reverseLinkedList(head2);
        printList(newHead);
    }
}
