package com.algo.ae.bintree;

import java.util.*;

/**
 * Branch Sums
 *
 * Write a function that takes in a Binary Tree and returns a list of its branch sums
 * ordered from leftmost branch sum to rightmost branch sum.
 *
 * A branch sum is the sum of all values in a Binary Tree branch. A Binary Tree branch
 * is a path of nodes in a tree that starts at the root node and ends at any leaf node.
 *
 * Each BinaryTree node has an integer value, a left child node, and a right child node.
 * Children nodes can either be BinaryTree nodes themselves or None/null.
 *
 * Sample Input:
 *
 * tree =                      1
 *                           /   \
 *                         2      3
 *                       /   \   /  \
 *                     4      5 6    7
 *                   /  \   /
 *                  8    9 10
 *
 * Sample Output: [15, 16, 18, 10, 11]
 * // 15 = 1 + 2 + 4 + 8
 * // 16 = 1 + 2 + 4 + 9
 * // 18 = 1 + 2 + 5 +10
 * // 10 = 1 + 3 + 6
 * // 11 = 1 + 3 + 7
 */
public class BranchSums {
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static class NodeWithParentSum {
        public BinaryTree node;
        public int parentSum;

        public NodeWithParentSum(BinaryTree node, int parentSum) {
            this.node = node;
            this.parentSum = parentSum;
        }
    }

    // Queue approach will give the branch sum list
    // But it will not maintain the left-to-right order
    // O(n) time , O(n) space -- n is no of nodes in the Binary Tree
    public static List<Integer> branchSumsL(BinaryTree root) {
        List<Integer> list = new ArrayList<>();
        Queue<NodeWithParentSum> queue = new LinkedList<>();
        queue.add(new NodeWithParentSum(root, 0));
        while (!queue.isEmpty()) {
            NodeWithParentSum currQueueNode = queue.poll();
            BinaryTree currTreeNode = currQueueNode.node;
            int currParentSum = currQueueNode.parentSum;
            int currSum = currParentSum + currTreeNode.value;
            if (currTreeNode.left == null && currTreeNode.right == null) {
                list.add(currSum);
            } else {
                if (currTreeNode.left != null) {
                    queue.add(new NodeWithParentSum(currTreeNode.left, currSum));
                }
                if (currTreeNode.right != null) {
                    queue.add(new NodeWithParentSum(currTreeNode.right, currSum));
                }
            }
        }
        return list;
    }

    // Stack approach
    // O(n) time , O(n) space -- n is no of nodes in the Binary Tree
    public static List<Integer> branchSumsL2(BinaryTree root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<NodeWithParentSum> stack = new Stack<>();
        NodeWithParentSum curr = new NodeWithParentSum(root, 0);
        //stack.push(curr);

        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.node.left == null ? null :
                        new NodeWithParentSum(curr.node.left, curr.parentSum + curr.node.value);
            } else {
                NodeWithParentSum currStackNode = stack.peek();
                stack.pop();
                int sumAtStackNode = currStackNode.parentSum + currStackNode.node.value;

                if (currStackNode.node.right != null) {
                    curr = new NodeWithParentSum(currStackNode.node.right, sumAtStackNode);
                }
                if (currStackNode.node.left == null && currStackNode.node.right == null) {
                    list.add(sumAtStackNode);
                }
            }
        }
        return list;
    }

    // O(n) time , O(n) space -- n is no of nodes in the Binary Tree
    public static List<Integer> branchSumsR(BinaryTree root) {
        // Write your code here.
        ArrayList<Integer> list = new ArrayList<Integer>();
        branchSumsHelper(root, 0, list);
        return list;
    }

    public static void branchSumsHelper(BinaryTree root,
                                        int parentSum,
                                        ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        int currentSum = parentSum + root.value;
        if (root.left == null && root.right == null) {
            list.add(currentSum);
            return;
        }
        branchSumsHelper(root.left, currentSum, list);
        branchSumsHelper(root.right, currentSum, list);
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(1);
        binaryTree.left = new BinaryTree(2);
        binaryTree.right = new BinaryTree(3);
        binaryTree.left.left = new BinaryTree(4);
        binaryTree.left.right = new BinaryTree(5);
        binaryTree.left.right.left = new BinaryTree(10);
        binaryTree.right.left = new BinaryTree(6);
        binaryTree.right.right = new BinaryTree(7);
        binaryTree.left.left.left = new BinaryTree(8);
        binaryTree.left.left.right = new BinaryTree(9);

        List<Integer> list1 = branchSumsR(binaryTree);
        for (int num : list1) {
            System.out.printf("%d ", num);
        }
        System.out.println();
        /*
        List<Integer> list2 = branchSumsL(binaryTree);
        for (int num : list2) {
            System.out.printf("%d ", num);
        }
        System.out.println();
        */

        List<Integer> list3 = branchSumsL2(binaryTree);
        for (int num : list3) {
            System.out.printf("%d ", num);
        }
        System.out.println();
    }
}
