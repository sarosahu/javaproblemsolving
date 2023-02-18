package com.algo.ae.heaps;

import java.util.ArrayList;
import java.util.List;

/**
 * Min Heap Construction
 *
 * Implement a MinHeap class that supports:
 * - Build a Min Heap from an input array of integers.
 * - Inserting integers in the heap.
 * - Removing the heap's minimum / root value.
 * - Peeking at the heap's minimum / root value.
 * - Shifting integers up and down the heap, which is to be used when
 *   inserting and removing values.
 *
 * Note: Heap should be represented in the form of an array.
 *
 */
public class MinHeapConstruction {
    static class MinHeap {
        List<Integer> heap = new ArrayList<Integer>();

        public MinHeap(List<Integer> array) {
            heap = buildHeap(array);
        }

        // Time : O(n), space : O(1)
        public List<Integer> buildHeap(List<Integer> array) {
            // Write your code here.
            int size = array.size();
            int firstParentIndex = (size - 2) / 2;
            for (int currIndex = firstParentIndex; currIndex >= 0; --currIndex) {
                siftDown(currIndex, size - 1, array);
            }
            return array;
        }

        // Time : O(log(n)), space : O(1)
        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            // Write your code here.
            int childOneIndex = currentIdx * 2 + 1;
            while (childOneIndex <= endIdx) {
                int childTwoIndex = currentIdx * 2 + 2 <= endIdx ?
                        currentIdx * 2 + 2 : -1;
                int indexToSwap;
                if (childTwoIndex != -1 && heap.get(childTwoIndex) < heap.get(childOneIndex)) {
                    indexToSwap = childTwoIndex;
                } else {
                    indexToSwap = childOneIndex;
                }
                if (heap.get(indexToSwap) < heap.get(currentIdx)) {
                    swap(currentIdx, indexToSwap, heap);
                    currentIdx = indexToSwap;
                    childOneIndex = currentIdx * 2 + 1;
                } else {
                    return;
                }
            }
        }

        // Time : O(log(n)), space : O(1)
        public void siftUp(int currentIdx, List<Integer> heap) {
            // Write your code here.
            int parentIndex = (currentIdx - 1) / 2;
            while (currentIdx > 0 && heap.get(currentIdx) < heap.get(parentIndex)) {
                swap(currentIdx, parentIndex, heap);
                currentIdx = parentIndex;
                parentIndex = (currentIdx - 1) / 2;
            }
        }

        public int peek() {
            // Write your code here.
            return heap.get(0);
        }

        public int remove() {
            // Write your code here.
            swap(0, heap.size() - 1, heap);
            int valueToRemove = heap.get(heap.size() - 1);
            heap.remove(heap.size() - 1);
            siftDown(0, heap.size() - 1, heap);
            return valueToRemove;
        }

        public void insert(int value) {
            // Write your code here.
            heap.add(value);
            siftUp(heap.size() - 1, heap);
        }

        public void swap(int i , int j, List<Integer> heap) {
            Integer temp = heap.get(j);
            heap.set(j, heap.get(i));
            heap.set(i, temp);
        }
    }
}
