package com.algo.ae.bintree;

import java.util.Stack;

/**
 * Symmetrical Tree
 *
 * Write a function that takes in a Binary Tree and returns if that tree
 * is symmetrical. A tree is symmetrical if the left and right subtrees
 * mirror images of each other.
 */
public class SymmetricalTree {
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    // Time : O(N), Space: O(h)
    public boolean symmetricalTreeL(BinaryTree tree) {
        if (tree == null || (tree.left == null && tree.right == null)) {
            return true;
        }
        Stack<BinaryTree> stackLeft = new Stack<>();
        stackLeft.push(tree.left);
        Stack<BinaryTree> stackRight = new Stack<>();
        stackRight.push(tree.right);

        while (!stackLeft.isEmpty() && !stackRight.isEmpty()) {
            BinaryTree left = stackLeft.pop();
            BinaryTree right = stackRight.pop();

            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null || left.value != right.value) {
                return false;
            }

            stackLeft.push(left.left);
            stackRight.push(right.right);

            stackLeft.push(left.right);
            stackRight.push(right.left);
        }

        return stackLeft.isEmpty() && stackRight.isEmpty();
    }

    // TIME: O(N), Space: O(h)
    public boolean symmetricalTreeR(BinaryTree tree) {
        if (tree == null ||
                tree.left == null && tree.right == null) {
            return true;
        }
        if (tree.left == null || tree.right == null) {
            return false;
        }
        return symmetricalTreeHelper(tree.left, tree.right);
    }

    public boolean symmetricalTreeHelper(BinaryTree tree1, BinaryTree tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        if (tree1.value != tree2.value) {
            return false;
        }
        return symmetricalTreeHelper(tree1.left, tree2.right) &&
                symmetricalTreeHelper(tree1.right, tree2.left);
    }
}
