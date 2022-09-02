package com.algo.lc.linkedlist;
/**
 * https://leetcode.com/explore/interview/card/google/60/linked-list-5/3065/
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists in a one sorted list. The list should be made by
 * splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 *
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 *
 * Input: list1 = [], list2 = []
 * Output: []
 * Example 3:
 *
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */

import com.ds.linkedlist.List;
import com.ds.linkedlist.ListNode;

public class MergeTwoSortedLists {
    public static ListNode<Integer>
    mergeTwoLists(ListNode<Integer> list1, ListNode<Integer> list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode<Integer> preHead = new ListNode<>(-1, null);
        ListNode<Integer> iter = preHead;

        while (list1 != null && list2 != null) {
            if (list1.data < list2.data) {
                iter.next = list1;
                list1 = list1.next;
            } else if (list2.data < list1.data) {
                iter.next = list2;
                list2 = list2.next;
            } else {
                iter.next = list1;
                list1 = list1.next;
                iter = iter.next;
                iter.next = list2;
                list2 = list2.next;
            }
            iter = iter.next;
        }

        iter.next = list1 == null ? list2 : list1;
        return preHead.next;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new List<>();
        list1.insertAtBack(1);
        list1.insertAtBack(2);
        list1.insertAtBack(4);
        list1.insertAtBack(6);

        List<Integer> list2 = new List<>();
        list2.insertAtBack(1);
        list2.insertAtBack(3);
        list2.insertAtBack(5);
        list2.insertAtBack(6);
        list2.insertAtBack(7);

        ListNode<Integer> resNode = mergeTwoLists(list1.getFirstNode(), list2.getFirstNode());
        System.out.println("The resulting new list: ");
        ListNode<Integer> iter = resNode;
        while (iter != null) {
            System.out.printf("%d --> ", iter.data);
            iter = iter.next;
        }
        System.out.println("NULL");
    }
}
