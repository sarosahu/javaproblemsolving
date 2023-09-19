package com.algo.lc.recursion.recursiontoiteration;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 */
public class SameTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
    }

    /**
     *
     * @param p
     * @param q
     * @return true/false
     *
     * This is recursive approach.
     * Complexity Analysis
     *
     * Time complexity : O(N),
     * where N is a number of nodes in the tree, since one visits
     * each node exactly once.
     *
     * Space complexity : O(N) in the worst case of completely unbalanced tree, to keep a recursion stack.
     */
    public boolean isSameTreeR(TreeNode p, TreeNode q) {
        // p and q are both null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return isSameTreeR(p.right, q.right) &&
                isSameTreeR(p.left, q.left);
    }

    /**
     * Iterative approach.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (!check(p, q)) {
            return false;
        }

        // init deque
        Deque<TreeNode> deqP = new ArrayDeque<>();
        Deque<TreeNode> deqQ = new ArrayDeque<>();
        deqP.addLast(p);
        deqQ.addLast(q);

        while (!deqP.isEmpty()) {
            p = deqP.removeFirst();
            q = deqQ.removeFirst();
            if (!check(p, q)) {
                return false;
            }
            if (p != null) {
                if (!check(p.left, q.left)) {
                    return false;
                }
                if (p.left != null) {
                    deqP.addLast(p.left);
                    deqQ.addLast(q.left);
                }
                if (!check(p.right, q.right)) {
                    return false;
                }
                if (p.right != null) {
                    deqP.addLast(p.right);
                    deqQ.addLast(q.right);
                }
            }
        }
        return true;
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }
        return true;
    }
}
