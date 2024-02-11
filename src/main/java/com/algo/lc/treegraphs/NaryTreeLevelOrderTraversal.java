package com.algo.lc.treegraphs;

import java.util.*;

/**
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value (See examples).
 *
 * Example 1:
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [[1],[3,2,4],[5,6]]
 *
 * Example 2:
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 *
 * Constraints:
 *
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 10^4]
 *
 */
public class NaryTreeLevelOrderTraversal {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    // { BFS - using queue
    // Time: O(N), space: O(N)
    public List<List<Integer>> levelOrderBFS(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currSize = queue.size();
            List<Integer> entry = new ArrayList<>();
            while (currSize > 0) {
                Node curr = queue.poll();
                entry.add(curr.val);
                enqueueChildren(queue, curr);
                --currSize;
            }
            result.add(entry);
        }
        return result;
    }
    // }

    // {BFS (simplified) without Queue
    // Time: O(N), space: O(N)
    public List<List<Integer>> levelOrderBFS2(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Node> prevLayer = Arrays.asList(root);
        while (!prevLayer.isEmpty()) {
            List<Node> currLayer = new ArrayList<>();
            List<Integer> prevVals = new ArrayList<>();
            for (Node node : prevLayer) {
                prevVals.add(node.val);
                currLayer.addAll(node.children);
            }
            result.add(prevVals);
            prevLayer = currLayer;
        }
        return result;
    }
    // }

    // { DFS approach (recursive)
    public List<List<Integer>> levelOrderDFS(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            traverse(root, 0, result);
        }
        return result;
    }

    private void traverse(Node node, int level, List<List<Integer>> result) {
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(node.val);
        for (Node child : node.children) {
            traverse(child, level + 1, result);
        }
    }
    // }

    private void enqueueChildren(Queue<Node> queue, Node node) {
        for (Node child : node.children) {
            queue.offer(child);
        }
    }
}
