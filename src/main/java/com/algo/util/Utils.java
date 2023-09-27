package com.algo.util;

import java.util.List;

public class Utils {
    public static void print1DArray(int[] arr) {
        for (int a : arr) {
            System.out.printf("%d, ", a);
        }
        System.out.println();
    }

    public static void print1DArray(Integer[] arr) {
        for (int a : arr) {
            System.out.printf("%d, ", a);
        }
        System.out.println();
    }

    public static void print2DList(List<List<Integer>> arrays) {
        for (List<Integer> array : arrays) {
            print1DArray(array.stream().toArray(Integer[]::new));
        }
    }
}
