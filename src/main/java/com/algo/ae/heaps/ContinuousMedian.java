package com.algo.ae.heaps;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Continuous Median
 *
 * Write a ContinuousMedianHandler class that supports:
 * - The continuous insertion of numbers with the insert method.
 * - The instant(O(1) time) retrieval of the median of the numbers that have
 *   been inserted thus far with the getMedian method.
 *
 * The getMedian method has already been written for you. You simply have to
 * write the insert method.
 *
 * The median of a set of numbers is the "middle" number when the numbers are
 * ordered from smallest to greatest. If there's an odd number of numbers in
 * the set, as in {1, 3, 7}, the median is the number in the middle(3 in this
 * case); if there's an even number of numbers in the set, as in {1, 3, 7, 8},
 * the median is the average of the 2 middle numbers i.e. (3+7)/2 = 5 here.
 *
 * Sample Usage:
 *
 * // All operations below are performed sequentially.
 * ContinuousMedianHandler(): - Instantiate a ContinuousMedianHandler
 * insert(5): -
 * insert(10): -
 * getMedian(): 7.5
 * insert(100): -
 * getMedian(): 10
 */
public class ContinuousMedian {
    static class ContinuousMedianHandler {
        double median = 0;
        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        public void insert(int number) {
            if (minHeap.isEmpty()) {
                minHeap.add(number);
            } else {
                if (number >= minHeap.peek()) {
                    minHeap.add(number);
                } else {
                    maxHeap.add(number);
                }
            }
            // Balancing the maxHeap and minHeap
            // Ensure minHeap and maxHeap should have equal number of elements
            // if even number of elements are read, otherwise, minHeap should
            // have one more element.
            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.add(minHeap.peek());
                minHeap.remove();
            } else if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.peek());
                maxHeap.remove();
            }

            median = minHeap.size() == maxHeap.size() ?
                    0.5 * (minHeap.peek() + maxHeap.peek()) :
                    minHeap.peek();
        }

        public double getMedian() {
            return median;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {10, 5, 2, 9, 1, 4};
        ContinuousMedianHandler continuousMedianHandler = new ContinuousMedianHandler();
        for (int num : array) {
            continuousMedianHandler.insert(num);
            System.out.println("Median : " + continuousMedianHandler.getMedian());
        }
    }
}
