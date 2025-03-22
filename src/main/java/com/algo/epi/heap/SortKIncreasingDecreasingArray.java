package com.algo.epi.heap;

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
                
                List<Integer> sortedArray = fetchSortedArray(array, startIdx, i - 1, sortType);
                arrays.add(sortedArray);
                startIdx = i;
                sortType = sortType == SortType.INCREASING ? SortType.DECREASING : SortType.INCREASING;
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
        if (sortType == SortType.DECREASING) {
            Collections.sort(sortedArray);
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

    static class SortedArray {
        private int startIdx;
        private final int endIdx;
        private final SortType sortType;
        private int currMinValIdx;

        public SortedArray(int startIdx, int endIdx, SortType sortType) {
            this.sortType = sortType;
            this.startIdx = startIdx;
            this.endIdx = endIdx;
            this.currMinValIdx = startIdx;
        }
        public int getStartIdx() {
            return this.startIdx;
        }
        public void setStartIdx(int startIdx) {
            this.startIdx = startIdx;
            this.currMinValIdx = startIdx;
        }
        public int getCurrMinValIdx() {
            return this.currMinValIdx;
        }

        public int getNextMinValIdx() {
            if (sortType == SortType.INCREASING) {
                return this.currMinValIdx + 1;
            } else {
                return this.currMinValIdx - 1;
            }
        }

        public boolean isOutOfRange() {
            if (sortType == SortType.INCREASING) {
                return this.startIdx > this.endIdx;
            } else {
                return this.startIdx < this.endIdx;
            }
        }

    }

    public List<Integer> sortKIncreasingDecreasingArray2(List<Integer> array) {
        List<SortedArray> arrays = new ArrayList<>();
        SortType sortType = SortType.INCREASING;
        int startIdx = 0;
        for (int i = 1; i <= array.size(); ++i) {
            if (i == array.size() ||
                    (array.get(i - 1) < array.get(i) && sortType == SortType.DECREASING) ||
                    (array.get(i - 1) >= array.get(i) && sortType == SortType.INCREASING)) {
                SortedArray sortedArray;
                if (sortType == SortType.DECREASING) {
                    sortedArray = new SortedArray(i - 1, startIdx, sortType);
                } else {
                    sortedArray = new SortedArray(startIdx, i - 1, sortType);
                }
                arrays.add(sortedArray);
                startIdx = i;
                sortType = sortType == SortType.INCREASING ? SortType.DECREASING : SortType.INCREASING;
            }

        }
        return mergeSortedArrays2(array, arrays);
    }

    public List<Integer> mergeSortedArrays2(List<Integer> array, List<SortedArray> sortedArrays) {
        Queue<SortedArray> minHeap =
                new PriorityQueue<>(
                        (a, b) -> array.get(a.currMinValIdx) - array.get(b.currMinValIdx));
        for (SortedArray sortedArray : sortedArrays) {
            minHeap.offer(sortedArray);
        }
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            SortedArray currArray = minHeap.poll();
            result.add(array.get(currArray.getCurrMinValIdx()));
            currArray.setStartIdx(currArray.getNextMinValIdx());
            if (!currArray.isOutOfRange()) {
                minHeap.offer(currArray);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(57, 131, 493, 294, 221, 339, 418, 452, 442, 190);
        System.out.println("Original array : ");
        printArray(array);
        SortKIncreasingDecreasingArray obj = new SortKIncreasingDecreasingArray();
        List<Integer> sortedArray = obj.sortKIncreasingDecreasingArray(array);
        System.out.println("Sorted array 1 : ");
        printArray(sortedArray);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("Original array : ");
        printArray(array);
        List<Integer> sortedArray2 = obj.sortKIncreasingDecreasingArray2(array);
        System.out.println("Sorted array 2 : ");
        printArray(sortedArray2);
    }

    public static void printArray(List<Integer> arr) {
        for (int num : arr) {
            System.out.printf("%d ", num);
        }
        System.out.println();
    }

}
