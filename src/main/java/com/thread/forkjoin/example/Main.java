package com.thread.forkjoin.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ForkJoinPool pool = new ForkJoinPool();

        SimpleRecursiveAction action1 = new SimpleRecursiveAction(800);
        pool.invoke(action1);

        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        PrintAction action2 = new PrintAction(list);
        pool.invoke(action2);

        // 0, 1, 1, 2, 3
        System.out.println("Fibonacci number for 25:" + pool.invoke(new FibonacciTask(25)));

    }
}
