package com.algo.ae.bst;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Write three functions that take in a Binary Search Tree(BST) and an empty array,
 * traverses the BST, add its nodes' values to the input array and return that
 * array. The three functions should traverse the BST using the in-order,
 * pre-order, and post-order tree-traversal techniques, respectively.
 *
 * Here is the BST node structure:
 * class BST {
 *     public int value;
 *     public BST left;
 *     public BST right;
 *
 *     public BST(int value) {
 *       this.value = value;
 *     }
 * }
 */
public class BstTraversal {

    // Time : O(N), Space: O(h)
    public static List<Integer> inOrderTraverseR(BST tree, List<Integer> array) {
        if (tree == null) {
            return array;
        }
        inOrderTraverseR(tree.left, array);
        array.add(tree.value);
        inOrderTraverseR(tree.right, array);
        return array;
    }

    // Time : O(N), Space: O(h)
    public static List<Integer> preOrderTraverseR(BST tree, List<Integer> array) {
        if (tree == null) {
            return array;
        }
        array.add(tree.value);
        preOrderTraverseR(tree.left, array);
        preOrderTraverseR(tree.right, array);

        return array;
    }

    // Time : O(N), Space: O(h)
    public static List<Integer> postOrderTraverseR(BST tree, List<Integer> array) {
        if (tree == null) {
            return array;
        }
        postOrderTraverseR(tree.left, array);
        postOrderTraverseR(tree.right, array);
        array.add(tree.value);

        return array;
    }

    // Time : O(N), Space: O(h)
    public static List<Integer> inOrderTraverseL(BST tree, List<Integer> array) {
        if (tree == null) {
            return array;
        }
        Stack<BST> stack = new Stack<>();
        BST curr = tree;
        //stack.push(tree);
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.peek();
                stack.pop();
                array.add(curr.value);
                curr = curr.right;
            }
        }
        return array;
    }

    // Time : O(N), Space: O(h)
    public static List<Integer> preOrderTraverseL(BST tree, List<Integer> array) {
        Stack<BST> stack = new Stack<>();
        stack.push(tree);
        while (!stack.isEmpty()) {
            BST curr = stack.peek();
            array.add(curr.value);
            stack.pop();
            if (curr.right != null) {
                stack.push(curr.right);
            }

            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return array;
    }

    // Time : O(N), Space: O(N)
    public static List<Integer> postOrderTraverseL(BST tree, List<Integer> array) {
        Stack<BST> stack = new Stack<>();
        stack.push(tree);
        while (!stack.isEmpty()) {
            BST curr = stack.peek();
            array.add(curr.value);
            stack.pop();
            if (curr.left != null) {
                stack.push(curr.left);
            }

            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        Collections.reverse(array);
        return array;
    }

    // This has same time : O(N) as above, but better space: O(h)
    public static List<Integer> postOrderTraverseLE(BST tree, List<Integer> array) {
        Stack<BST> stack = new Stack<>();
        stack.push(tree);
        BST prev = null;
        while (!stack.isEmpty()) {
            BST curr = stack.peek();
            if (prev == null ||
                    prev.left == curr ||
                    prev.right == curr) {

                // Either we are at root or We came down from top
                if (curr.left != null) {
                    // Traverse left
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    // Left child is null, traverse right
                    stack.push(curr.right);
                } else {
                    // Leaf node, visit now
                    array.add(curr.value);
                    stack.pop();
                }
            } else if (curr.left == prev) {
                // Done with left, traverse right
                if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    // No right child, visit this node.
                    array.add(curr.value);
                    stack.pop();
                }
            } else {
                // Done with left and right child
                // Visit the current node.
                array.add(curr.value);
                stack.pop();
            }
            prev = curr;

        }
        return array;
    }
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
