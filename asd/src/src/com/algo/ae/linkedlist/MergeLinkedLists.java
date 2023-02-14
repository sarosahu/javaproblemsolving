package com.algo.ae.linkedlist;

/**
 * Merge Linked Lists
 *
 * Write a function that takes in the heads of two Singly Linked Lists that are in
 * sorted order, respectively. The function should merge lists in place (i.e. it
 * shouldn't create a brand new list) and return the head of the merged list; the
 * merged list should be in sorted order.
 *
 * Each LinkedList node has an integer value as well as next node pointing to the
 * next node in the list or to None/null ifit's the tail of the list.
 *
 * You can assume that the input linked lists will always have at least one node;
 * in other words, the heads will never be None/null.
 *
 * Sample Input:
 * HeadOne = 2 -> 6 -> 7 -> 8
 * HeadTwo = 1 -> 3 -> 4 -> 5 -> 9 -> 10
 *
 * Sample Output:
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
 */
public class MergeLinkedLists {
    public static class LinkedList {
        int value;
        LinkedList next;

        LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // O(n + m) time | o(n + m) space
    public static LinkedList mergeLinkedListsR(LinkedList headOne, LinkedList headTwo) {
        recursiveMerge(headOne, headTwo, null);
        return headOne.value < headTwo.value ? headOne : headTwo;
    }

    public static void recursiveMerge(LinkedList p1,
                                      LinkedList p2,
                                      LinkedList p1Prev)
    {
        if (p1 == null) {
            p1Prev.next = p2;
            return;
        }
        if (p2 == null) {
            return;
        }

        if (p1.value < p2.value) {
            recursiveMerge(p1.next, p2, p1);
        } else {
            if (p1Prev != null) {
                p1Prev.next = p2;
            }
            LinkedList newP2 = p2.next;
            p2.next = p1;
            recursiveMerge(p1, newP2, p2);
        }
    }

    // O(n + m) time | o(1) space, where n is no of nodes in the first
    // LinkedList and m is the number of nodes in the second linked list.
    public static LinkedList mergeLinkedListsL(LinkedList headOne, LinkedList headTwo) {
        // Write your code here.
        LinkedList iter1 = headOne;
        LinkedList iter2 = headTwo;
        LinkedList prevNewHead = new LinkedList(-1);
        LinkedList newIter = prevNewHead;

        while (iter1 != null && iter2 != null) {
            if (iter1.value < iter2.value) {
                newIter.next = iter1;
                iter1 = iter1.next;
            } else if (iter1.value > iter2.value) {
                newIter.next = iter2;
                iter2 = iter2.next;
            } else {
                newIter.next = iter1;
                newIter = newIter.next;
                iter1 = iter1.next;
                newIter.next = iter2;
                iter2 = iter2.next;
            }
            newIter = newIter.next;
        }
        while (iter1 != null) {
            newIter.next = iter1;
            iter1 = iter1.next;
            newIter = newIter.next;
        }
        while (iter2 != null) {
            newIter.next = iter2;
            iter2 = iter2.next;
            newIter = newIter.next;
        }
        return prevNewHead.next;
    }
}
