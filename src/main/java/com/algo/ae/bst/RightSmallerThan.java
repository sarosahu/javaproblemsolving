package com.algo.ae.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.algoexpert.io/questions/right-smaller-than
 * Right Smaller Than
 * Write a function that takes in an array of integers and return an
 * array of the same length, where each element in the output array corresponds
 * to the number of integers in the input array that are to the right of
 * the relevant index and that are strictly smaller than the integer at that index.
 *
 * In other words, the value at output[i] represents the number of integers that
 * are to the right of i and that are strictly smaller than input[i].
 *
 * Sample input : array = {8, 5, 11, -1, 3, 4, 2}
 * Sample output: {5, 4, 4, 0, 1, 1, 0}
 */
public class RightSmallerThan {

    static class BST {
        public int value;
        public int leftSubtreeSize;
        BST left;
        BST right;
        public BST(int value) {
            this.value = value;
            this.leftSubtreeSize = 0;
            this.left = null;
            this.right = null;
        }

        public void insert(int value, int idx, List<Integer> rightSmallerCounts) {
            insertHelper(value, idx, rightSmallerCounts, 0);
        }

        public void insertHelper(int value,
                                 int idx,
                                 List<Integer> rightSmallerCounts,
                                 int numSmallerAtInsertTime) {
            if (value < this.value) {
                ++leftSubtreeSize;
                if (left == null) {
                    left = new BST(value);
                    rightSmallerCounts.set(idx, numSmallerAtInsertTime);
                } else {
                    left.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
                }
            } else {
                numSmallerAtInsertTime += leftSubtreeSize;
                if (value > this.value) {
                    ++numSmallerAtInsertTime;
                }
                if (right == null) {
                    right = new BST(value);
                    rightSmallerCounts.set(idx, numSmallerAtInsertTime);
                } else {
                    right.insertHelper(value, idx, rightSmallerCounts, numSmallerAtInsertTime);
                }
            }
        }
    }

    // Time: Average: when the created BST is balanced, then O(nlog(n)) and O(n) space
    //       Worst: when created BST is like a linked list, then O(n^2), and O(n) space
    public static List<Integer> rightSmallerThan(List<Integer> array) {
        if (array.isEmpty()) {
            return new ArrayList<Integer>();
        }

        List<Integer> rightSmallerCounts = new ArrayList<Integer>(array);
        int lastIdx = array.size() - 1;
        BST bst = new BST(array.get(lastIdx));
        rightSmallerCounts.set(lastIdx, 0);
        for (int i = array.size() - 2; i >= 0; --i) {
            bst.insert(array.get(i), i, rightSmallerCounts);
        }
        return rightSmallerCounts;
    }

    public static void main(String[] args) {
        //int [] a = {8, 5, 11, -1, 3, 4, 2};
        List<Integer> list = Arrays.asList(8, 5, 11, -1, 3, 4, 2);
        List<Integer> result = rightSmallerThan(list);
        System.out.println("Result list : ");
        for (int num : result) {
            System.out.printf("%d ", num);
        }
        System.out.println();
    }
}
