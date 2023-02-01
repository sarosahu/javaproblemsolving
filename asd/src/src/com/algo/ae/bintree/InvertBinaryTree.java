package com.algo.ae.bintree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Invert Binary Tree
 *
 * Write a function that takes in a Binary Tree and inverts it. In other words,
 * the function should swap every left node in the tree for its corresponding
 * right node.
 *
 * Each BinaryTree node has an integer value, a left child node and a right
 * child node. Children nodes can either be BinaryTree nodes themselves or
 * None/null.
 *
 * Sample Input:
 *
 * tree =       1
 *            /   \
 *           2     3
 *         /   \   | \
 *        4     5  6  7
 *       /  \
 *      8    9
 *
 *      Sample Output:
 * tree =       1
 *            /   \
 *           3     2
 *         /   \   | \
 *        7     6  5  4
 *                   /  \
 *                  9    8
 **/
public class InvertBinaryTree {

    // Time: O(N), Space: O(d), d is depth of binary tree.
    public static void invertBinaryTree(BinaryTree tree) {
        // Write your code here.
        if (tree == null) {
            return;
        }
        // swap it's left and right nodes
        BinaryTree leftNode = tree.left;
        tree.left = tree.right;
        tree.right = leftNode;

        invertBinaryTree(tree.left);
        invertBinaryTree(tree.right);
    }

    // Time: O(N), Space: O(N)
    public static void invertBinaryTree2(BinaryTree tree) {
        // Write your code here.
        if (tree == null) {
            return;
        }
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size != 0) {
                BinaryTree currNode = queue.poll();
                --size;
                // swap the left and right node
                BinaryTree leftNode = currNode.left;
                currNode.left = currNode.right;
                currNode.right = leftNode;
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
        }

    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
