package com.algo.lc.treegraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 2246. Longest Path With Different Adjacent Characters
 *
 * You are given a tree (i.e. a connected, undirected graph that has no cycles)
 * rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is
 * represented by a 0-indexed array parent of size n, where parent[i] is the parent
 * of node i. Since node 0 is the root, parent[0] == -1.
 *
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 *
 * Return the length of the longest path in the tree such that no pair of adjacent nodes on
 * the path have the same character assigned to them.
 */
public class LongestPathDiffAdjacentCharacters {

    // A single node can always be selected
    private int longestPath = 1;

    // { DFS approach
    // Time: O(N), space : O(N)
    public int longestPathDfs(int[] parent, String s) {

        TreeNode[] nodes = new TreeNode[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            nodes[i] = new TreeNode(s.charAt(i));
        }

        TreeNode root = null;
        for (int i = 0; i < parent.length; ++i) {
            int pIdx = parent[i];
            if (pIdx == -1) {
                root = nodes[i];
            } else {
                nodes[pIdx].children.add(nodes[i]);
            }
        }
        computeMaxPathDfs(root);
        return longestPath;
    }

    public int computeMaxPathDfs(TreeNode node) {
        if (node.children.isEmpty()) {
            return 1;
        }
        int maxPath1 = 0, maxPath2 = 0;
        for (TreeNode child : node.children) {
            int maxPathFromChild = computeMaxPathDfs(child);
            if (node.val == child.val) {
                continue;
            }
            if (maxPathFromChild > maxPath1) {
                maxPath2 = maxPath1;
                maxPath1 = maxPathFromChild;
            } else if (maxPathFromChild > maxPath2){
                maxPath2 = maxPathFromChild;
            }
            // Add 1 for node "node" itself
            longestPath = Math.max(longestPath, maxPath1 + maxPath2 + 1);
        }
        return maxPath1 + 1;
    }
    // } DFS

    // BFS approach.
    // Time: O(N), space : O(N)
    public int longestPathBfs(int[] parent, String s) {
        int n = parent.length;
        int [] childrenCount = new int[n];

        // Start from node 1, since the root node doesn't have parent.
        for (int node = 1; node < n; ++node) {
            childrenCount[parent[node]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[][] longestChains = new int[n][2];

        for (int node = 1; node < n; ++node) {
            // Push all the leaf nodes in the queue.
            if (childrenCount[node] == 0) {
                longestChains[node][0] = 1;
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            int currNode = queue.poll();
            int par = parent[currNode];
            // Get the num. of nodes in the longest chain starting from the currNode
            // including the currentNode.
            int longestChainFromCurrNode = longestChains[currNode][0];
            if (s.charAt(currNode) != s.charAt(par)) {
                if (longestChainFromCurrNode > longestChains[par][0]) {
                    longestChains[par][1] = longestChains[par][0];
                    longestChains[par][0] = longestChainFromCurrNode;
                } else if (longestChainFromCurrNode > longestChains[par][1]) {
                    longestChains[par][1] = longestChainFromCurrNode;
                }
            }

            longestPath = Math.max(longestPath, longestChains[par][0] + longestChains[par][1] + 1);
            childrenCount[par]--;

            if (childrenCount[par] == 0 && par != 0) {
                longestChains[par][0]++;
                queue.offer(par);
            }
        }
        // } BFS

        return this.longestPath;
    }
    static class TreeNode {
        char val;
        List<TreeNode> children = new ArrayList<>();
        public TreeNode(char val) {
            this.val = val;
        }
    }
}
