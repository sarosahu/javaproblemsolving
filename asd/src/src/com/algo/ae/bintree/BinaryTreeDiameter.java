package com.algo.ae.bintree;

/**
 * Binary Tree Diameter
 *
 * Write a function that takes in a Binary Tree and returns its diameter.
 * The diameter of a binary tree is defined as the length of its longest
 * path, even if that path doesn't pass through the root of the tree.
 *
 * A path is a collection of connected nodes in a tree, where no node
 * is connected to more than 2 other nodes. The length of a path is the
 * number of edges between the path's first node and its last node.
 *
 * Each BinaryTree node has an integer value, a left child node, and a
 * right child node. Children nodes can either be BinaryTree nodes
 * themselves or None / null.
 */
public class BinaryTreeDiameter {
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    static class TreeInfo {
        int diameter;
        int height;
        public TreeInfo(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;
        }
    }

    public int binaryTreeDiameter(BinaryTree tree) {
        return getTreeInfo(tree).diameter;
    }

    public TreeInfo getTreeInfo(BinaryTree tree) {
        if (tree == null) {
            return new TreeInfo(0, 0);
        }

        TreeInfo leftTreeInfo = getTreeInfo(tree.left);
        TreeInfo rightTreeInfo = getTreeInfo(tree.right);

        int longestPathThroughRoot = leftTreeInfo.height + rightTreeInfo.height;
        int maxDiameterSoFar = Math.max(leftTreeInfo.diameter, rightTreeInfo.diameter);
        int currentDiameter = Math.max(longestPathThroughRoot, maxDiameterSoFar);
        int currentHeight = 1 + Math.max(leftTreeInfo.height, rightTreeInfo.height);

        return new TreeInfo(currentDiameter, currentHeight);
    }
}
