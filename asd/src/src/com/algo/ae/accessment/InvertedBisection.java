package com.algo.ae.accessment;

public class InvertedBisection {
    public static LinkedList invertedBisection(LinkedList head) {
        int n = getSize(head);
        if (n <= 3) {
            return head;
        }
        boolean isOdd = false;
        if (n % 2 == 1) {
            isOdd = true;
        }
        LinkedList iter = head;
        for (int i = 1; i < n/2; ++i) {
            iter = iter.next;
        }
        LinkedList middleNode = isOdd ? iter.next : null;
        LinkedList secondHead = isOdd ? iter.next.next : iter.next;
        iter.next = null;
        LinkedList firstTail = head;
        //LinkedList secondIter = secondHead;

        LinkedList newHead = reverseList(head);
        LinkedList newHead2 = reverseList(secondHead);
        firstTail.next = isOdd ? middleNode : newHead2;

        return newHead;
    }

    public static LinkedList reverseList(LinkedList root) {
        LinkedList prev = null;
        LinkedList curr = root;
        while (curr != null) {
            LinkedList next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static int getSize(LinkedList root) {
        int n = 0;
        LinkedList iter = root;
        while (iter != null) {
            n++;
            iter = iter.next;
        }
        return n;
    }

    // This is an input class. Do not edit.
    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LinkedList root = new LinkedList(0);
        root.next = new LinkedList(1);
        root.next.next = new LinkedList(2);
        root.next.next.next = new LinkedList(3);
        root.next.next.next.next = new LinkedList(4);
        //root.next.next.next.next.next = new LinkedList(5);

        LinkedList newHead = invertedBisection(root);
    }
}
