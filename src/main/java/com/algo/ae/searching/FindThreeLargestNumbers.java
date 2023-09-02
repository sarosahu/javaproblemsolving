package com.algo.ae.searching;

/**
 * Write a function that takes in an array of at least 3 integers
 * and without sorting the input array, return a sorted array of
 * 3 largest integers in the input array.
 *
 * The function should return duplicate integers if necessary; for
 * example, it should return [10, 10, 12] for an input array of
 * [10, 5, 9, 10, 12]
 *
 * Sample input
 * array = [141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7]
 *
 * Sample output:
 * [18, 141, 541]
 */
public class FindThreeLargestNumbers {
    public static int[] findThreeLargestNumbers(int[] array) {
        int thirdLargest = Integer.MIN_VALUE;

        int firstLargest = Math.max(array[0], array[1]);
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

    public static int[]
    findThreeLargestNumbers2(int[] array) {
        // nums contains largest number from 3rd to 1st.
        // last number is the largest number.
        int[] nums = new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        int firstLargest = Math.max(array[0], array[1]);
        nums[2] = firstLargest;
        int secondLargest = firstLargest == array[0] ? array[1] : array[0];
        nums[1] = secondLargest;
        for (int i = 2; i < array.length; ++i) {
            findAndSetNextLargest(nums, array[i]);
        }
        return nums;
    }

    private static void findAndSetNextLargest(int[] nums, int num) {
        if (num > nums[2]) {
            nums[0] = nums[1];
            nums[1] = nums[2];
            nums[2] = num;
        } else if (num > nums[1]) {
            nums[0] = nums[1];
            nums[1] = num;
        } else if (num > nums[0]) {
            nums[0] = num;
        }
    }

    public static void main(String[] args) {
        int[] array = {141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7};
        int[] threeLargestNumbers = findThreeLargestNumbers(array);
        for (int num : threeLargestNumbers) {
            System.out.printf("%d ", num);
        }
        System.out.println();

        int[] threeNums = findThreeLargestNumbers(array);
        for (int num : threeNums) {
            System.out.printf("%d ", num);
        }
        System.out.println();
    }
}
