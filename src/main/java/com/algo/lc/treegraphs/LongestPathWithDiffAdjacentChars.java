package com.algo.lc.treegraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 2246. Longest Path With Different Adjacent Characters
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at
 * node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed
 * array parent of size n, where parent[i] is the parent of node i.
 * Since node 0 is the root, parent[0] == -1.
 *
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 *
 * Return the length of the longest path in the tree such that no pair of adjacent nodes on the
 * path have the same character assigned to them.
 * Ex 1:
 *                        'a'
 *                        0
 *                      /  \
 *                'a' 2     1 'b'
 *                  /      / \
 *            'e' 5       3   4
 *                      'c'   'b'
 *
 * Input: parent = [-1,0,0,1,1,2], s = "abacbe"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters
 * in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
 * It can be proven that there is no longer path that satisfies the conditions.
 *
 * Example 2:
 * Input: parent = [-1,0,0,0], s = "aabc"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters is the
 * path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.
 *
 */
public class LongestPathWithDiffAdjacentChars {
    static class TreeNode {
        char val;
        List<TreeNode> children = new ArrayList<>();
        public TreeNode(char val) {
            this.val = val;
        }
    }
    private int longestPath = 1;
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

        return this.longestPath;
    }

    private int computeMaxPathDfs(TreeNode node) {
        if (node.children.isEmpty()) {
            return 1;
        }
        int longest = 0, secondLongest = 0;
        for (TreeNode child : node.children) {
            int maxPathFromChild = computeMaxPathDfs(child);
            if (node.val == child.val) {
                continue;
            }
            if (maxPathFromChild > longest) {
                secondLongest = longest;
                longest = maxPathFromChild;
            } else if (maxPathFromChild > secondLongest){
                secondLongest = maxPathFromChild;
            }
            // Add 1 for node "node" itself
            this.longestPath = Math.max(longestPath, longest + secondLongest + 1);
        }
        return longest + 1;
    }

    // {Kahn's algorithm approach
    public int longestPathBfs(int[] parent, String s) {
        int n = parent.length;
        TreeInfo [] treeInfos = new TreeInfo[n];
        for (int idx = 0; idx < n; ++idx) {
            treeInfos[idx] = new TreeInfo(idx);
        }
        // Start from node 1, since the root node doesn't have parent.
        for (int idx = 1; idx < n; ++idx) {
            treeInfos[parent[idx]].childrenCount++;
        }

        Queue<TreeInfo> queue = new LinkedList<>();

        for (int idx = 1; idx < n; ++idx) {
            // Push all the leaf nodes in the queue.
            if (treeInfos[idx].childrenCount == 0) {
                treeInfos[idx].longest = 1;
                queue.offer(treeInfos[idx]);
            }
        }

        while (!queue.isEmpty()) {
            TreeInfo currTreeInfo = queue.poll();
            int parIdx = parent[currTreeInfo.id];
            // Get the num. of nodes in the longest chain starting from the currNode
            // including the currentNode.
            int longestChainFromCurrNode = currTreeInfo.longest;
            if (s.charAt(currTreeInfo.id) != s.charAt(parIdx)) {
                if (longestChainFromCurrNode > treeInfos[parIdx].longest) {
                    treeInfos[parIdx].secondLongest = treeInfos[parIdx].longest;
                    treeInfos[parIdx].longest = longestChainFromCurrNode;
                } else if (longestChainFromCurrNode > treeInfos[parIdx].secondLongest) {
                    treeInfos[parIdx].secondLongest = longestChainFromCurrNode;
                }
            }

            longestPath = Math.max(longestPath,
                    treeInfos[parIdx].longest + treeInfos[parIdx].secondLongest + 1);
            treeInfos[parIdx].childrenCount--;

            if (treeInfos[parIdx].childrenCount == 0 && parIdx != 0) {
                treeInfos[parIdx].longest++;
                queue.offer(treeInfos[parIdx]);
            }
        }
        return this.longestPath;
    }
    static class TreeInfo {
        int id;
        int longest;
        int secondLongest;
        int childrenCount;
        public TreeInfo(int id) {
            this.id = id;
        }
    }
    // } Kahn's algorithm

    public static void main(String[] args) {
        int [] parent = {-1,0,0,1,1,2};
        String s = "abacbe";
        LongestPathWithDiffAdjacentChars obj = new LongestPathWithDiffAdjacentChars();
        int longest = obj.longestPathDfs(parent, s);
        System.out.println("Longest path (DFS): " + longest);

        longest = obj.longestPathBfs(parent, s);
        System.out.println("Longest path (BFS): " + longest);
    }
}
