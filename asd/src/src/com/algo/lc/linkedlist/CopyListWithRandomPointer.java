package com.algo.lc.linkedlist;


import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    Map<Node, Node> visitedHash = new HashMap<>();
    public Node
    copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (visitedHash.containsKey(head)) {
            return visitedHash.get(head);
        }

        Node newNode = new Node(head.val);
        this.visitedHash.put(head, newNode);

        newNode.next = copyRandomList(head.next);
        newNode.random = copyRandomList(head.random);

        return newNode;
    }

    public Node
    copyRandomListE(Node head) {
        if (head == null) {
            return null;
        }

        // Creating a new weaved list of original and copied nodes.
        Node ptr = head;
        while (ptr != null) {
            // Cloned node
            Node newNode = new Node(ptr.val);

            // Inserting the cloned node just next to the original node.
            // If A -> B -> C is original linked list,
            // Linked list after weaving cloned nodes would be
            // A -> A' -> B -> B' -> C -> C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = ptr.next.next;
        }

        ptr = head;

        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        // Unweave the linked list to get back the original linked list
        // and the cloned list. i.e. A -> A' -> B -> B' -> C -> C' would
        // be broken to A -> B -> C and A' -> B' -> C'
        Node ptrOldList = head;
        Node ptrNewList = head.next;
        Node newHead = head.next;

        while (ptrOldList != null) {
            ptrOldList.next = ptrOldList.next.next;
            ptrNewList.next = (ptrNewList.next != null) ? ptrNewList.next.next : null;
            ptrOldList = ptrOldList.next;
            ptrNewList = ptrNewList.next;
        }
        return newHead;
    }
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        n2.random = n1;
        n3.random = n5;
        n4.random = n3;
        n5.random = n1;

        CopyListWithRandomPointer obj = new CopyListWithRandomPointer();
        Node newNode = obj.copyRandomList(n1);
        while (newNode != null) {
            System.out.printf("%d --> ", newNode.val);
            newNode = newNode.next;
        }
        System.out.println("NULL");

        Node newNode2 = obj.copyRandomListE(n1);
        while (newNode2 != null) {
            System.out.printf("%d --> ", newNode2.val);
            newNode2 = newNode2.next;
        }
        System.out.println("NULL");
    }
}
