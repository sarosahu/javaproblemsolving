package com.streams.parallelstream;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    public  static final long THRESHOLD = 10_000;
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            System.out.println("Less than threshold");
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; ++i) {
            sum += numbers[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        long[] nums = {1, 2, 3, 4, 5};
        long[] nums2 = new long[10001];
        for (int i = 0; i <= 10000; ++i) {
            nums2[i] = i + 1;
        }
        ForkJoinSumCalculator obj = new ForkJoinSumCalculator(nums);
        long sum = obj.compute();
        System.out.println("Sum : " + sum);

        ForkJoinSumCalculator obj2 = new ForkJoinSumCalculator(nums2);
        long sum2 = obj2.compute();
        System.out.println("Sum : " + sum2);
    }
}
