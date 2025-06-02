package com.theory;

import java.io.IOException;
import java.util.Arrays;

public class InterviewQuestion {
    // Question1

    /**
     * Given an array arr[] of size n, the task is to print all the Leaders in the array.
     * An element is a Leader if it is greater than all the elements to its right side.
     * Input: arr[] = {16, 17, 4, 3, 5, 2}
     */
    public static void printLeaders(int[] a) {
        // BF approach. time -> O(n^2)
        for (int i = 0; i < a.length; i++) {
            int curr = a[i];
            //int count = 0;
            boolean isLeader = true;
            for(int j = i + 1; j < a.length; j++) {
                if (curr <= a[j]) {
                   isLeader = false;
                   break;
                }
            }
            if (isLeader) {
                System.out.println(a[i]);
            }
        }
    }

    /**
     *
     * @param
     */
    public static void printLeadersE(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        int greatestSoFar = a[a.length - 1];
        System.out.println(a[a.length - 1]);
        for (int i = a.length - 2; i >= 0; i--) {
            if (a[i] > greatestSoFar) {
                System.out.println(a[i]);
                greatestSoFar = a[i];
            }
        }
    }

    /**
     * Given a sorted array arr (sorted in ascending order) and a target,
     * find if there exists any pair of elements (arr[i], arr[j]) such that their sum is equal to the target.
     * Input: arr[] = {10, 15, 20, 30, 32, 35, 40, 45, 50}, target =35
     */
    public static int[] sumPair(int[] a, int target) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int sum = a[l] + a[r];
            if (sum == target) {
                return new int[]{a[l], a[r]};
            } else if (sum > target) {
                --r;
            } else {
                ++l;
            }
        }
        return null;
    }

    public static void exceptionTest() {
        try {
            throw new IOException();
        } catch(Exception e) {
//        } catch(IOException e) {
//            System.out.println("Hello");
//        }
        }
    }

    public static void main(String[] args) {
//        String s1 = "abc";
//        String s2 = s1;
//
//        s1 = "cda";
//        System.out.println(s1 == s2);
//        System.out.println("s1 = " + s1 + ",s2 = " + s2);
//        System.out.println();
        int[] a = {16, 17, 4, 3, 5, 2};
        printLeadersE(a);
        //int [] a1 = new int[] {}
        int[] pair = sumPair(new int []{10, 15, 20, 30, 32, 35, 40, 45, 50}, -35);
        System.out.println(Arrays.toString(pair));
    }
}
