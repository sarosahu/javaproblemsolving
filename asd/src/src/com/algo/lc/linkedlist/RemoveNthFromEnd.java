package com.algo.lc.linkedlist;

import com.ds.linkedlist.List;
import com.ds.linkedlist.ListNode;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 */
public class RemoveNthFromEnd {
    // Time: O(L), Space: O(1)
    public static ListNode<Integer>
    removeNthFromEnd(ListNode<Integer> head, int n) {
        ListNode<Integer> preHead = new ListNode<>(-1, head);
        ListNode<Integer> second = preHead;
        ListNode<Integer> first = preHead;
        for (int i = 0; i <= n; ++i) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;

        return preHead.next;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new List<>();
        list1.insertAtBack(1);
        list1.insertAtBack(2);
        list1.insertAtBack(4);
        list1.insertAtBack(6);

        ListNode<Integer> root = removeNthFromEnd(list1.getFirstNode(), 4);
        System.out.println("Resulting list: ");
        while (root != null) {
            System.out.printf("%d --> ", root.data);
            root = root.next;
        }
        System.out.println("NULL");
    }
}
