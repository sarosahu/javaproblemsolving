package com.algo.ae.bintree;

import java.util.function.Function;

/**
 * Iterative In-Order Traversal
 *
 * Write a function that takes in a Binary Tree (where nodes have an additional pointer
 * to their parent node) and traverses it iteratively using the in-order tree traversal
 * technique; the traversal should specifically not use recursion. As the tree is being
 * traversed, a callback function passed in as an argument to the main function should
 * be called on each node (i.e., callback(currentNode)).
 *
 * Each BinaryTree node has an integer value, a parent node, a left child node, and a
 * right child node. Children nodes can either be BinaryTree nodes themselves or None/null.
 *
 * Sample Input:
 * tree =               1
 *                    /  \
 *                  2     3
 *                /      /  \
 *              4       6    7
 *               \
 *                9
 *
 * Sample Output:
 * // The input callback will have been called in the following order:
 * callback(4)
 * callback(9)
 * callback(2)
 * callback(1)
 * callback(6)
 * callback(3)
 * callback(7)
 *
 */
public class IterativeInOrderTraversal {
    // Time : O(n) , space: O(1)
    public static void iterativeInOrderTraversal(
            BinaryTree tree, Function<BinaryTree, Void> callback) {
        // THis is same as solution given.
        BinaryTree prev = null, curr = tree;

        while (curr != null) {
            BinaryTree next = null;
            if (curr.parent == prev) {
                // We came down to curr from parent
                if (curr.left != null) {
                    next = curr.left;
                } else {
                    callback.apply(curr);
                    // Done with the left, now let's go to right if right is not null
                    // else go up
                    next = curr.right != null ? curr.right : curr.parent;
                }
            } else if (curr.left == prev) {
                // We came up from it's left child
                callback.apply(curr);
                next = curr.right != null ? curr.right : curr.parent;
            } else {
                // Done with both children, so move up.
                next = curr.parent;
            }

            prev = curr;
            curr = next;
        }
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;
        public BinaryTree parent;

        public BinaryTree(int value) {
            this.value = value;
        }

        public BinaryTree(int value, BinaryTree parent) {
            this.value = value;
            this.parent = parent;
        }
    }
}
