package com.algo.ae.linkedlist;

/**
 * Linked List Construction
 *
 * Write a DoublyLinkedList class that has a head and a tail, both of which point to
 * either a linked list Node or None/null. The class should support:
 *      - Setting the head and tail of the linked list.
 *      - inserting nodes before and after other nodes as well as at given positions
 *        (the position of the head node is 1)
 *      - Removing given nodes and removing nodes with given values.
 *      - Searching for nodes with given values.
 *
 * Note that the setHead, setTail, insertBefore, insertAfter, insertAtPosition, and
 * remove methods all take in actual Node s as input parameters - not integers (
 * except for insertAtPosition, which also taks in an integer representing the
 * position); this means that you don't need to create any new Node s in these
 * methods. The input nodes can be either stand-alone nodes or nodes that are
 * already in the linked list. If input nodes can be either stand-alone nodes or
 * nodes that are already in the linked list. If they are nodes that are already
 * in the linked list, the methods will effectively be moving the nodes within the
 * linked list. You won't be told if the input nodes are already in the linked
 * list, so your code will have to defensively handle this scenario.
 *
 * Each Node has an integer value as well as a prev node and a next node, both of
 * which can point to either another node or None/null.
 */
public class LinkedListConstruction {
    static class DoublyLinkedList {
        public Node head;
        public Node tail;

        /**
         *
         * @param node
         * The parameter "node" will be new head.
         */
        public void setHead(Node node) {
            if (this.head == null) {
                this.head = node;
                this.tail = node;
                return;
            }
            this.insertBefore(this.head, node);
        }

        /**
         * @param node
         * This will set the parameter "node" to its new tail.
         */
        public void setTail(Node node) {
            // Write your code here.
            if (this.tail == null) {
                this.tail = node;
                this.head = node;
                return;
            }
            this.insertAfter(tail, node);
        }

        /**
         *
         * @param node
         * @param nodeToInsert
         * This method will insert the node "nodeToInsert" before "node"
         */
        public void insertBefore(Node node, Node nodeToInsert) {
            // If the new inserting node is the only node, then do nothing.
            if (node == null || (nodeToInsert == head && nodeToInsert == tail)) {
                return;
            }
            this.remove(nodeToInsert);
            Node prevNode = node.prev;
            nodeToInsert.prev = prevNode;
            if (prevNode == null) {
                // node is head
                this.head = nodeToInsert;
            } else {
                prevNode.next = nodeToInsert;
            }
            node.prev = nodeToInsert;
            nodeToInsert.next = node;
        }

        /**
         *
         * @param node
         * @param nodeToInsert
         * This will make the node "nodeToInsert" as next node for "node"
         */
        public void insertAfter(Node node, Node nodeToInsert) {
            // If the node "nodeToInsert" is the only node, then do nothing
            if (node == null || (nodeToInsert == head && nodeToInsert == tail)) {
                return;
            }
            this.remove(nodeToInsert);
            Node nextNode = node.next;
            nodeToInsert.prev = node;
            node.next = nodeToInsert;
            if (nextNode == null) {
                // node is tail
                this.tail = nodeToInsert;
            } else {
                nextNode.prev = nodeToInsert;
            }
            nodeToInsert.next = nextNode;
        }

        /**
         * @param position
         * @param nodeToInsert
         * This will insert the node "nodeToInsert" at position "position"
         */
        public void insertAtPosition(int position, Node nodeToInsert) {
            // Write your code here.
            if (position == 1) {
                this.setHead(nodeToInsert);
                return;
            }
            Node iter = this.head;
            int i = 1;
            while (i < position && iter != null) {
                iter = iter.next;
                ++i;
            }
            if (i == position) {
                this.insertBefore(iter, nodeToInsert);
            }
        }

        /**
         * @param value
         * Remove all the nodes with value = "value"
         */
        public void removeNodesWithValue(int value) {
            // Write your code here.
            Node iter = this.head;
            Node nextIter = null;
            while (iter != null) {
                nextIter = iter.next;
                if (iter.value == value) {
                    // remove this node
                    this.remove(iter);
                }
                iter = nextIter;
            }
        }

        /**
         *
         * @param node
         * It will remove the node "node" from the existing linked list.
         */
        public void remove(Node node) {
            // Write your code here.
            if (node == head) {
                this.head = this.head.next;
            }
            if (node == this.tail) {
                this.tail = this.tail.prev;
            }
            this.removeNodeBindings(node);
        }

        /**
         * @param value
         * @return : boolean , returns if a node with value "value" is present.
         */
        public boolean containsNodeWithValue(int value) {
            // Write your code here.
            Node iter = this.head;
            while (iter != null) {
                if (iter.value == value) {
                    return true;
                }
                iter = iter.next;
            }
            return false;
        }

        /**
         * @param node
         * This will remove the bindings for its previous and next nodes
         */
        private void removeNodeBindings(Node node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            node.prev = null;
            node.next = null;
        }
    }

    // Do not edit the class below.
    static class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
