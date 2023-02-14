package com.algo.ae.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Number of Binary tree topologies
 *
 * Write a function that takes in a non-negative integer n and returns the number of
 * possible Binary Tree topologies that can be created with exactly n nodes.
 *
 * A Binary Tree topology is defined as any Binary Tree configuration, irrespective of
 * node values. For instance, there exist only 2 Binary Tree topologies when n = 2:
 * a root node with a left node and a root node with a right node.
 *
 * Note that when n = 0, there's one topology that can be created: the None/null node.
 *
 * Sample Input: n = 3
 *
 * Sample Output: 5
 */
public class NumberOfBinaryTree {
    // Upper Bound: O((n*(2n)!)/(n!(n+1)!)) time | O(n) space
    public static int numberOfBinaryTreeTopologies(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int numTrees = 0;
        for (int leftTreeSize = 0; leftTreeSize < n; ++leftTreeSize) {
            int rightTreeSize = n - 1 - leftTreeSize;
            int numberOfLeftTree = numberOfBinaryTreeTopologies(leftTreeSize);
            int numberOfRightTree = numberOfBinaryTreeTopologies(rightTreeSize);
            numTrees += numberOfLeftTree * numberOfRightTree;
        }

        return numTrees;
    }

    // Time : O(N^2), Space : O(N)
    public static int numberOfBinaryTreeTopologiesE1(int n) {
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        return numberOfBinaryTreeTopologies(n, cache);
    }

    public static int numberOfBinaryTreeTopologies(int n,
                                                   Map<Integer, Integer> cache)
    {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int numTrees = 0;
        for (int leftTreeSize = 0; leftTreeSize < n; ++leftTreeSize) {
            int rightTreeSize = n - 1 - leftTreeSize;
            int numberOfLeftTrees = numberOfBinaryTreeTopologies(leftTreeSize, cache);
            int numberOfRightTrees = numberOfBinaryTreeTopologies(rightTreeSize, cache);
            numTrees += numberOfLeftTrees * numberOfRightTrees;
        }
        cache.put(n, numTrees);
        return numTrees;
    }

    // Time: O(N^2) | Space : O(N)
    public static int numberOfBinaryTreeTopologiesE2(int n) {
        List<Integer> cache = new ArrayList<>();
        cache.add(1);
        for (int m = 1; m < n + 1; ++m) {
            int numTrees = 0;
            for (int leftTreeSize = 0; leftTreeSize < m; ++leftTreeSize) {
                int rightTreeSize = m - 1 - leftTreeSize;
                int numLeftTrees = cache.get(leftTreeSize);
                int numRightTrees = cache.get(rightTreeSize);
                numTrees += numLeftTrees * numRightTrees;
            }
            cache.add(numTrees);
        }
        return cache.get(n);
    }
}
