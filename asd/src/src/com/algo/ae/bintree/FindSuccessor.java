package com.algo.ae.bintree;

/**
 * Find Successor
 *
 * Write a function that takes in a Binary Tree(Where nodes have an additional
 * pointer to its parent node) as well as node contained in that tree and
 * returns the given node's successor.
 *
 * A node's successor is the next node to be visited (immediately after the
 * given node) when traversing its tree using the in-order traversal technique.
 * A node has no successor if it's the last node to be visited in the in-order
 * traversal.
 *
 * If a node has no successor, your function should return None / null. Here
 * is the structure of BinaryTree:
 * class BinaryTree {
 *     public int value;
 *     public BinaryTree left = null;
 *     public BinaryTree right = null;
 *     public BinaryTree parent = null;
 *
 *     public BinaryTree(int value) {
 *       this.value = value;
 *     }
 *   }
 */
public class FindSuccessor {
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;
        public BinaryTree parent = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
        // Write your code here.
        BinaryTree successor = null;
        if (node.right != null) {
            successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
        } else {
            successor = node;
            if (successor.parent != null && successor.parent.left == successor) {
                successor = successor.parent;
            } else {
                while (successor != tree && successor.parent.right == successor) {
                    successor = successor.parent;
                }
                successor = successor.parent;
            }
        }
        return successor;
    }

    public BinaryTree findSuccessor2(BinaryTree tree, BinaryTree node) {
        BinaryTree successor = null;
        if (node.right != null) {
            return getLeftMostChild(node.right);
        }
        return getNearestRightParent(node);
    }

    public BinaryTree getLeftMostChild(BinaryTree node) {
        BinaryTree curr = node;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    public BinaryTree getNearestRightParent(BinaryTree node) {
        BinaryTree curr = node;
        while (curr.parent != null && curr.parent.right == curr) {
            curr = curr.parent;
        }
        return curr.parent;
    }
}
