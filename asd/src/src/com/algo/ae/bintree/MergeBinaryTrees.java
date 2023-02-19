package com.algo.ae.bintree;

import java.util.Stack;

/**
 * Merge Binary Trees
 *
 * Given two binary trees, merge them and return the resulting tree.
 * If 2 nodes overlap during the merger then sum the values, otherwise
 * use the existing node.
 *
 * Note that your solution can either mutate the existing trees or
 * return a new tree.
 *
 * Sample Input:
 *
 * tree1 =     1
 *           /  \
 *         3     2
 *       /  \
 *     7     4
 *
 * tree2 =      1
 *            /  \
 *          5     9
 *        /     /  \
 *      2      7    6
 *
 *
 *  Sample Output:
 *
 *              2
 *            /  \
 *          8     11
 *        /  \   /  \
 *      9     4 7    6
 */
public class MergeBinaryTrees {
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    // Time : O(N), Space: O(N)
    public BinaryTree mergeBinaryTrees(BinaryTree tree1, BinaryTree tree2) {
        if (tree1 != null && tree2 != null) {
            return mergeBinaryTreesHelper(tree1, tree2);
        } else if (tree1 == null && tree2 == null) {
            return null;
        }
        return  tree1 == null ? tree2 : tree1;
    }

    public BinaryTree mergeBinaryTreesHelper(BinaryTree tree1, BinaryTree tree2) {
        if (tree1 == null && tree2 == null) {
            return null;
        }
        int newValue = tree1 == null ? 0 : tree1.value;
        newValue += tree2 == null ? 0 : tree2.value;

        BinaryTree newTree = new BinaryTree(newValue);
        newTree.left = mergeBinaryTreesHelper(tree1 == null ? null : tree1.left, tree2 == null ? null : tree2.left);
        newTree.right = mergeBinaryTreesHelper(tree1 == null ? null : tree1.right, tree2 == null ? null : tree2.right);

        return newTree;
    }

    // Time: O(n), space : O(h) -- n is the number of nodes in the smaller of two
    // trees and h is the height of the shorter tree.
    public BinaryTree mergeBinaryTrees2(BinaryTree tree1, BinaryTree tree2) {
        if (tree1 == null) {
            return tree2;
        }
        Stack<BinaryTree> tree1Stack = new Stack<>();
        tree1Stack.push(tree1);
        Stack<BinaryTree> tree2Stack = new Stack<>();
        tree2Stack.push(tree2);

        while (!tree1Stack.isEmpty()) {
            BinaryTree tree1Node = tree1Stack.pop();
            BinaryTree tree2Node = tree2Stack.pop();

            if (tree2Node == null) {
                continue;
            }

            tree1Node.value += tree2Node.value;

            if (tree1Node.left == null) {
                tree1Node.left = tree2Node.left;
            } else {
                tree1Stack.push(tree1Node.left);
                tree2Stack.push(tree2Node.left);
            }

            if (tree1Node.right == null) {
                tree1Node.right = tree2Node.right;
            } else {
                tree1Stack.push(tree1Node.right);
                tree2Stack.push(tree2Node.right);
            }
        }
        return tree1;
    }

    // Time : O(N), Space : O(1)
    // Here we are not using external space, hence O(1)
    // N is the number of nodes in the smaller of 2 trees.
    public BinaryTree mergeBinaryTreesE(BinaryTree tree1, BinaryTree tree2) {

        if (tree1 == null) {
            return tree2;
        }
        if (tree2 == null) {
            return tree1;
        }

        tree1.value += tree2.value;

        tree1.left = mergeBinaryTrees(tree1.left, tree2.left);
        tree1.right = mergeBinaryTrees(tree1.right, tree2.right);

        return tree1;
    }
}
