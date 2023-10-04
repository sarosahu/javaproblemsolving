package com.streams.flatmap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapTest {
    public static void main(String[] args) {
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(4, 5);

        List<int[]> pairs =
                nums1.stream()
                        .flatMap(i -> nums2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(Collectors.toList());

        pairs.forEach(nums -> System.out.println(nums[0] + "," + nums[1]));
    }
}
