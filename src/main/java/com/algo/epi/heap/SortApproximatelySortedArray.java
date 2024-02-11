package com.algo.epi.heap;

import java.util.*;

public class SortApproximatelySortedArray {
    public List<Integer> sortApproximatelyKSortedArray(List<Integer> array, int k) {
        int i = 0;
        Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        while (i < k) {
            minHeap.offer(array.get(i));
            ++i;
        }
        List<Integer> sortedArray = new ArrayList<>();
        while (i < array.size()) {
            sortedArray.add(minHeap.poll());
            minHeap.offer(array.get(i));
            ++i;
        }

        // Sequence is exhausted, iteratively extracts teh remaining elements
        while(!minHeap.isEmpty()) {
            sortedArray.add(minHeap.poll());
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(3, -1, 2, 6, 4, 5, 8);
        SortApproximatelySortedArray obj = new SortApproximatelySortedArray();
        List<Integer> sortedArray = obj.sortApproximatelyKSortedArray(array, 2);
        System.out.println("Sorted array : ");
        for (int num : sortedArray) {
            System.out.printf("%d ", num);
        }
        System.out.println();
    }
}
