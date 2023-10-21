package com.algo.lc.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 *
 * Example 2:
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 5 * 104].
 * 1 <= Node.val <= 1000
 */
public class ReorderList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // Time: O(N), space : O(N)
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode last = head;
        Map<ListNode, ListNode> nodeToPrev = new HashMap<>();
        ListNode prev = null;
        while (last.next != null) {
            nodeToPrev.put(last.next, last);
            prev = last;
            last = last.next;
        }
        nodeToPrev.put(last, prev);
        ListNode first = head;
        while (first.next != last) {
            if (first == last) {
                break;
            }
            ListNode nextNode = first.next;
            first.next = last;
            last.next = nextNode;
            first = nextNode;
            last = nodeToPrev.get(last);
        }
        last.next = null;
    }

    // Time : O(N), Space: O(1)
    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        // Find the middle of linked list
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second part of the list
        ListNode prev = null, curr = slow;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        // Merge 2 lists (one starting from head and other starting from prev) now
        ListNode first = head, second = prev;
        while (second.next != null) {
            ListNode next = first.next;
            first.next = second;
            first = next;
            next = second.next;
            second.next = first;
            second = next;
        }
    }
}
