package com.algo.lc.recursion.one;

import java.util.*;

/**
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *                          1
 *                        1   1
 *                      1   2   1
 *                    1   3   3   1
 *                  1   4   6   4   1
 *
 * Example: Input rowIndex = 3
 * Output: [1, 3, 3, 1]
 *
 * Example : Input rowIndex = 0
 *           Output: [1]
 *
 * Example : Input - rowIndex = 1
 *          Output : [1, 1]
 */
public class PascalTriangle {
    Map<Integer, List<Integer>> memo = new HashMap<>();

    public List<Integer> getRowi(int rowIndex) {
        if (rowIndex == 0) {
            return Arrays.asList(1);
        } else if (rowIndex == 1) {
            return Arrays.asList(1, 1);
        }

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(Arrays.asList(1));
        lists.add(Arrays.asList(1, 1));
        for (int i = 2; i <= rowIndex; ++i) {
            List<Integer> array = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; ++j) {
                array.add(1);
            }
            for (int j = 1; j < i; ++j) {
                List<Integer> prevArray = lists.get(i - 1);
                array.set(j, prevArray.get(j - 1) + prevArray.get(j));
            }
            lists.add(array);
        }

        return lists.get(rowIndex);
    }
    /**
     *
     * Recursive approach:
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            return Arrays.asList(1);
        } else if (rowIndex == 1) {
            return Arrays.asList(1, 1);
        }
        memo.put(0, Arrays.asList(1));
        memo.put(1, Arrays.asList(1, 1));

        return getRowHelper(rowIndex);
    }
    public List<Integer> getRowHelper(int rowIndex) {
        if (memo.containsKey(rowIndex)) {
            return memo.get(rowIndex);
        }
        List<Integer> array = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; ++i) {
            array.add(1);
        }
        // Column range is {1, rowIndex - 1}
        for (int j = 1; j < rowIndex; ++j) {
            List<Integer> prevArray = getRowHelper(rowIndex - 1);
            array.set(j, prevArray.get(j - 1) + prevArray.get(j));
        }
        memo.put(rowIndex, array);
        return array;
    }

    public static void main(String[] args) {
        PascalTriangle obj = new PascalTriangle();
        List<Integer> result = obj.getRow(12);
        for (int i : result) {
            System.out.print(i + ",");
        }
        System.out.println();
        List<Integer> result2 = obj.getRowi(12);
        for (int i : result2) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
}
