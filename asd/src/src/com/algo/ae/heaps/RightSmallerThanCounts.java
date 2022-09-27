package com.algo.ae.heaps;

import java.util.*;

/**
 * This problem is exactly same as in algo/ae/bst/RightSmallerThan.
 * This can also be solved with using Heap data structure and the
 * time complexity is same as the other solution using BST i.e. O(nlogn)
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
public class RightSmallerThanCounts {
    public static List<Integer> rightSmallerThan(List<Integer> array) {
        // Write your code here.
        if (array == null || array.isEmpty()) {
            return new ArrayList<>();
        }

        // Elements >= current are stored here.
        Queue<Integer> minHeap = new PriorityQueue<>();

        // Elements < current are stored in maxHeap
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        List<Integer> res = new ArrayList<>();
        res.add(0, 0);
        int n = array.size();
        minHeap.add(array.get(n - 1));

        for (int i = n - 2; i >= 0; --i) {
            int curr = array.get(i);

            while (!minHeap.isEmpty() && minHeap.peek() < curr) {
                maxHeap.add(minHeap.poll());
            }

            while (!maxHeap.isEmpty() && maxHeap.peek() >= curr) {
                minHeap.add(maxHeap.poll());
            }
            minHeap.add(curr);
            res.add(0, maxHeap.size());
        }
        return res;
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
