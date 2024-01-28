package com.algo.ctci.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 4.12 Paths with Sum
 *
 * You are given a binary tree in which each node contains an integer value
 * (which might be positive or negative). Design an algorithm to count of paths
 * that sum to a given value. The path doesn't need to start or end at the root
 * or a leaf, but it must go downwards (travelling only from parent nodes to
 * child nodes).
 */
public class PathsWithSum {
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // { Brute-force approach -- recursive way
    public int countPathsWithSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);

        // Try the nodes on left and right
        int pathsOnLeft = countPathsWithSum(root.left, targetSum);
        int pathsOnRight = countPathsWithSum(root.right, targetSum);

        return pathsFromRoot + pathsOnLeft + pathsOnRight;
    }

    private int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
        int totalPaths = 0;
        if (node == null) {
            return 0;
        }
        currentSum += node.data;
        if (currentSum == targetSum) {
            ++totalPaths;
        }
        totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
        totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);

        return totalPaths;
    }
    // }

    public int countPathsWithSumRO(TreeNode root, int targetSum) {
        return countPathsWithSumRO(root, targetSum, new HashMap<>(), 0);
    }

    // { Optimized way -- recursive - DFS traversal root -> left , then root -> right
    private int countPathsWithSumRO(TreeNode node,
                                          int targetSum,
                                          Map<Integer, Integer> cache,
                                          int runningSum) {
        if (node == null) {
            return 0;
        }
        runningSum += node.data;
        int sum = runningSum - targetSum;
        int totalPath = cache.getOrDefault(sum, 0);
        // if sum = 0, that means there is an additional path including this "node"
        if (sum == 0) {
            ++totalPath;
        }
        // Increment the cache(hash table)
        incrementCache(cache, runningSum, 1);
        totalPath += countPathsWithSumRO(node.left, targetSum, cache, runningSum);
        totalPath += countPathsWithSumRO(node.right, targetSum, cache, runningSum);
        incrementCache(cache, runningSum, -1);

        return totalPath;
    }
    // }

    // { Optimized way -- iterative approach using stack
    public int countPathsWithSumIO(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        int totalPath = 0;
        int runningSum = 0;
        Map<Integer, Integer> cache = new HashMap<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                runningSum += curr.data;
                int sum = runningSum - targetSum;
                totalPath += cache.getOrDefault(sum, 0);
                if (sum == 0) {
                    ++totalPath;
                }
                incrementCache(cache, runningSum, 1);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (curr.right == null) {
                    // Remove curr from stack.
                    //stack.pop();
                    //curr = null;
                    incrementCache(cache, runningSum, -1);
                }
                curr = curr.right;
            }
        }
        return totalPath;
    }

    private void incrementCache(Map<Integer, Integer> cache, int runningSum, int delta) {
        int newCount = cache.getOrDefault(runningSum, 0) + delta;
        if (newCount == 0) {
            cache.remove(runningSum);
        } else {
            cache.put(runningSum, newCount);
        }
    }

    public static void main(String[] args) {
        TreeNode root= new TreeNode(8);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.right.right = new TreeNode(11);

        PathsWithSum pathsWithSum = new PathsWithSum();
        int totalPaths = 0;

        totalPaths = pathsWithSum.countPathsWithSum(root, 8);
        System.out.println("Total paths : " + totalPaths);

        totalPaths = pathsWithSum.countPathsWithSumRO(root, 8);
        System.out.println("Total paths : " + totalPaths);

        totalPaths = pathsWithSum.countPathsWithSumIO(root, 8);
        System.out.println("Total paths : " + totalPaths);
    }
}
