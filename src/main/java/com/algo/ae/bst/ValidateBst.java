package com.algo.ae.bst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Validate BST
 *
 * Write a function that takes in a potentially invalid BST and returns
 * a boolean representing if the BST is valid.
 *
 * Each BST node has an integer value, a left child node and a right child
 * node. A node is said to be a valid BST node, if and only if it satisfies the
 * BST  property: its value is strictly greater than the values of every node
 * to its left; its value is less than or equal to the values of every node to
 * its right; and its children nodes are either valid BST nodes themselves or
 * None / null.
 * A BST is valid if and only if all its nodes are valid BST nodes.
 */

public class ValidateBst {
    // Recursive way: O(n) time, O(d) space
    public static boolean validateBstR(BST tree) {
        if (tree == null) {
            return true;
        }
        return validateBstHelper(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean validateBstHelper(BST root, int minValue, int maxValue) {
        if (root == null) {
            return true;
        }
        if (root.value < minValue || root.value >= maxValue) {
            return false;
        }
        return validateBstHelper(root.left, minValue, root.value) &&
                validateBstHelper(root.right, root.value, maxValue);
    }

    // Iterative way: O(n) time, O(d) space
    public static boolean validateBstL(BST tree) {
        // Write your code here.
        if (tree == null) {
            return true;
        }
        Queue<QueueNode> queue = new LinkedList<>();
        queue.add(new QueueNode(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        while (!queue.isEmpty()) {
            QueueNode qnode = queue.poll();
            BST curr = qnode.node;
            int minVal = qnode.minValue;
            int maxVal = qnode.maxValue;
            if (curr.value < minVal || curr.value >= maxVal) {
                return false;
            }
            if (curr.left != null) {
                queue.add(new QueueNode(curr.left, minVal, curr.value));
            }
            if (curr.right != null) {
                queue.add(new QueueNode(curr.right, curr.value, maxVal));
            }
        }
        return true;
    }

    static class QueueNode {
        public BST node;
        public int minValue;
        public int maxValue;

        public QueueNode(BST node, int min, int max) {
            this.node = node;
            this.minValue = min;
            this.maxValue = max;
        }
    }
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        BST bst = new BST(10);
        bst.left = new BST(1);
        bst.right = new BST(10);

        boolean isValid = validateBstR(bst);
        if (isValid) {
            System.out.println("Valid BST");
        } else {
            System.out.println("Invalid BST");
        }

        isValid = validateBstL(bst);
        if (isValid) {
            System.out.println("Valid BST");
        } else {
            System.out.println("Invalid BST");
        }
    }
}
