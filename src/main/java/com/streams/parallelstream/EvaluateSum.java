package com.java.streams.parallelstream;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class EvaluateSum {
    public static long sequentialSum(int n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static long rangeSum(int n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    public static long parallelRangeSum(int n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static void main(String[] args) {
        long sum10 = sequentialSum(100000);
        System.out.println("Sum of first 100000 numbers : " + sum10);

        sum10 = rangeSum(100000);
        System.out.println("Sum of first 100000 numbers : " + sum10);

        sum10 = parallelRangeSum(100000);
        System.out.println("Sum of first 100000 numbers : " + sum10);
    }
}
