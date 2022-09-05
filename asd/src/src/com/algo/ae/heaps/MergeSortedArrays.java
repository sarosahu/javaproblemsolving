package com.algo.ae.heaps;

import java.util.*;

public class MergeSortedArrays {
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
            resultList.add(currIterator.currValue);
            List<Integer> currArray = arrays.get(currIterator.arrayIdx);
            if (currIterator.idx + 1 < currArray.size()) {
                minHeap.add(new ElementIterator(
                        currIterator.idx + 1, currIterator.arrayIdx, currArray.get(currIterator.idx + 1)
                ));
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
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> l1 = Arrays.asList(1, 5, 9, 21);
        List<Integer> l2 = Arrays.asList(-1, 0);
        List<Integer> l3 = Arrays.asList(-124, 81, 121);
        List<Integer> l4 = Arrays.asList(3, 6, 12, 20, 150);
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        lists.add(l4);

        MergeSortedArrays obj = new MergeSortedArrays();
        List<Integer> result = obj.mergeSortedArrays(lists);
        System.out.println("Result list : ");
        for (int a : result) {
            System.out.printf("%d ", a);
        }
        System.out.println();
    }
}
