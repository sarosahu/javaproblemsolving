package com.algo.ae.bintree;

/**
 * Max Path Sum in Binary Tree
 *
 * Write a function that takes in a Binary Tree and returns its max path sum
 *
 * A path is a collection of connected nodes in a tree, where no node is connected to
 * more than 2 other nodes; a path sum is the sum of the values of the nodes in a
 * particular path.
 *
 * Each BinaryTree node has an integer value, a left child node, and a right child node.
 * Children nodes can either be BinaryTree nodes themselves or None/null.
 *
 * Sample Input:
 *
 * tree =               1
 *                    /   \
 *                  2      3
 *                /  \    /  \
 *               4    5  6    7
 *
 * Sample Output: 18 // 5 +2 + 1 + 3 + 7
 */
public class MaxPathSumInBinaryTree {
    private int maxPathSum = Integer.MIN_VALUE;

    // Time: O(n), space: O(log(n))
    public int maxPathSum(BinaryTree tree) {
        maxPathSumHelper(tree);
        return maxPathSum;
    }

    public int maxPathSumHelper(BinaryTree node) {
        if (node == null) return 0;

        // max sum on the left and right sub-trees of node
        int leftSubTreeMaxSumPath = Math.max(maxPathSumHelper(node.left), 0);
        int rightSubTreeMaxSumPath = Math.max(maxPathSumHelper(node.right), 0);

        // the maximum path sum to start a new path where `node` is a highest node
        int maxPathSumWithCurrentNode = node.value + leftSubTreeMaxSumPath + rightSubTreeMaxSumPath;

        // update max_sum if it's better to start a new path
        maxPathSum = Math.max(maxPathSum, maxPathSumWithCurrentNode);

        // for recursion :
        // return the max sum path if continue the same path
        return node.value + Math.max(leftSubTreeMaxSumPath, rightSubTreeMaxSumPath);
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
