package com.algo.ae.linkedlist;

/**
 * Merging Linked Lists
 *
 * You're given 2 linked lists of potentially unequal length. These linked lists potentially
 * merge at a shared intersection node. Write a function that returns the intersection node
 * or returns None/null if there is no intersection.
 *
 * Each LinkedList node has an integer value as well as a next node pointing to the next
 * node in the list or to None / null if it's the tail of the list.
 *
 * Note: Your function should return an existing node. It should not modify either Linked
 * list, and it should not create any new linked lists.
 */
public class MergingLinkedLists {
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList mergingLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
        LinkedList small = linkedListOne;
        int countOne = 0;
        while (small != null) {
            ++countOne;
            small = small.next;
        }

        LinkedList big = linkedListTwo;
        int countTwo = 0;
        while (big != null) {
            ++countTwo;
            big = big.next;
        }
        int diff = countOne - countTwo;
        if (diff > 0) {
            small = linkedListTwo;
            big = linkedListOne;
        } else {
            small = linkedListOne;
            big = linkedListTwo;
            diff *= -1;
        }
        for (int i = 0; i < diff; ++i) {
            big = big.next;
        }
        while (small != big) {
            small = small.next;
            big = big.next;
        }
        return small;
    }

    public static LinkedList mergingLinkedLists2(LinkedList linkedListOne, LinkedList linkedListTwo) {
        LinkedList iter1 = linkedListOne;
        LinkedList iter2 = linkedListTwo;

        while (iter1 != iter2) {
            if (iter1 == null) {
                iter1 = linkedListTwo;
            } else {
                iter1 = iter1.next;
            }

            if (iter2 == null) {
                iter2 = linkedListOne;
            } else {
                iter2 = iter2.next;
            }
        }
        return iter1;
    }

    public static void main(String[] args) {
        LinkedList one = new LinkedList(1);
        one.next = new LinkedList(2);
        one.next.next = new LinkedList(3);
        one.next.next.next = new LinkedList(4);
        one.next.next.next.next = new LinkedList(5);

        LinkedList two = new LinkedList(10);
        two.next = one.next.next.next;

        LinkedList mergingNode = mergingLinkedLists(one, two);
        System.out.println("Merging node : " + mergingNode.value);
        mergingNode = mergingLinkedLists2(one, two);
        System.out.println("Merging node : " + mergingNode.value);
    }
}
