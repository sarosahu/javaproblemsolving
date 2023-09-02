package com.algo.ae.linkedlist;

/**
 * Sum of Linked Lists
 *
 * You're given 2 linked lists of potentially unequal length. Each linked list
 * represents a non-negative integer, where each node in the linked list is a
 * digit of that integer, and the first node in each linked list always
 * represents the least significant digit of the integer. Write a function
 * that returns the head of a new linked list that represents the sum of the
 * integers represented by the 2 input Linked lists.
 *
 * Each LinkedList node has an integer "value" as well as a next node
 * pointing to the next node in the list or to None / null if it's the
 * tail of the list. The value of each LinkedList node is always in the
 * range of 0 - 9.
 *
 * Sample Input:
 * LinkedList one = 2->4->7->1
 * LinkedList two = 9->4->5
 *
 * Sample Output: 1->9->2->2
 *
 * LinkedList one represents 1742
 * LinkedList two represetns 549
 * 1742 + 549 = 2291
 */
public class SumOfLinkedLists {
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList sumOfLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
        LinkedList iter1 = linkedListOne;
        LinkedList iter2 = linkedListTwo;
        int carry = 0;
        LinkedList dummyHead = new LinkedList(-1);
        LinkedList iter = dummyHead;
        while (iter1 != null || iter2 != null) {
            int sum = iter1 != null ? iter1.value : 0;
            sum += iter2 != null ? iter2.value : 0;
            sum += carry;
            iter.next = new LinkedList(sum % 10);
            carry = sum / 10;
            iter = iter.next;
            iter1 = iter1 != null ? iter1.next : null;
            iter2 = iter2 != null ? iter2.next : null;
        }

        if (carry != 0) {
            iter.next = new LinkedList(carry);
        }
        LinkedList newHead = dummyHead.next;
        dummyHead.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        LinkedList one = new LinkedList(2);
        one.next = new LinkedList(4);
        one.next.next = new LinkedList(7);
        one.next.next.next = new LinkedList(1);

        LinkedList two = new LinkedList(9);
        two.next = new LinkedList(4);
        two.next.next = new LinkedList(5);

        LinkedList third = sumOfLinkedLists(one, two);

        LinkedList curr = third;
        while (curr != null) {
            System.out.printf("%d -> ", curr.value);
            curr = curr.next;
        }
        System.out.println();
    }
}
