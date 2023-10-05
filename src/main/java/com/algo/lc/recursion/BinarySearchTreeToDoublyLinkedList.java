package com.algo.lc.recursion;

import java.util.Stack;

/**
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor
 * and successor pointers in a doubly-linked list. For a circular doubly linked list,
 * the predecessor of the first element is the last element, and the successor of the
 * last element is the first element.
 *
 * We want to do the transformation in place. After the transformation, the left pointer
 * of the tree node should point to its predecessor, and the right pointer should point
 * to its successor. You should return the pointer to the smallest element of the linked list.
 *
 * Example 1:
 *                          4
 *                        /  \
 *                      2     5
 *                    /  \
 *                  1     3
 *
 * Input:[above tree]
 * output:    1<-->2 <--> 3 <--> 4 <--> 5
 *            ^                         |
 *            | <----------------------->v
 *
 *  Head points to 1.
 *
 *
 *
 *  Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 * All the values of the tree are unique.
 */
public class BinarySearchTreeToDoublyLinkedList {
    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    private Node first = null; // Smallest node
    private Node last = null; // largest node

    /**
     * Complexity Analysis
     *
     * Time complexity : O(N) since each node is processed exactly once.
     *
     * Space complexity : O(N). We have to keep a recursion stack of the size of the tree height,
     * which is O(logN) for the best case of completely balanced tree and O(N) for the worst case
     * of completely unbalanced tree.
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        helper(root);
        // Close the DLL
        last.right = first;
        first.left = last;

        return first;
    }

    private void helper(Node node) {
        if (node == null) {
            return;
        }
        helper(node.left);
        if (last == null) {
            first = node;
        } else {
            last.right = node;
            node.left = last;
        }
        last = node;
        helper(node.right);
    }

    // Iterative approach using stack;
    // Time: O(N), Space : O(N)
    public Node treeToDoublyListI(Node root) {
        if (root == null) {
            return null;
        }
        Node dummy = new Node();
        Node prev = dummy;
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                prev.right = curr;
                curr.left = prev;
                prev = curr;
                curr = curr.right;
            }
        }
        this.first = dummy.right;
        this.last = prev;

        last.right = first;
        first.left = last;

        return first;
    }

    public Node getFirst() {
        return this.first;
    }

    public Node getLast() {
        return this.last;
    }

    public static void main(String[] args) {
        Node root = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        BinarySearchTreeToDoublyLinkedList obj = new BinarySearchTreeToDoublyLinkedList();
        Node tmp = obj.treeToDoublyList(root);
        while (tmp != null) {
            System.out.printf("%d < -- > ", tmp.val);
            if (tmp == obj.getLast()) {
                break;
            }
            tmp = tmp.right;
        }
        System.out.println();

        Node root2 = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        tmp = obj.treeToDoublyListI(root2);
        while (tmp != null) {
            System.out.printf("%d < -- > ", tmp.val);
            if (tmp == obj.getLast()) {
                break;
            }
            tmp = tmp.right;
        }
        System.out.println();
    }
}
