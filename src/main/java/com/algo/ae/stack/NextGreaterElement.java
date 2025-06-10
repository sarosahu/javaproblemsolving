package com.algo.ae.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Next Greater Element
 *
 * Write a function that takes in an array of integers and returns a new array
 * containing, at each index, the next element in the input array that is greater
 * than the element at that index in the input array.
 *
 * In other words, your function should return a new array where outputArray[i]
 * is the next element in the input array that is greater than inputArray[i].
 * If there is no such next greater element for a particular index, the value
 * at that index in the output array should be -1. For example, given array =
 * [1,2], your function should return [2, -1].
 *
 * Additionally, your function should treat the input array as a circular array.
 * A circular array wraps around itself as if it were connected end-to-end. So
 * the next index after the last index in a circular array is the first index.
 * This means that, for our problem, given array = [0, 0, 5, 0, 0, 3, 0, 0],
 * the next greater element after 3 is 5, since array is circular.
 *
 * Sample Input:
 *  array = [2, 5, -3, -4, 6, 7, 2]
 *
 * Sample Output:
 *  array = [5, 6, 6, 6, 7, -1, 5]
 */
public class NextGreaterElement {
    public int[] nextGreaterElement(int[] array) {
        // Write your code here.
        int[] result = new int[array.length];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();

        for (int idx = 0; idx < 2 * array.length; ++idx) {
            int cIdx = idx % array.length;
            int currNum = array[cIdx];
            while (!stack.isEmpty() && currNum > array[stack.peek()]) {
                int top = stack.pop();
                result[top] = currNum;
            }
            stack.push(cIdx);
        }
        return result;
    }

    public int[] nextGreaterElement2(int[] array) {
        // Write your code here.
        int[] result = new int[array.length];
        Arrays.fill(result, -1);

        Stack<Integer> stack = new Stack<>();

        for (int idx = 0; idx < 2 * array.length; ++idx) {
            int cIdx = idx % array.length;
            int currNum = array[cIdx];
            while (!stack.isEmpty() && currNum > array[stack.peek()]) {
                int top = stack.pop();
                result[top] = currNum;
            }
            stack.push(cIdx);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, -3, -4, 6, 7, 2};
        System.out.println("Original : " + Arrays.toString(arr));
        NextGreaterElement obj = new NextGreaterElement();
        int[] result = obj.nextGreaterElement(arr);
        System.out.println("Result : " + Arrays.toString(result));
    }
}
