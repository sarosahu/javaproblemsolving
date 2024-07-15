package com.algo.epi.linkedlist;

import com.ds.linkedlist.ListNode;

/**
 * EPI LinkedList 8.1
 * Merge 2 sorted lists (linked list).
 */
public class MergeTwoSortedLists {
    public ListNode<Integer> merge(ListNode<Integer> head1, ListNode<Integer> head2) {
        ListNode<Integer> dummy = new ListNode<>();
        ListNode<Integer> iter = dummy;
        while (head1 != null && head2 != null) {
            if (head1.getData() < head2.getData()) {
                iter.next = head1;
                head1 = head1.next;
            } else {
                iter.next = head2;
                head2 = head2.next;
            }
            iter = iter.next;
        }
        if (head1 != null || head2 != null) {
            iter.next = head1 != null ? head1 : head2;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        MergeTwoSortedLists obj = new MergeTwoSortedLists();
        ListNode<Integer> listHead1 = new ListNode<>(2,
                new ListNode<>(7,
                        new ListNode<>(9,
                                new ListNode<>(20,
                                        new ListNode<>(25)))));
        ListNode<Integer> listHead2 = new ListNode<>(1,
                new ListNode<>(3,
                        new ListNode<>(5,
                                new ListNode<>(10,
                                        new ListNode<>(15)))));

        ListNode<Integer> newHead = obj.merge(listHead1, listHead2);
        for (ListNode<Integer> iter = newHead; iter != null; iter = iter.next) {
            System.out.printf("%d -- > ", iter.data);
        }
        System.out.println("NULL");
    }
}
