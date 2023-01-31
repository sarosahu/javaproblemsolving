package com.algo.ae.bst;

import java.util.Stack;

/**
 * Find Kth Largest Value In BST
 *
 * Write a function that takes in a Binary Search Tree (BST) and a positive
 * integer k and returns kth largest integer contained in the BST.
 *
 * You can assume that there will only be integer values in the BST and that
 * k is less than or equal to the number of nodes in the tree.
 *
 * Also, for the purpose of this question, duplicate integers will be treated
 * as distinct values. In other words, the second largest value in a BST
 * containing values {5, 7, 7} will be 7 - not 5.
 Here is the BST node structure:
 *  * class BST {
 *  *     public int value;
 *  *     public BST left;
 *  *     public BST right;
 *  *
 *  *     public BST(int value) {
 *  *       this.value = value;
 *  *     }
 *  * }
 * A node is said to be a valid BST node, if and only if it satisfies the
 * BST  property: its value is strictly greater than the values of every node
 * to its left; its value is less than or equal to the values of every node to
 * its right; and its children nodes are either valid BST nodes themselves or
 * None / null.
 * A BST is valid if and only if all its nodes are valid BST nodes.
 *
 */
public class FindKthLargestValueInBst {
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    public int findKthLargestValueInBst(BST tree, int k) {
        // Write your code here.
        Stack<BST> stack = new Stack<>();
        BST curr = tree;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.right;
            } else {
                curr = stack.peek();
                stack.pop();
                --k;
                if (k == 0) {
                    return curr.value;
                }
                curr = curr.left;
            }
        }
        return -1;
    }
}
