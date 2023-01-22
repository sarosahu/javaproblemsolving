package com.algo.ae.bintree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Node Depths
 *
 * The distance between a node in a Binary tree and the tree's root is
 * called the node's depth.
 *
 * Write a function that takes in a Binary tree and returns the sum of
 * its nodes' depths.
 *
 * Each BinaryTree node has an integer value, a left child node, and a
 * right child node. Children nodes can either be BinaryTree nodes
 * themselves or None / null.
 *
 * Sample input
 * tree =                  1
 *                       /   \
 *                     2      3
 *                   /  \    /  \
 *                 4     5  6    7
 *               /   \
 *             8      9
 *
 *
 *  Sample output: 16
 */
public class NodeDepths {
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

    static class Level {
        public BinaryTree root;
        int depth;

        public Level(BinaryTree root, int depth) {
            this.root = root;
            this.depth = depth;
        }
    }

    /**
     * BFS approach. Time: O(N), Space : O(h)
     */
    public static int nodeDepthsBFS(BinaryTree root) {
        // Write your code here.
        if (root == null) {
            return 0;
        }
        Queue<BinaryTree> q = new LinkedList<>();
        int depthSum = 0;
        int depth = 0;
        int numProcessingElements = 0;
        q.add(root);

        while (!q.isEmpty()) {
            depthSum += depth*q.size();
            numProcessingElements = q.size();

            while (numProcessingElements > 0) {
                BinaryTree curr = q.poll();
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
                --numProcessingElements;
            }
            depth++;
        }
        return depthSum;
    }

    /**
     * Stack approach, Time: O(N), Space : O(h)
     */
    public static int nodeDepths(BinaryTree root) {
        int sumOfDepths = 0;
        Stack<Level> stack = new Stack<>();
        stack.push(new Level(root, 0));

        while (stack.size() > 0) {
            Level top = stack.pop();
            BinaryTree currNode = top.root;
            int depth = top.depth;
            sumOfDepths += depth;
            if (currNode.left != null) {
                stack.push(new Level(currNode.left, depth + 1));
            }
            if (currNode.right != null) {
                stack.push(new Level(currNode.right, depth + 1));
            }
        }
        return sumOfDepths;
    }

    /**
     * Recursive approach
     */
    public static int nodeDepthsR(BinaryTree root) {
        return nodeDepthsHelper(root, 0);
    }

    private static int nodeDepthsHelper(BinaryTree root, int depth) {
        if (root == null) {
            return 0;
        }
        return depth + nodeDepthsHelper(root.left, depth +1)
                + nodeDepthsHelper(root.right, depth + 1);
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(1);
        binaryTree.left = new BinaryTree(2);
        binaryTree.right = new BinaryTree(3);

        binaryTree.left.left = new BinaryTree(4);
        binaryTree.left.right = new BinaryTree(5);

        binaryTree.right.left = new BinaryTree(6);
        binaryTree.right.right = new BinaryTree(7);

        binaryTree.left.left.left = new BinaryTree(8);
        binaryTree.left.left.right = new BinaryTree(9);

        int sumOfDepths = nodeDepthsBFS(binaryTree);
        System.out.println("Sum of depths of all nodes : " + sumOfDepths);

        sumOfDepths = nodeDepths(binaryTree);
        System.out.println("Sum of depths of all nodes : " + sumOfDepths);

        sumOfDepths = nodeDepthsR(binaryTree);
        System.out.println("Sum of depths of all nodes : " + sumOfDepths);
    }
}
