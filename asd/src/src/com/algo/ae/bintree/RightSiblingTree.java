package com.algo.ae.bintree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Right Sibling Tree
 *
 * Write a function that takes in a Binary Tree, transforms it into a
 * Right Sibling Tree, and returns its root.
 *
 * A right sibling tree is obtained by making every node in a Binary Tree
 * have its right property point to its right sibling instead of its right
 * child. A node's right sibling is the node immediately to its right on
 * the same level or None/null if there is no node immediately to its right.
 *
 * Note that once transformation is complete, some nodes might no longer
 * have a node pointing to them. For example, in the sample output below,
 * the node with value 10 no longer has any inbound pointers and is
 * effectively unreachable.
 *
 * The transformation should be done in place, meaning that the original
 * data structure should be mutated (no new structure should be created).
 *
 * Each BinaryTree node has an integer "value", a "left" child node, and
 * a "right" child node. Children nodes can either be BinaryTree nodes
 * themselves or None/null.
 *
 * Sample input:
 * tree =      1
 *        /        \
 *       2          3
 *      / \         / \
 *     4   5       6   7
 *    / \   \     /   / \
 *   8   9   10  11  12  13
 *              /
 *             14
 *
 *   Sample output:
 *
 *          1
 *         /
 *        2------------3
 *       /            /
 *      4----5-------6------7
 *     /            /      /
 *    8---9   10--11     12--13
 *               /
 *             14
 */
public class RightSiblingTree {
    static class BinaryTree {
        int value;
        BinaryTree left = null;
        BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
    public static BinaryTree rightSiblingTree(BinaryTree root) {
        if (root == null) {
            return root;
        }
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int currLevelSize = queue.size();
            BinaryTree prev = null;

            for (int i = 0; i < currLevelSize; ++i) {
                BinaryTree curr = queue.poll();
                if (curr == null) {
                    prev = null;
                    continue;
                }
                if (prev != null) {
                    prev.right = curr;
                }
                if (curr.left != null || curr.right != null) {
                    queue.add(curr.left);
                    queue.add(curr.right);
                    curr.right = null;
                }
                prev = curr;
            }

        }
        return root;
    }

    public static BinaryTree rightSiblingTreeR(BinaryTree root) {
        mutate(root, null, false);
        return root;
    }

    public static void mutate(BinaryTree node,
                              BinaryTree parent,
                              boolean isLeftChild) {
        if (node == null) {
            return;
        }

        var left = node.left;
        var right = node.right;
        mutate(left, node, true);
        if (parent == null) {
            node.right = null;
        } else if (isLeftChild) {
            node.right = parent.right;
        } else {
            if (parent.right == null) {
                node.right = null;
            } else {
                node.right = parent.right.left;
            }
        }
        mutate(right, node, false);
    }
    public static void main(String[] args) {

    }
}
