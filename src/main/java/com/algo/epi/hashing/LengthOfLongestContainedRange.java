package com.algo.epi.hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LengthOfLongestContainedRange {
    public static int lengthOfLongestContainedRange(List<Integer> arr) {
        Set<Integer> set = new HashSet<>(arr);
        int maxLen = 0;
        int i = 0;
        while (!set.isEmpty() && i < arr.size()) {
            if (set.contains(arr.get(i))) {
                int curr = arr.get(i);
                set.remove(curr);
                int lowerBound = curr - 1;
                while (set.contains(lowerBound)) {
                    set.remove(lowerBound);
                    --lowerBound;
                }
                int upperBound = curr + 1;
                while (set.contains(upperBound)) {
                    set.remove(upperBound);
                    ++upperBound;
                }
                maxLen = Math.max(maxLen, upperBound - lowerBound - 1);
            }
            ++i;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(-3, -2, 7, 9, 8, 1, 2, 0, -1, 5, 8);
        System.out.println(lengthOfLongestContainedRange(integerList));
    }
}
