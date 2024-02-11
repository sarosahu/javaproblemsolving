package com.algo.epi.heap;

import com.algo.ae.heaps.MergeSortedArrays;

import java.util.*;

/**
 *
 */
public class SortKIncreasingDecreasingArray {
    enum SortType {
        INCREASING,
        DECREASING
    };

    public List<Integer> sortKIncreasingDecreasingArray(List<Integer> array) {
        List<List<Integer>> arrays = new ArrayList<>();
        SortType sortType = SortType.INCREASING;
        int startIdx = 0;
        for (int i = 1; i <= array.size(); ++i) {
            if (i == array.size() ||
                (array.get(i - 1) < array.get(i) && sortType == SortType.DECREASING) ||
                (array.get(i - 1) >= array.get(i) && sortType == SortType.INCREASING)) {
                if (sortType == SortType.DECREASING) {
                    List<Integer> sortedArray = fetchSortedArray(array, startIdx, i - 1, SortType.DECREASING);
                    arrays.add(sortedArray);
                } else {
                    List<Integer> sortedArray = fetchSortedArray(array, startIdx, i - 1, SortType.INCREASING);
                    arrays.add(sortedArray);
                }
                startIdx = i;
            }

        }
        return mergeSortedArrays(arrays);
    }

    private List<Integer> fetchSortedArray(List<Integer> array, int startIdx, int endIdx, SortType sortType) {
        List<Integer> sortedArray = new ArrayList<>();
        int i = startIdx;
        while (i <= endIdx) {
            sortedArray.add(array.get(i));
            ++i;
        }
        return sortedArray;
    }


    public List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
        Queue<ElementIterator> minHeap =
                new PriorityQueue<>((a, b) -> a.currValue - b.currValue);
        for (int i = 0; i < arrays.size(); ++i) {
            List<Integer> currArray = arrays.get(i);
            ElementIterator iterator =
                    new ElementIterator(0, i, currArray.get(0));
            minHeap.offer(iterator);
        }
        List<Integer> resultList = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ElementIterator currIterator = minHeap.poll();
            //resultList.add(currIterator.currValue);
            List<Integer> currArray = arrays.get(currIterator.arrayIdx);
            resultList.add(currArray.get(currIterator.idx));
            if (currIterator.idx < currArray.size() - 1) {
                currIterator.idx += 1;
                currIterator.currValue = currArray.get(currIterator.idx);
                minHeap.offer(currIterator);
            }
        }
        return resultList;
    }

    static class ElementIterator {
        int idx;
        int arrayIdx;
        int currValue;
        public ElementIterator(int idx, int arrayIdx, int currValue) {
            this.idx = idx;
            this.arrayIdx = arrayIdx;
            this.currValue = currValue;
        }
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(57, 131, 493, 294, 221, 339, 418, 452, 442, 190);
        SortKIncreasingDecreasingArray obj = new SortKIncreasingDecreasingArray();
        List<Integer> sortedArray = obj.sortKIncreasingDecreasingArray(array);
        System.out.println("Sorted array : ");
        for (int num : sortedArray) {
            System.out.printf("%d ", num);
        }
        System.out.println();
    }
}
