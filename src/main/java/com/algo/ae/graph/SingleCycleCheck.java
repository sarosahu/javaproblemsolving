package com.algo.ae.graph;

import java.util.Arrays;

/**
 * You are given an array of integers where each integer represents
 * a jump of its value in the array. For instance 2 represents a
 * jump of 2 indices forward in the array; the integer -3 represents
 * a jump of 3 indices backward in the array.
 *
 * If a jump spills past the array's bounds, it wraps over to the
 * other side. For instance, a jump of -1 at index 0 brings us to
 * the last index in the array. Similarly, a jump of 1 at the last
 * index in the array brings us to index 0.
 *
 * Write a function that returns a boolean representing whether the
 * jumps in the array form a single cycle. A single cycle occurs if,
 * starting at any index in the array and following the jumps, every
 * element in the array is visited exactly once before landing back
 * on the starting index.
 *
 * Sample Input: array  =  [2, 3, 1, -4, -4, 2]
 *
 * Sample output: true
 */
public class SingleCycleCheck {
    // Time: O(n), space : O(1) -- where n is the length of input array
    public static boolean hasSingleCycle(int[] array) {
        int[] count = new int[array.length];
        Arrays.fill(count, 0);
        //int currIndex = 0;
        int nextIndex = 0;
        int numVisited = 0;
        while (numVisited < array.length) {
            int currIndex = nextIndex;
            count[currIndex] += 1;
            if (count[currIndex] > 1) {
                return false;
            }
            nextIndex = (currIndex + array[currIndex]) % array.length;
            nextIndex = nextIndex < 0 ? nextIndex + array.length : nextIndex;
            ++numVisited;
        }
        return numVisited == array.length && nextIndex == 0;
    }
    public static boolean hasSingleCycleE(int[] array) {
        int numElementsVisited = 0;
        int currIndex = 0;
        while (numElementsVisited < array.length) {
            if (numElementsVisited > 0 && currIndex == 0) {
                return false;
            }
            ++numElementsVisited;
            currIndex = getNextIndex(currIndex, array);
        }
        return currIndex == 0;
    }

    private static int getNextIndex(int index, int [] array) {
        int jump = array[index];
        int nextIndex = (index + jump) % array.length;
        return nextIndex >= 0 ? nextIndex : nextIndex + array.length;
    }

    public static void main(String[] args) {
        int[] array = {2, 3, 1, -4, -4, 2};
        boolean isSingleCycle = hasSingleCycle(array);
        if (isSingleCycle) {
            System.out.println("Array is single cyclic");
        } else {
            System.out.println("Array is not single cyclic");
        }
    }
}
