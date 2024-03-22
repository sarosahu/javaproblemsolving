package com.algo.epi.search;

import java.util.Arrays;

/**
 * 12.4 Search a cyclically sorted array.
 *
 * Design an O(log(n)) algorithm for finding the position of the smallest
 * element in a cyclically sorted array. Assume all elements are distinct.
 *
 * e.g.
 * array = [378, 478, 550, 631, 103, 203, 220, 234, 279, 368]
 *
 * output: 4
 */
public class SearchSmallestInCyclicallySortedArray {
    public static int searchSmallest(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = l + (r - l)/2;
            if (arr[mid] > arr[r]) {
                l = mid + 1;
            } else { // arr[mid] <= arr[r]
                r = mid;
            }
        }
        return l;
    }

    public static int searchLargest(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            if (arr[r] > arr[l]) {
                break;
            }
            if (r - l == 1) {
                r = arr[r] > arr[l] ? r : l;
                break;
            }
            int mid = l + (r - l)/2;
            if (arr[mid] > arr[l]) {
                l = mid;
            } else { // arr[mid] < arr[l]
                r = mid - 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        int [] array = {378, 478, 550, 631, 103, 203, 220, 234, 279, 368};
        System.out.println(Arrays.toString(array));
        int smallest = searchSmallest(array);
        System.out.println("Smallest element : " + array[smallest]);
        int largest = searchLargest(array);
        System.out.println("Largest element : " + array[largest]);

        System.out.println("---------------------");

        int[] array2 = {38, 40, 50, 60, 70, 80, 20, 28, 30, 35, 37};
        System.out.println(Arrays.toString(array2));
        smallest = searchSmallest(array2);
        System.out.println("Smallest element : " + array2[smallest]);
        largest = searchLargest(array2);
        System.out.println("Largest element : " + array2[largest]);
    }
}
