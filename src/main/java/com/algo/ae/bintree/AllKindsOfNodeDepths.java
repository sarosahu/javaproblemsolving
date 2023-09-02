package com.algo.ae.bintree;

import java.util.Stack;

/**
 * All kinds of node depths
 *
 * The distance between a node in a Binary Tree and the tree's root is called
 * the node's depth.
 *
 * Write a function that takes in a Binary Tree and returns the sum of all of
 * its subtrees' nodes' depths.
 *
 * Each "BinaryTree" node has an integer value, a left child node, and a right
 * child node. Children nodes can either be BinaryTree nodes themselves or
 * None/NULL.
 *
 * Sample input:
 *
 * tree   =            1
 *                   /  \
 *                 2     3
 *               /  \   / \
 *             4     5 6   7
 *           /  \
 *         8     9
 *
 * Sample output: 26
 */
public class AllKindsOfNodeDepths {
    // Time: O(n), space : O(h) -- when balanced tree
    public static int allKindsOfNodeDepths(BinaryTree root) {
        int sumOfAllDepths = 0;
        Stack<BinaryTree> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinaryTree curr = stack.pop();
            if (curr == null) {
                continue;
            }
            sumOfAllDepths += nodeDepths(curr, 0);
            stack.push(curr.left);
            stack.push(curr.right);
        }
        return sumOfAllDepths;
    }

    public static int nodeDepths(BinaryTree node, int depth) {
        if (node == null) {
            return 0;
        }
        return depth + nodeDepths(node.left, depth + 1) + nodeDepths(node.right, depth + 1);
    }

    // Time: O(n), space : O(h) -- when balanced tree
    public static int allKindsOfNodeDepthsE(BinaryTree root) {
        return getTreeInfo(root).sumOfAllDepths;
    }

    public static TreeInfo getTreeInfo(BinaryTree root) {
        if (root == null) {
            return new TreeInfo(0, 0, 0);
        }

        TreeInfo leftTreeInfo = getTreeInfo(root.left);
        TreeInfo rightTreeInfo = getTreeInfo(root.right);

        int sumOfLeftTreeDepths = leftTreeInfo.sumOfDepths + leftTreeInfo.numNodes;
        int sumOfRightTreeDepths = rightTreeInfo.sumOfDepths + rightTreeInfo.numNodes;

        int numNodesInTree = 1 + leftTreeInfo.numNodes + rightTreeInfo.numNodes;
        int sumOfDepthsAtThisNode = sumOfLeftTreeDepths +sumOfRightTreeDepths;
        int sumOfAllDepthsAtThisNode = sumOfDepthsAtThisNode +
                leftTreeInfo.sumOfAllDepths +
                rightTreeInfo.sumOfAllDepths;

        return new TreeInfo(numNodesInTree, sumOfDepthsAtThisNode, sumOfAllDepthsAtThisNode);
    }

    // Time: O(n), space : O(h) -- when balanced tree
    public static int allKindsOfNodeDepthsE2(BinaryTree root) {
        return allKindsOfNodeDepthsHelper(root, 0, 0);
    }

    public static int allKindsOfNodeDepthsHelper(BinaryTree node,
                                                 int depthSum,
                                                 int depth)
    {
        if (node == null) {
            return 0;
        }
        depthSum += depth;

        return depthSum
                + allKindsOfNodeDepthsHelper(node.left, depthSum, depth + 1)
                + allKindsOfNodeDepthsHelper(node.right, depthSum, depth + 1);
    }
    public static class TreeInfo {
        public int numNodes;
        public int sumOfDepths;
        public int sumOfAllDepths;

        public TreeInfo(int numNodes,
                        int sumOfDepths,
                        int sumOfAllDepths) {
            this.numNodes = numNodes;
            this.sumOfDepths = sumOfDepths;
            this.sumOfAllDepths = sumOfAllDepths;
        }
    }
    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
    public static void main(String[] args) {

    }
}
