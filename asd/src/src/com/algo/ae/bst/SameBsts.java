package com.algo.ae.bst;

import java.util.List;

/**
 * Same BSTs
 *
 * An array of integers is said to represent the Binary Search Tree (BST) obtained by inserting
 * each integer in the array, from left to right, into the BST.
 *
 * Write function that takes in 2 arrays of integers and determines whether these arrays
 * represent the same BST. Note that you're not allowed to construct any BSTs in your
 * code.
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
 *   "arrayOne": [10, 15, 8, 12, 94, 81, 5, 2, 11],
 *   "arrayTwo": [10, 8, 5, 15, 2, 12, 11, 94, 81]
 * }
 *
 * Sample Output: true
 *
 * Both arrays  represent the BST below:
 *                         10
 *                        /   \
 *                      8      15
 *                    /       /  \
 *                  5       12    94
 *                /        /     /
 *              2        11    81
 */
public class SameBsts {
    // Time: O(n^2), space : O(d) -- n is number of nodes in each array and d is depth of BST
    public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
        return isSameBstsHelper(arrayOne, arrayTwo, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isSameBstsHelper(
            List<Integer> arrayOne,
            List<Integer> arrayTwo,
            int rootIdxOne,
            int rootIdxTwo,
            int minVal,
            int maxVal) {

        if (rootIdxOne == -1 || rootIdxTwo == -1) {
            return rootIdxOne == rootIdxTwo;
        }

        if (arrayOne.get(rootIdxOne).intValue() != arrayTwo.get(rootIdxTwo).intValue()) {
            return false;
        }

        int leftRootIdxOne = getIndexFirstSmaller(arrayOne, rootIdxOne, minVal);
        int leftRootIdxTwo = getIndexFirstSmaller(arrayTwo, rootIdxTwo, minVal);
        int rightRootIdxOne = getIndexFirstBiggerOrEqual(arrayOne, rootIdxOne, maxVal);
        int rightRootIdxTwo = getIndexFirstBiggerOrEqual(arrayTwo, rootIdxTwo, maxVal);

        int currValue = arrayOne.get(rootIdxOne);

        boolean isLeftSame =
                isSameBstsHelper(arrayOne, arrayTwo, leftRootIdxOne, leftRootIdxTwo, minVal, currValue);
        boolean isRightSame =
                isSameBstsHelper(arrayOne, arrayTwo, rightRootIdxOne, rightRootIdxTwo, currValue, maxVal);

        return isLeftSame && isRightSame;
    }

    private static int getIndexFirstSmaller(List<Integer> array, int startIndex, int minValue) {
        for (int i = startIndex + 1; i < array.size(); ++i) {
            if (array.get(i).intValue() < array.get(startIndex).intValue()
                    && array.get(i).intValue() >= minValue) {
                return i;
            }
        }
        return -1;
    }

    private static int getIndexFirstBiggerOrEqual(List<Integer> array, int startIndex, int maxVal) {
        for (int i = startIndex + 1; i < array.size(); ++i) {
            if (array.get(i).intValue() >= array.get(startIndex).intValue()
                    && array.get(i).intValue() < maxVal) {
                return i;
            }
        }
        return -1;
    }
}
