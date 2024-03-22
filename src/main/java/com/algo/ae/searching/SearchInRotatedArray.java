package com.algo.ae.searching;

/**
 * Search in rotated array.
 *
 * Given a sorted array of n integers that has been rotated an unknown number of times, write
 * code to find an element in the array. You may assume that the array was originally sorted
 * in increasing order.
 */
public class SearchInRotatedArray {
    public int search(int [] a, int x) {
        return search(a, 0, a.length - 1, x);
    }
    private int search(int[] a, int left, int right, int x) {
        if (right < left) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (x == a[mid]) {
            return mid;
        }

        // Either the left or right half must be normally ordered.
        // Find out which side is normally ordered, then use the normally
        // ordered half to figure out which side to find x.
        if (a[left] < a[mid]) { //Left half is normally ordered.
            if (x >= a[left] && x < a[mid]) {
                return search(a, left, mid - 1, x);
            } else {
                // search right
                return search(a, mid + 1, right, x);
            }
        } else if (a[mid] < a[left]) { // Right half is normally ordered
            if (x > a[mid] && x <= a[right]) {
                return search(a, mid + 1, right, x);
            } else {
                // search left
                return search(a, left, mid - 1, x);
            }
        } else { // a[left] == a[mid]
            if (a[mid] != a[right]) {
                // In this case there are duplicate numbers (a[mid]) from
                // left to mid, hence the right half should be searched.
                return search(a, mid + 1, right, x);
            } else {
                // In this case we must search both half!!
                int result = search(a, left, mid - 1, x);
                if (result != -1) {
                    return result = search(a, mid + 1, right, x);
                }
                return result;
            }
        }
        //return -1;
    }


    public static void main(String[] args) {
        int [] a = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        SearchInRotatedArray obj = new SearchInRotatedArray();
        int idx = obj.search(a, 5);
        System.out.println("Searched index : " + idx);
    }
}
