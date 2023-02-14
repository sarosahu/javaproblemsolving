package com.algo.ae.bintree;

/**
 * Height Balanced Binary Tree
 *
 * You're given the root node of a Binary Tree. Write a function that returns true if this
 * Binary Tree is height balanced and false if it isn't.
 *
 * A Binary tree is height balanced if for each node in the tree, the difference between
 * the height of its left subtree and the height of its right subtree is at most 1.
 *
 * Each BinaryTree node has an integer value, a left child node, and a right child node.
 * Children nodes can either be BinaryTree nodes themselves or None/null.
 *
 * Sample Input:
 *
 * tree =               1
 *                    /   \
 *                   2     3
 *                 /  \     \
 *                4    5     6
 *                   /   \
 *                  7     8
 *
 * Sample Output: true
 */
public class HeightBalancedBinaryTree {
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    // Time: O(N), Space : O(h)
    public boolean heightBalancedBinaryTree(BinaryTree tree) {
        // Write your code here.
        return heightBalancedBinTreeHelper(tree).isBalanced;
    }

    public TreeInfo heightBalancedBinTreeHelper(BinaryTree tree) {
        if (tree == null) {
            return new TreeInfo(true, -1);
        }

        TreeInfo leftResult = heightBalancedBinTreeHelper(tree.left);
        if (!leftResult.isBalanced) {
            return new TreeInfo(false, 0);
        }

        TreeInfo rightResult = heightBalancedBinTreeHelper(tree.right);
        if (!rightResult.isBalanced) {
            return new TreeInfo(false, 0);
        }
        int diff = Math.abs(leftResult.height - rightResult.height);
        boolean isBalanced = diff > 1 ? false : true;
        int height = Math.max(leftResult.height, rightResult.height) + 1;
        return new TreeInfo(isBalanced, height);
    }

    static class TreeInfo {
        boolean isBalanced;
        int height;
        TreeInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
}
