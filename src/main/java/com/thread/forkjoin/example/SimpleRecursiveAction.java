package com.thread.forkjoin.example;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    private int simulatedWork;
    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }
    @Override
    protected void compute() {
        // If the task is too large then we split it and execute the tasks in parallel
        if (simulatedWork > 100) {
            // Split
            System.out.println("Parallel execution and split the tasks..");
            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork/2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork/2);

            /*
            action1.fork();
            action2.fork();

            action1.join();
            action2.join();
             */
            invokeAll(action1, action2);
        } else {
            System.out.println("THe task is rather small!");
        }
    }
}
