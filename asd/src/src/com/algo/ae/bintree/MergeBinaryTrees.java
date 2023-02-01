package com.algo.ae.bintree;

/**
 * Merge Binary Trees
 *
 * Given two binary trees, merge them and return the resulting tree.
 * If 2 nodes overlap during the merger then sum the values, otherwise
 * use the existing node.
 *
 * Note that your solution can either mutate the existing trees or
 * return a new tree.
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
        if (tree1 == null && tree2 == null) {
            return null;
        }
        tree1.value += tree2.value;

        tree1.left = mergeBinaryTrees(tree1.left, tree2.left);
        tree1.right = mergeBinaryTrees(tree1.right, tree2.right);

        return tree1;
    }
}
