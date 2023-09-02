package com.algo.ae.bst;

import java.util.List;

/**
 * Min Height BST
 *
 * Write a function that takes in a non-empty sorted array of distinct integers,
 * constructs a BST from the integers, and return the root of BST.
 *
 * The function should minimize the height of the BST.
 *
 * You've been provided with a BST class that you'll have to use to construct
 * the BST. Here is the BST node structure:
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
 * Note that the BST class already has an insert method which you can use if
 * you want.
 */
public class MinHeightBst {
    // Time: O(N), Space: O(N)
    public static BST minHeightBst(List<Integer> array) {
        // Write your code here.
        int left = 0, right = array.size() - 1;
        return minHeightBSTHelper(array, left, right);
    }

    private static BST minHeightBSTHelper(List<Integer> array, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        BST root = new BST(array.get(mid));
        root.left = minHeightBSTHelper(array, left, mid - 1);
        root.right = minHeightBSTHelper(array, mid + 1, right);

        return root;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        public void insert(int value) {
            if (value < this.value) {
                if (left == null) {
                    left = new BST(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new BST(value);
                } else {
                    right.insert(value);
                }
            }
        }
    }
}
