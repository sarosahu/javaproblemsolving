package com.algo.ae.bst;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Reconstruct BST
 *
 * The pre-order traversal of a Binary Tree is a traversal technique that starts at the
 * tree's root node and visits nodes in the following order:
 * 1. Current node
 * 2. Left subtree
 * 3. Right subtree
 *
 * Given a non-empty array of integers representing the pre-order traversal of a
 * Binary Search Tree(BST), write a function that creates the relevant BST and
 * returns its root node.
 *
 * The input array will contain the values of BST nodes in the order in which these
 * nodes would be visited with a pre-order traversal.
 *
 * Each BST node has an integer value, a left child node and a right child
 * node. A node is said to be a valid BST node, if and only if it satisfies the
 * BST  property: its value is strictly greater than the values of every node
 * to its left; its value is less than or equal to the values of every node to
 * its right; and its children nodes are either valid BST nodes themselves or
 * None / null.
 *
 * Sample Input:
 * {
 *   "preOrderTraversalValues": [10, 4, 2, 1, 5, 17, 19, 18]
 * }
 *
 * Sample Output:
 *                                            10
 *                                          /   \
 *                                         4    17
 *                                       /  \     \
 *                                      2    5     19
 *                                    /           /
 *                                   1          18
 */
public class ReconstructBst {
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    // Brute force approach
    // Time : O(N^2), space : O(N)
    public BST reconstructBstBF(ArrayList<Integer> preOrderTraversalValues) {
        // Write your code here.
        if (preOrderTraversalValues.isEmpty()) {
            return null;
        }
        return reconstructBstHelper(preOrderTraversalValues, 0, 1, preOrderTraversalValues.size() - 1);
    }

    public BST reconstructBstHelper(ArrayList<Integer> preOrder, int parentIndex, int fromIndex, int toIndex) {
        BST root = new BST(preOrder.get(parentIndex));
        int[] childIndices = getChildIndices(preOrder, root.value, fromIndex, toIndex);
        if (childIndices[0] != -1) {
            root.left = reconstructBstHelper(preOrder, childIndices[0], childIndices[0] + 1,
                    (childIndices[1] == -1 ? toIndex : childIndices[1] - 1));
        }
        if (childIndices[1] != -1) {
            root.right = reconstructBstHelper(preOrder, childIndices[1], childIndices[1] + 1, toIndex);
        }
        return root;
    }

    public int[] getChildIndices(ArrayList<Integer> preOrder, int parentVal, int fromIndex, int toIndex) {
        int[] childIndices = {-1, -1};
        for (int i = fromIndex; i <= toIndex && (childIndices[0] == -1 || childIndices[1] == -1); ++i) {
            int currVal = preOrder.get(i);
            if (currVal < parentVal && childIndices[0] == -1) {
                childIndices[0] = i;
            }
            if (currVal >= parentVal && childIndices[1] == -1) {
                childIndices[1] = i;
            }
        }
        return childIndices;
    }

    // Loop approach by using stack
    // Time : O(N^2), Space O(h)
    public BST reconstructBst(ArrayList<Integer> preOrderTraversalValues) {
        BST root = new BST(preOrderTraversalValues.get(0));
        Stack<BST> stack = new Stack<>();
        stack.push(root);

        for (int i = 1; i < preOrderTraversalValues.size(); ++i) {
            int currItem = preOrderTraversalValues.get(i);
            BST nextChildNode = new BST(currItem);
            if (currItem < stack.peek().value) {
                BST currNode = stack.peek();
                currNode.left = nextChildNode;
                stack.push(currNode.left);
            } else {
                BST parentNode = null;
                while (!stack.isEmpty() && stack.peek().value <= currItem) {
                    parentNode = stack.pop();
                }
                parentNode.right = nextChildNode;
                stack.push(parentNode.right);
            }
        }

        return root;
    }

    // { Time : O(N), Space : O(N)
    private int rootIndex = 0;
    public BST reconstructBstE(ArrayList<Integer> preOrderTraversalValues) {
        // Write your code here.
        int rootIdx = 0;
        return reconstructBstEHelper(preOrderTraversalValues, rootIdx,
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public BST reconstructBstEHelper(ArrayList<Integer> preOrder,
                                    int rootIdx,
                                    int lowerBound,
                                    int upperBound) {

        if (rootIdx == preOrder.size()) {
            return null;
        }
        int root = preOrder.get(rootIdx);
        if (root < lowerBound || root >= upperBound) {
            return null;
        }

        BST rootNode = new BST(root);
        this.rootIndex = rootIdx + 1;
        rootNode.left = reconstructBstHelper(preOrder, this.rootIndex, lowerBound, root);
        rootNode.right = reconstructBstHelper(preOrder, this.rootIndex, root, upperBound);

        return rootNode;
    }
    // }
}
