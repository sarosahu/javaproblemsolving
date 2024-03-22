package com.algo.ctci.tree;

import com.algo.ae.bst.ValidateBst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Validate BST
 *
 * Implement a function to check if a binary tree is a binary search tree.
 *
 *  * Each BST node has an integer value, a left child node and a right child
 *  * node. A node is said to be a valid BST node, if and only if it satisfies the
 *  * BST  property: its value is strictly greater than the values of every node
 *  * to its left; its value is less than or equal to the values of every node to
 *  * its right; and its children nodes are either valid BST nodes themselves or
 *  * None / null.
 *  * A BST is valid if and only if all its nodes are valid BST nodes.
 */
public class ValidateBST {
    private Integer lastVisted = null;

    // { In order traversal approach
    /**
     *
     * Note: This algorithm works properly if there are no consecutive
     * duplicate entries. E.g.
     *              10            10
     *            /                \
     *           10                 10
     *
     *  Time: O(N), space: O(d)
     *  where N is total nodes and d is depth of tree.
     */

    public boolean checkBST(TreeNode n) {
        if (n == null) {
            return true;
        }
        // Check / recurse left
        if (!checkBST(n.left)) {
            return false;
        }
        if (lastVisted != null && n.val <= lastVisted) {
            return false;
        }
        lastVisted = n.val;

        // Check / recurse right
        if (!checkBST(n.right)) {
            return false;
        }
        return true; // All good!
    }
    // } In order traversal approach

    // { Pre order traversal approach with min - max approach
    // Time: O(N), space: O(d)
    // where N is total nodes and d is depth of tree.
    public boolean validateBstR(TreeNode node) {
        if (node == null) {
            return true;
        }
        return validateBstHelper(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean validateBstHelper(TreeNode node, int minValue, int maxValue) {
        if (node == null) {
            return true;
        }
        if (node.val < minValue || node.val >= maxValue) {
            return false;
        }
        return validateBstHelper(node.left, minValue, node.val) &&
                validateBstHelper(node.right, node.val, maxValue);
    }
    // } Pre order traversal approach with min - max approach

    // { Iterative way (Using queue)
    // Time: O(N), space: O(d)
    // where N is total nodes and d is depth of tree.
    public static boolean validateBstL(TreeNode tree) {
        // Write your code here.
        if (tree == null) {
            return true;
        }
        Queue<QueueNode> queue = new LinkedList<>();
        queue.add(new QueueNode(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        while (!queue.isEmpty()) {
            QueueNode qnode = queue.poll();
            TreeNode curr = qnode.node;
            int minVal = qnode.minValue;
            int maxVal = qnode.maxValue;
            if (curr.val < minVal || curr.val >= maxVal) {
                return false;
            }
            if (curr.left != null) {
                queue.add(new QueueNode(curr.left, minVal, curr.val));
            }
            if (curr.right != null) {
                queue.add(new QueueNode(curr.right, curr.val, maxVal));
            }
        }
        return true;
    }

    static class QueueNode {
        public TreeNode node;
        public int minValue;
        public int maxValue;

        public QueueNode(TreeNode node, int min, int max) {
            this.node = node;
            this.minValue = min;
            this.maxValue = max;
        }
    }
    // } Iterative way (Using queue)

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10, new TreeNode(10), new TreeNode(15));
        ValidateBST obj = new ValidateBST();
        boolean isValidBst = obj.checkBST(root);
        if (isValidBst) {
            System.out.println("Tree is valid BST");
        } else {
            System.out.println("Tree is invalid BST");
        }
    }
}
