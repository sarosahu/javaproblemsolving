package com.algo.ae.bintree;

/**
 * Flatten binary tree.
 * Write a function that takes a binary tree, flattens it and returns its leftmost node.
 *
 * A flattened binary tree is a structure that is nearly identical to a doubly linked list
 * (except that nodes have left and right pointers instead of prev and next pointers.),
 * where nodes follow original tree's left to right order.
 *
 * Note that if the input binary tree happens to be a valid binary search tree, the nodes
 * in the flattened tree will be sorted.
 *
 * The flattening should be done in place, meaning the original data structure should be
 * mutated (no new structure should be created).
 * Each BinaryTree node has a value, a left child node and a right child node. Children
 * nodes can either be BinaryTree nodes themselves or None / null.
 */
public class FlattenBinaryTree {
    public static BinaryTree flattenBinaryTree(BinaryTree root) {
        return flattenBinaryTreeHelper(root).leftMost;
    }

    public static LeftAndRightMostPair
    flattenBinaryTreeHelper(BinaryTree node) {
        BinaryTree leftMost;
        BinaryTree rightMost;

        if (node == null) {
            return new LeftAndRightMostPair(null, null);
        }

        // Recursively builds the list from left and right subtrees
        LeftAndRightMostPair leftResult = flattenBinaryTreeHelper(node.left);
        LeftAndRightMostPair rightResult = flattenBinaryTreeHelper(node.right);

        // Appends tree to the list from left subtree
        if (leftResult.rightMost != null) {
            leftResult.rightMost.right = node;
        }
        node.left = leftResult.rightMost;

        // Appends the list from right subtree to tree
        node.right = rightResult.leftMost;
        if (rightResult.leftMost != null) {
            rightResult.leftMost.left = node;
        }

        return new LeftAndRightMostPair((leftResult.leftMost != null ? leftResult.leftMost : node),
                (rightResult.rightMost != null ? rightResult.rightMost : node));
    }
    // This is the class of the input root. Do not edit it.
    static class BinaryTree {
        int value;
        BinaryTree left = null;
        BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    static class LeftAndRightMostPair {
        BinaryTree leftMost;
        BinaryTree rightMost;
        public LeftAndRightMostPair(BinaryTree leftMost, BinaryTree rightMost) {
            this.leftMost = leftMost;
            this.rightMost = rightMost;
        }
    }

    public static void main(String[] args) {
        
    }
}
