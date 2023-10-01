package com.algo.lc.treegraphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next
 * right node, the next pointer should be set to NULL.
 *
 * Example 1:
 *         1
 *       /  \
 *      2    3
 *    /  \  /  \
 *  4    5 6    7
 *
 *  *         1 -> null
 *  *       /  \
 *  *      2 -> 3 -> null
 *  *    /  \  /  \
 *  *  4 -> 5-6 -> 7 -> null
 *
 * Initially, all next pointers are set to NULL.
 */
public class PopulateNextRightPointer {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

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

    // Level order traversal using BFS
    // time: O(N), space: O(N)
    public Node connect(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currLevelSize = queue.size();
            Node prev = null;
            while (currLevelSize > 0) {
                Node curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                if (prev != null) {
                    prev.next = curr;
                }
                prev = curr;
                --currLevelSize;
            }
        }
        return root;
    }

    // Approach 2: Using previously established next pointers
    public Node connect2(Node root) {

        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        // Each level can be treated as one linkedlist
        Node leftmost = root;
        while (leftmost.left != null) {
            Node head = leftmost;
            while (head != null) {
                // Connection 1 -> for the same parent
                head.left.next = head.right;

                // Connection 2 -> for the different parents in same level.
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(1,
                new Node(2, new Node(4), new Node(5)),
                new Node(3, new Node(6), new Node(7))
        );
        PopulateNextRightPointer obj = new PopulateNextRightPointer();
        Node root1 = obj.connect(root);

        Node root2 = obj.connect2(root);
    }
}
