package com.algo.epi.search;

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

    /*public static int searchLargest(int[] arr) {
        int l = 0, r = arr.length - 1;
        int largest = -1;
        while (l <= r) {
            int mid = l + (r - l)/2;
            if (arr[mid] < arr[l]) {
                r = mid - 1;
            } else { // arr[mid] >= arr[l]
                //largest = mid;
                l = mid;
                if (r - l == 1) {
                    r = arr[r] > arr[l] ? r : l;
                    break;
                }
            }
        }
        return r;
    }*/

    public static int searchLargest(int[] arr) {
        int l = 0, r = arr.length - 1;
        //int largest = -1;
        while (l < r) {
            int mid = l + (r - l)/2;
            if (arr[mid] < arr[l]) {
                r = mid - 1;
            } else { // arr[mid] >= arr[l]
                //largest = mid;
                l = mid;
                if (r - l == 1) {
                    r = arr[r] > arr[l] ? r : l;
                    break;
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
        //int[] array = {38, 40, 50, 60, 10, 20, 25, 28, 30, 35, 37};
        //int[] array = {60, 70, 75, 10, 20};
        int[] array = {55, 10, 20, 30, 40, 50};
        //int smallest = searchSmallest(array);
        int largest = searchLargest(array);
        //System.out.println("Smallest element : " + array[smallest]);
        System.out.println("Largest element : " + array[largest]);

        /*
        int[] array2 = {38, 40, 50, 60, 70, 80, 20, 28, 30, 35, 37};
        smallest = searchSmallest(array2);
        System.out.println("Smallest element : " + array2[smallest]);
        System.out.println("Largest element : " + array2[largest]);*/
    }
}
