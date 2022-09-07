package com.algo.ae.linkedlist;

public class ZipLinkedList {
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public LinkedList zipLinkedList(LinkedList linkedList) {
        // Write your code here.
        LinkedList head = linkedList;
        if (head == null || head.next == null || head.next.next == null) {
            return linkedList;
        }
        LinkedList iter = linkedList;
        LinkedList fastIter = linkedList;

        while (fastIter.next != null && fastIter.next.next != null) {
            iter = iter.next;
            fastIter = fastIter.next.next;
        }
        LinkedList newHead = iter.next;
        iter.next = null;
        iter = head;
        if (fastIter.next != null) {
            fastIter = fastIter.next;
        }
        // We now have 2nd half of list i.e. starting from
        // newHead till fastIter and let's reverse this list.
        newHead = reverseLinkedList(newHead);

        while (newHead != null) {
            LinkedList secondListNext = newHead.next;
            LinkedList firstListNext = iter.next;
            iter.next = newHead;
            newHead.next = firstListNext;
            newHead = secondListNext;
            iter = firstListNext;
        }
        return head;
    }

    public LinkedList reverseLinkedList(LinkedList head) {
        LinkedList curr = head, prev = null;
        while (curr != null) {
            LinkedList nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    public static void main(String[] args) {

    }
}
