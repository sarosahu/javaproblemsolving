package com.algo.ae.searching;

/**
 * Binary Search
 *
 * Write a function that takes in a sorted array of integers as well as
 * a target integer. The function should use the Binary search algorithm
 * to determine if the target integer is contained in the array and
 * should return its index, otherwise -1.
 *
 * Sample input: [0, 1, 21, 33, 45, 45, 61, 71, 72, 73]
 * target: 33
 */
public class BinarySearch {
    //Time: O(log(N)), Space O(1)
    public static int binarySearchL(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target < array[mid]) {
                right = mid - 1;
            } else if (target > array[mid]) {
                left = mid + 1;
            } else {
                // Found the target
                return mid;
            }
        }
        return -1;
    }

    // Time: O(log(N)), Space O(log(N))
    public static int binarySearchR(int[] array, int target) {
        return binarySearchHelper(array, target, 0, array.length - 1);
    }

    public static int binarySearchHelper(int [] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left)/2;
        if (target < array[mid]) {
            return binarySearchHelper(array, target, left, mid - 1);
        } else if (target > array[mid]) {
            return binarySearchHelper(array, target, mid + 1, right);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 21, 33, 45, 45, 61, 71, 72, 73};
        int index = binarySearchL(array, 33);
        System.out.println("Target index : " + index);
        index = binarySearchR(array, 33);
        System.out.println("Target index2: " + index);

        index = binarySearchL(array, 34);
        System.out.println("Target index : " + index);
        index = binarySearchR(array, 34);
        System.out.println("Target index2: " + index);

    }
}
