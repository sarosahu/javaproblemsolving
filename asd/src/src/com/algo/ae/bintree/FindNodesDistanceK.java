package com.algo.ae.bintree;

import java.util.*;

/**
 * Find Nodes Distance K
 *
 * You're given the root node of a Binary Tree, a target value of a node that is contained
 * in the tree, and a +ve integer k. Write a function that returns the values of all the
 * nodes that are exactly distance k from the node with target value.
 *
 * The distance between 2 nodes is defined as the number of edges that must be traversed
 * to go from one node to the other. For example, the distance between a node and its
 * immediate left or right child is 1. The same holds in reverse: the distance between
 * a node and its parent is 1. In a tree of 3 nodes, where the root node has a left and
 * right child, the left and right children are distance 2 from each other.
 *
 * Each BinaryTree node has an integer value, a left child node, and a right child node.
 * Children nodes can either be BinaryTree nodes themselves or None/null.
 *
 * Note that all BinaryTree nodes values will be unique, and your function can return the
 * output values in any order.
 *
 * Sample Input:
 * tree =                   1
 *                        /   \
 *                      2      3
 *                    /   \      \
 *                  4      5      6
 *                              /   \
 *                            7      8
 *  target = 3, k = 2
 *
 *  Sample Output: [2, 7, 8]  // These values could be ordered differently
 */
public class FindNodesDistanceK {
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    static class Pair<U, V> {
        public final U first;
        public final V second;

        private Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    // Time: O(n), space O(n)
    public ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k) {
        // Write your code here. I referred the solution 1.
        // Store the parent of each node in a hashmap.
        Map<Integer, BinaryTree> nodesToParent = new HashMap<Integer, BinaryTree>();
        populateNodesToParents(tree, nodesToParent, null);
        BinaryTree targetNode = getNodeFromValue(target, tree, nodesToParent);
        return fetchKthNodesFromTarget(targetNode, nodesToParent, k);
    }

    private ArrayList<Integer> fetchKthNodesFromTarget(BinaryTree targetNode,
                                                       Map<Integer, BinaryTree> nodesToParent,
                                                       int k) {
        Queue<Pair<BinaryTree, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<BinaryTree, Integer>(targetNode, 0));

        HashSet<Integer> seen = new HashSet<>();
        seen.add(targetNode.value);

        while (!queue.isEmpty()) {
            Pair<BinaryTree, Integer> currPair = queue.poll();
            BinaryTree currNode = currPair.first;
            int distFromTarget = currPair.second;

            if (distFromTarget == k) {
                ArrayList<Integer> kthNodes = new ArrayList<Integer>();
                // Queue now has all the entries at kth distances.
                for (Pair<BinaryTree, Integer> pair : queue) {
                    kthNodes.add(pair.first.value);
                }
                kthNodes.add(currNode.value);
                return kthNodes;
            }

            List<BinaryTree> connectedNodes = new ArrayList<BinaryTree>();
            connectedNodes.add(currNode.left);
            connectedNodes.add(currNode.right);
            connectedNodes.add(nodesToParent.get(currNode.value));

            for (BinaryTree node : connectedNodes) {
                if (node == null || seen.contains(node.value)) {
                    continue;
                }
                seen.add(node.value);
                queue.add(new Pair<BinaryTree, Integer>(node, distFromTarget + 1));
            }
        }

        return new ArrayList<Integer>();
    }

    public BinaryTree getNodeFromValue(int target, BinaryTree tree, Map<Integer, BinaryTree> nodesToParent) {
        BinaryTree parent = nodesToParent.get(target);
        if (parent == null) {
            return tree;
        }
        if (parent.left != null && parent.left.value == target) {
            return parent.left;
        }
        return parent.right;
    }

    public void populateNodesToParents(
            BinaryTree node, Map<Integer, BinaryTree> nodesToParents, BinaryTree parent) {
        if (node != null) {
            nodesToParents.put(node.value, parent);
            populateNodesToParents(node.left, nodesToParents, node);
            populateNodesToParents(node.right, nodesToParents, node);
        }
    }


}
