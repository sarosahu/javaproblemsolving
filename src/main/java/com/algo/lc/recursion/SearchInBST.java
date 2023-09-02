package com.algo.lc.recursion;

/**
 * Search in a Binary Search Tree
 *
 * You are given the root of a binary search tree (BST) and an integer val.
 *
 * Find the node in the BST that the node's value equals val and return the
 * subtree rooted with that node. If such a node does not exist, return null.
 *
 * Example 1:
 *                      4
 *                    /   \
 *                  2      7
 *                /   \
 *               1     3
 *
 *  Input : root = [4, 2, 7, 1, 3], val = 2
 *
 *  Output: [2, 1, 3]
 */
public class SearchInBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Complexity Analysis
     *
     * Time complexity : O(H), where H is a tree height. That results in
     * O(logN) in the average case, and O(N) in the worst case.
     *
     * Space complexity : O(H) to keep the recursion stack,
     * i.e. O(logN) in the average case,
     * and O(N) in the worst case.
     */
    public TreeNode searchBSTr(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        return val < root.val ? searchBSTr(root.left, val) : searchBSTr(root.right, val);
    }

    /**
     * Time: O(H),where H is a tree height. That results in
     * O(logN) in the average case, and O(N) in the worst case
     *
     * Space: O(1)
     */
    public TreeNode searchBSTi(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = val < root.val ? root.left : root.right;
        }
        return root;
    }
}
