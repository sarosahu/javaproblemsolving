package com.algo.ae.bintree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Compare Leaf Traversal
 *
 * Write a function that takes in the root nodes of two Binary Trees and returns a
 * boolean representing whether their leaf traversals are the same.
 *
 * The leaf traversal of a Binary Tree traverses only its leaf nodes from left to
 * right. A leaf node is any node that has no left or right children.
 *
 * For example, the leaf traversal of the following Binary Tree is 1, 3, 2.
 *                      4
 *                    /  \
 *                  1     5
 *                      /   \
 *                    3      2
 * Each BinaryTree node has an integer value, a left child, and a right child node.
 * Children nodes can either be BinaryTree nodes themselves or None/null.
 *
 * Sample Input:
 *
 * tree1 =                  1
 *                        /  \
 *                      2     3
 *                    /  \     \
 *                  4     5     6
 *                      /  \
 *                     7    8
 *
 * tree2 =                  1
 *                        /  \
 *                       2    3
 *                     /  \    \
 *                   4     7    5
 *                            /  \
 *                          8     6
 *
 * Sample Output: true
 */
public class CompareLeafTraversal {
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
    public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        } else {
            if (tree1 == null || tree2 == null) {
                return false;
            }
        }
        List<Integer> leafTraversalList1 = computeLeafTraversals(tree1, new ArrayList<Integer>());
        List<Integer> leafTraversalList2 = computeLeafTraversals(tree2, new ArrayList<Integer>());

        return compareList(leafTraversalList1, leafTraversalList2);
    }

    public List<Integer> computeLeafTraversals(BinaryTree tree, List<Integer> list) {
        if (tree.left == null && tree.right == null) {
            list.add(tree.value);
        }

        if (tree.left != null) {
            computeLeafTraversals(tree.left, list);
        }
        if (tree.right != null) {
            computeLeafTraversals(tree.right, list);
        }
        return list;
    }

    public boolean compareList(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); ++i) {
            if (list1.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    // Time: O(n + m) , space : O(max(h1, h2)) -- n is no of nodes in the first
    // binary tree, m is the number in the second, h1 and h2 are the heights of
    // first and second binary tree.
    public boolean compareLeafTraversalE(BinaryTree tree1, BinaryTree tree2) {
        Stack<BinaryTree> stk1 = new Stack<>();
        stk1.push(tree1);
        Stack<BinaryTree> stk2 = new Stack<>();
        stk2.push(tree2);

        while (stk1.size() > 0 && stk2.size() > 0) {
            BinaryTree leafNode1 = getNextLeafNode(stk1);
            BinaryTree leafNode2 = getNextLeafNode(stk2);

            if (leafNode1.value != leafNode2.value) {
                return false;
            }
        }

        return (stk1.size() == 0 && stk2.size() == 0);
    }

    public BinaryTree getNextLeafNode(Stack<BinaryTree> stack) {
        BinaryTree currNode = stack.pop();

        while (!isLeafNode(currNode)) {
            if (currNode.right != null) {
                stack.push(currNode.right);
            }
            if (currNode.left != null) {
                stack.push(currNode.left);
            }
            currNode = stack.pop();
        }
        return currNode;
    }

    public boolean isLeafNode(BinaryTree node) {
        return (node.left == null) && (node.right == null);
    }

    public static void main(String[] args) {

    }
}
