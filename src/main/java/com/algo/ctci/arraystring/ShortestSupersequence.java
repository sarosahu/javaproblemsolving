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
        //return shortestClosure(locations);
        return shortestClosure2(locations);
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
        int loc;
        int listId;
        public HeapNode(int loc, int listId) {
            this.loc = loc;
            this.listId = listId;
        }
    }

    private Range shortestClosure(List<Queue<Integer>> locations) {
        Queue<HeapNode> minHeap = new PriorityQueue<>(locations.size(), Comparator.comparingInt(a -> a.loc));
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < locations.size(); ++i) {
            Queue<Integer> location = locations.get(i);
            int currIdx = location.poll();
            max = Math.max(max, currIdx);
            minHeap.offer(new HeapNode(currIdx, i));
        }
        Range range = new Range(minHeap.peek().loc, max);

        while (true) {
            HeapNode n = minHeap.poll();
            Queue<Integer> currList = locations.get(n.listId);
            int currMinIdx = n.loc;
            if (max - currMinIdx < range.end - range.start) {
                range.start = currMinIdx;
                range.end = max;
            }
            if (currList.isEmpty()) {
                break;
            }
            n.loc = currList.poll();
            minHeap.offer(n);
            max = Math.max(max, n.loc);
        }
        return range;
    }

    // This function looks better than above one
    // Note: The above function looks to be little confusing to me, however nothing wrong.
    private Range shortestClosure2(List<Queue<Integer>> locations) {
        Queue<HeapNode> minHeap = new PriorityQueue<>(locations.size(), Comparator.comparingInt(a -> a.loc));
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < locations.size(); ++i) {
            Queue<Integer> location = locations.get(i);
            int currIdx = location.peek();
            max = Math.max(max, currIdx);
            minHeap.offer(new HeapNode(currIdx, i));
        }
        if (minHeap.isEmpty()) {
            return new Range(-1, -1);
        }
        int min = minHeap.peek().loc;
        Range range = new Range(min, max);

        while (!minHeap.isEmpty()) {
            HeapNode currNode = minHeap.poll();
            //Queue<Integer> currList = locations.get(currNode.listId);
            int currMinIdx = currNode.loc;
            if (max - currMinIdx < range.end - range.start) {
                range.start = currMinIdx;
                range.end = max;
            }
            Queue<Integer> currList = locations.get(currNode.listId);
            if (currList.isEmpty()) {
                break;
            }
            currNode.loc = currList.poll();
            minHeap.offer(currNode);
            max = Math.max(max, currNode.loc);
        }
        return range;
    }

    static class LocNode {
        int index;
        int value;
        LocNode prev;
        LocNode next;
        public LocNode(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    static class Node {
        int index;
        int value;
        Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public Range shortestSuperSequenceOptimized() {
        List<Node> locations = new LinkedList<>();
        Map<Integer, Node> dict = new HashMap<>();
        for (int num : elements) {
            dict.put(num, null);
        }
        Range range = new Range(-1, -1);
        int idx = 0;
        while (idx < array.length) {
            int curr = array[idx];
            if (dict.containsKey(curr)) {
                if (dict.get(curr) != null) {
                    Node currNode = dict.get(curr);
                    locations.remove(currNode);
                }
                locations.add(new Node(idx, curr));
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

    // Doubly linked list approach.
    static class DList {
        LocNode head;
        LocNode tail;
        int size;

        void addLast(LocNode node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            ++size;
        }

        void remove(LocNode node) {
            if (head == node) {
                head = node.next;
            } else {
                node.prev.next = node.next;
            }
            if (tail == node) {
                tail = node.prev;
            } else {
                node.next.prev = node.prev;
            }
            --size;
        }

        int size() {
            return size;
        }
        LocNode getTail() {
            return tail;
        }
        LocNode getHead() {
            return head;
        }
    }

    public Range shortestSuperSequenceOptimized2() {
        DList locations = new DList();
        Map<Integer, LocNode> dict = new HashMap<>();
        for (int num : elements) {
            dict.put(num, null);
        }
        Range range = new Range(-1, -1);
        int idx = 0;
        while (idx < array.length) {
            int curr = array[idx];
            if (dict.containsKey(curr)) {
                if (dict.get(curr) != null) {
                    LocNode currNode = dict.get(curr);
                    locations.remove(currNode);
                }
                locations.addLast(new LocNode(idx, curr));
                dict.put(curr, locations.getTail());

                if (locations.size() == elements.length &&
                        ((range.start == -1 && range.end == -1) ||
                                idx - locations.getHead().index < range.end - range.start)) {
                    range.start = locations.getHead().index;
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

        Range range3 = obj.shortestSuperSequenceOptimized2();
        System.out.println("Range : (" + range3.start + ", " + range3.end + ")");
    }
}
