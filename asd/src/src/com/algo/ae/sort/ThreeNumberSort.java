package com.algo.ae.sort;

/**
 * Three Number Sort
 *
 * You're given an array of integers and another array of 3 distinct integers.
 * The first array is guranteed to only contain integers that are in the
 * second array, and the second array represents a desired order for the
 * integers in the first array. For example, a second array of [x, y, z]
 * represents a desired order of [x,x,...,x,y,y,...,y,z,z,...,z] in the
 * first array.
 *
 * Write a function that sorts the first array according to the desired
 * order in the second array.
 *
 * The function should permit this in place (i.e., it should mutate the
 * input array), and it shouldn't use any auxiliary space (i.e. it should
 * run with constant space: O(1) space).
 *
 * Note that the desired order won't necessarily be ascending or descending
 * and that the first array won't necessarily contain all 3 integers found
 * in the second array - it might only contain one or two.
 *
 * Sample input:
 * array = [1, 0, 0, -1, -1, 0, 1, 1]
 * order = [0, 1, -1]
 *
 * Sample output: [0, 0, 0, 1, 1, 1, -1, -1]
 */
public class ThreeNumberSort {
    public int[] threeNumberSort(int[] array, int[] order) {
        // Write your code here.
        int iter = 0, left = 0, right = array.length - 1;
        while (iter <= right) {
            int currNum = array[iter];
            if (currNum == order[0]) {
                swap(array, left, iter);
                ++iter;
                ++left;
            } else if (currNum == order[2]) {
                swap(array, iter, right);
                --right;
            } else {
                ++iter;
            }
        }
        return array;
    }

    public void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
