package com.algo.ae.bst;

import java.util.Stack;

/**
 * Write a function that takes in a BST and a target integer value and returns
 * the closest value to that target value contained in the BST.
 *
 * You can assume that there will only be one closest value.
 *
 * Each BST node has an integer value, a left child node and a right child node.
 * A node is said to be a valid BST node if and only if it satisfies the BST
 * property: its value is strictly greater than the value of every node to its
 * left; its value is less than or equal to the values of every node to its right;
 * and its children nodes are either valid BST nodes themselves or None/null.
 *
 * Sample input:
 *
 * tree =           10
 *                /   \
 *              5      15
 *            /  \    /  \
 *          2     5  13   22
 *        /           \
 *      1              14
 *
 * Target = 12
 *
 * Sample output: 13
 */
public class FindClosestValueInBST {
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }

    // Time : O(N), Space : O(h)
    public static int findClosestValueInBst(BST tree, int target) {
        Stack<BST> stack = new Stack<>();
        BST curr = tree;
        int diff = Integer.MAX_VALUE;
        int closestElement = -1;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (diff > Math.abs(curr.value - target)) {
                    diff = Math.abs(curr.value - target);
                    closestElement = curr.value;
                }
                curr = curr.right;
            }
        }
        return closestElement;
    }

    // Time: Average case : O(logN), space O(1)
    //       Worst case : O(N), space O(1)
    public static int findClosestValueInBstF(BST tree, int target) {
        BST curr = tree;
        int diff = Integer.MAX_VALUE;
        int close = -1;

        while (curr != null) {
            int currDiff = Math.abs(target - curr.value);
            if (currDiff == 0) {
                close = curr.value;
                break;
            }
            if (currDiff < diff) {
                diff = currDiff;
                close = curr.value;
            }
            if (target < curr.value) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return close;
    }

    public static void main(String[] args) {
        BST bst = new BST(10);
        bst.left = new BST(5);
        bst.right = new BST(15);
        bst.left.left = new BST(2);
        bst.left.right = new BST(5);
        bst.left.left.left = new BST(1);

        bst.right.left = new BST(13);
        bst.right.right = new BST(22);
        bst.right.left.right = new BST(14);

        int closestVal = findClosestValueInBst(bst, 12);
        System.out.println("Closest value : " + closestVal);

        closestVal = findClosestValueInBstF(bst, 12);
        System.out.println("Closest value : " + closestVal);
    }
}
