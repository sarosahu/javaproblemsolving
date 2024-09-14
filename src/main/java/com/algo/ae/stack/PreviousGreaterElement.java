package com.algo.ae.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * /**
 *  * Previous Greater Element
 *  *
 *  * Write a function that takes in an array of integers and returns a new array
 *  * containing, at each index, the previous element in the input array that is greater
 *  * than the element at that index in the input array.
 *  *
 *  * In other words, your function should return a new array where outputArray[i]
 *  * is the previous element in the input array that is greater than inputArray[i].
 *  * If there is no such next greater element for a particular index, the index value
 *  * at that index in the output array should be -1. For example, given array =
 *  * [2, 1], your function should return [-1, 0].
 *  *
 *  *
 *  * Sample Input:
 *  *  array = [13, 8, 1, 5, 2, 5, 9, 7, 6, 12]
 *  *
 *  * Sample Output:
 *  *  array = [-1, 0, 1, 1, 3, 1, 0, 6, 7, 0]
 *  */

public class PreviousGreaterElement {
    public int[] prevGreaterElement(int[] array) {
        // Write your code here.
        int[] result = new int[array.length];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();

        for (int idx = 0; idx < array.length; ++idx) {
            int currItem = array[idx];
            while (!stack.isEmpty() && currItem >= array[stack.peek()]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[idx] = stack.peek();
            }
            stack.push(idx);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {13, 8, 1, 5, 2, 5, 9, 7, 6, 12};
        PreviousGreaterElement obj = new PreviousGreaterElement();
        int[] result = obj.prevGreaterElement(arr);
        System.out.println("Result array of indexes: " + Arrays.toString(result));
    }
}
