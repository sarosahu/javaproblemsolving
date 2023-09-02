package com.algo.lc.treegraphs;

import com.ds.bintree.BinTreeNode;

import java.util.*;

public class LowestCommonAncestorBinTree {
    static class NodeWithNumTargets {
        int numTargets;
        BinTreeNode<Integer> ancestorNode;
        public NodeWithNumTargets(int n, BinTreeNode<Integer> node) {
            this.numTargets = n;
            this.ancestorNode = node;
        }
    }
    public BinTreeNode<Integer> lowestCommonAncestor(BinTreeNode<Integer> root,
                                                     BinTreeNode<Integer> p,
                                                     BinTreeNode<Integer> q) {
        return lCAHelper(root, p, q).ancestorNode;
    }

    public NodeWithNumTargets
    lCAHelper(BinTreeNode<Integer> root, BinTreeNode<Integer> p, BinTreeNode<Integer> q) {
        if (root == null) {
            return new NodeWithNumTargets(0, null);
        }

        NodeWithNumTargets leftResult = lCAHelper(root.leftNode, p, q);
        if (leftResult.numTargets == 2) {
            return leftResult;
        }
        NodeWithNumTargets rightResult = lCAHelper(root.rightNode, p, q);
        if (rightResult.numTargets == 2) {
            return rightResult;
        }

        int numTargets = leftResult.numTargets + rightResult.numTargets +
                (root == p || root == q ? 1 : 0);
        return new NodeWithNumTargets(numTargets, numTargets == 2 ? root : null);
    }

    // Approach 2: Iterative using parent pointers
    public BinTreeNode<Integer> lowestCommonAncestorI(BinTreeNode<Integer> root,
                                                     BinTreeNode<Integer> p,
                                                     BinTreeNode<Integer> q) {
        // Stack for tree traversal
        Deque<BinTreeNode<Integer>> stack = new ArrayDeque<>();

        // HashMap for parent pointers
        Map<BinTreeNode<Integer>, BinTreeNode<Integer>> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            BinTreeNode<Integer> curr = stack.pop();

            // While traversing the tree, keep saving the parent pointers
            if (curr.leftNode != null) {
                parent.put(curr.leftNode, curr);
                stack.push(curr.leftNode);
            }
            if (curr.rightNode != null) {
                parent.put(curr.rightNode, curr);
                stack.push(curr.rightNode);
            }
        }
        Set<BinTreeNode<Integer>> ancestors = new HashSet<>();

        // Process all the ancestors  for node p using parent pointer.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }

        return q;
    }

    public static void main(String[] args) {

    }
}
