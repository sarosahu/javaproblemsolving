package com.algo.ctci.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * BST Sequences: A binary search tree was created by traversing through an array from left to right
 * and inserting each element. Given a binary search tree with distinct elements, print all possible
 * arrays that could have led to this tree.
 *
 *  e.g
 *         2
 *       /  \
 *      1    3
 *
 *  Output: {2, 1, 3}, {2, 3, 1}
 */
public class BstSequences {
    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int data;
        public TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static List<LinkedList<Integer>>
    allSequences(TreeNode root) {
        List<LinkedList<Integer>> result = new ArrayList<>();
        if (root == null) {
            result.add(new LinkedList<>());
            return result;
        }
        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(root.data);

        List<LinkedList<Integer>> leftResult = allSequences(root.left);
        List<LinkedList<Integer>> rightResult = allSequences(root.right);

        for (LinkedList<Integer> leftSequence : leftResult) {
            for (LinkedList<Integer> rightSequence : rightResult) {
                List<LinkedList<Integer>> weavedResult = new ArrayList<>();
                weaveLists(leftSequence, rightSequence, weavedResult, prefix);
                result.addAll(weavedResult);
            }
        }
        return result;
    }

    private static void weaveLists(LinkedList<Integer> first,
                                   LinkedList<Integer> second,
                                   List<LinkedList<Integer>> results,
                                   LinkedList<Integer> prefix) {

        if (first.isEmpty() || second.isEmpty()) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        // Recurse with head of first added to the prefix.
        // Removing the head will damage first, so we'll need to put it back
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        // Do the same thing with second, damaging and then restoring the list
        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10, null, null);
        root.left = new TreeNode(5, null, null);
        root.right = new TreeNode(20, null, null);
        root.left.left = new TreeNode(2, null, null);
        root.left.right = new TreeNode(8, null, null);
        root.right.left = new TreeNode(15, null, null);
        root.right.right = new TreeNode(30, null, null);

        List<LinkedList<Integer>> result = allSequences(root);
        System.out.println("Size : " + result.size());
        for (LinkedList<Integer> seq : result) {
            System.out.println(seq.toString());
        }
    }
}
