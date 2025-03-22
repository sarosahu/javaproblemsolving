package com.thread.forkjoin.example;

import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Integer> {
    private int n;
    public FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        // F(0) = F(1) = 0;
        if (n <= 1) {
            return n;
        }
        FibonacciTask task1 = new FibonacciTask(n - 1);
        FibonacciTask task2 = new FibonacciTask(n - 2);

        task1.fork();
        task2.fork();

        return task1.join() + task2.join();
    }
}
