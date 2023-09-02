package com.algo.lc.topintq.array;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (set.contains(x)) {
                return true;
            }
            set.add(x);
        }
        return false;
    }

    public static void main(String[] args) {
        int [] a = {1, 2, 3, 4, 1};
        boolean hasDuplicate = containsDuplicate(a);
        if (hasDuplicate) {
            System.out.println("Array has duplicate");
        } else {
            System.out.println("Array doesn't have duplicate");
        }
    }
}
