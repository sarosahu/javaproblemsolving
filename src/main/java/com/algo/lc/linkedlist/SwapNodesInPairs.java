package com.algo.lc.linkedlist;

/**
 * Swap Nodes in Pairs
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
 *
 * Example 1:
 * 1->2->3->4
 *
 * 2->1->4->3
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 */
public class SwapNodesInPairs {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = this.val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * Recusive way.
     * Complexity Analysis
     *
     * Time Complexity: O(N) where N is the size of the linked list.
     * Space Complexity: O(N) stack space utilized for recursion.
     */

    public static ListNode swapPairs(ListNode head) {
        return swapPairsHelper(head);
    }

    public static ListNode swapPairsHelper(ListNode node) {
        // If the list has no node or has only one node left.
        if (node == null || node.next == null) {
            return node;
        }

        // Nodes to be swapped.
        ListNode firstNode = node;
        ListNode secondNode = node.next;

        // Swapping
        firstNode.next = swapPairsHelper(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second one.
        return secondNode;
    }


    /**
     * Iterative approach:
     * Complexity Analysis
     *
     * Time Complexity : O(N) where N is the size of the linked list.
     *
     * Space Complexity : O(1).
     */
    public static ListNode swapPairsi(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy;

        while ((head != null) && (head.next != null)) {
            // Nodes to be swapped
            ListNode first = head;
            ListNode second = head.next;

            // swapping
            prev.next = second;
            first.next = second.next;
            second.next = first;

            // Reinitializing prev and head
            prev = first;
            head = first.next;
        }
        // Return the new head
        return dummy.next;
    }
}
