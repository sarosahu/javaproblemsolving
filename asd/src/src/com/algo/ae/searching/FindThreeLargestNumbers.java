package com.algo.ae.searching;

public class FindThreeLargestNumbers {
    public static int[] findThreeLargestNumbers(int[] array) {
        // Write your code here.
        int thirdLargest = Integer.MIN_VALUE;

        int firstLargest = array[0] > array[1] ? array[0] : array[1];
        int secondLargest = firstLargest == array[0] ? array[1] : array[0];

        for (int i = 2; i < array.length; ++i) {
            if (array[i] > firstLargest) {
                thirdLargest = secondLargest;
                secondLargest = firstLargest;
                firstLargest = array[i];
            } else if (array[i] > secondLargest) {
                thirdLargest = secondLargest;
                secondLargest = array[i];
            } else if (array[i] > thirdLargest) {
                thirdLargest = array[i];
            }
        }
        return new int[] {thirdLargest, secondLargest, firstLargest};
    }

    public static void main(String[] args) {
        int[] array = {10, 5, 9, 10, 12};
        int[] threeLargestNumbers = findThreeLargestNumbers(array);
        for (int num : threeLargestNumbers) {
            System.out.printf("%d ", num);
        }
        System.out.println();
    }
}
