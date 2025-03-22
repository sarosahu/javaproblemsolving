package com.algo.ctci.arraystring;

import java.util.*;

public class ShortestSupersequence {
    private static class Range {
        private int start;
        private int end;
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    private final int[] array;
    private final int[] elements;
    public ShortestSupersequence(int[] array, int[] elements) {
        this.array = array;
        this.elements = elements;
    }

    public Range shortestSuperSequence() {
        List<Queue<Integer>> locations = getLocationsForElements();
        return shortestClosure(locations);
    }

    private List<Queue<Integer>> getLocationsForElements() {
        List<Queue<Integer>> allLocations = new ArrayList<>();

        Map<Integer, Queue<Integer>> itemLocations = new HashMap<>();
        for (int num: elements) {
            itemLocations.put(num, new LinkedList<>());
        }
        for (int i = 0; i < array.length; ++i) {
            if (itemLocations.containsKey(array[i])) {
                itemLocations.get(array[i]).offer(i);
            }
        }
        allLocations.addAll(itemLocations.values());
        return allLocations;
    }

    static class HeapNode {
        int locationWithinList;
        int listId;
        public HeapNode(int locationWithinList, int listId) {
            this.locationWithinList = locationWithinList;
            this.listId = listId;
        }
    }

    private Range shortestClosure(List<Queue<Integer>> locations) {
        Queue<HeapNode> minHeap = new PriorityQueue<>(locations.size(),
                Comparator.comparingInt(a -> a.locationWithinList));
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < locations.size(); ++i) {
            Queue<Integer> location = locations.get(i);
            int minLocation = location.poll();
            max = Math.max(max, minLocation);
            minHeap.offer(new HeapNode(minLocation, i));
        }
        Range range = new Range(minHeap.peek().locationWithinList, max);

        while (true) {
            HeapNode n = minHeap.poll();
            Queue<Integer> currList = locations.get(n.listId);
            int min = n.locationWithinList;
            if (max - min < range.end - range.start) {
                range.start = min;
                range.end = max;
            }
            if (currList.isEmpty()) {
                break;
            }
            n.locationWithinList = currList.poll();
            minHeap.offer(n);
            max = Math.max(max, n.locationWithinList);
        }
        return range;
    }

    static class ListNode {
        int index;
        int value;
        public ListNode(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    public Range shortestSuperSequenceOptimized() {
        List<ListNode> locations = new LinkedList<>();
        Map<Integer, ListNode> dict = new HashMap<>();
        for (int num : elements) {
            dict.put(num, null);
        }
        Range range = new Range(-1, -1);
        int idx = 0;
        while (idx < array.length) {
            int curr = array[idx];
            if (dict.containsKey(curr)) {
                if (dict.get(curr) != null) {
                    ListNode currNode = dict.get(curr);
                    locations.remove(currNode);
                }
                locations.add(new ListNode(idx, curr));
                dict.put(curr, locations.get(locations.size() - 1));
                //}
                if (locations.size() == elements.length &&
                        ((range.start == -1 && range.end == -1) ||
                                idx - locations.get(0).index < range.end - range.start)) {
                    range.start = locations.get(0).index;
                    range.end = idx;
                }
            }
            ++idx;
        }
        return range;
    }

    public static void main(String[] args) {
        int [] array = {7, 5, 9, 0, 2, 1, 3, 15, 7, 9, 1, 1, 5, 8, 1, 9, 50};
        int [] elements = {1, 5, 9};

        ShortestSupersequence obj = new ShortestSupersequence(array, elements);
        Range range = obj.shortestSuperSequence();
        System.out.println("Range : (" + range.start + ", " + range.end + ")");

        Range range2 = obj.shortestSuperSequenceOptimized();
        System.out.println("Range : (" + range2.start + ", " + range2.end + ")");
    }
}
