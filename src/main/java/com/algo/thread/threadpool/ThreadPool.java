package com.algo.thread.threadpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    // Create a LinkedList field containing Runnable. This is our "tasks" queue.
    // Hint: Since LinkedList is not thread-safe, we need to synchronize it.
    private final LinkedList<Runnable> tasks = new LinkedList<>();
    // Create an ArrayList containing all the Worker threads.
    // Hint: ArrayList is also not thread-safe, so we need to synchronize it.
    private final List<Worker> workers = new ArrayList<>();

    //Lock lock = new ReentrantLock();
    public ThreadPool(int poolSize) {
        // create several Worker threads and add them to workers list
        // Hint: Worker is an inner class defined at the bottom of this class
        for (int i = 0; i < poolSize; ++i) {
            workers.add(new Worker("worker-" + i));
            synchronized (workers) {
                workers.get(i).start();
            }
        }
    }

    private Runnable take() throws InterruptedException {
        // if the LinkedList is empty, we wait
        //
        synchronized (tasks) {
            while (tasks.isEmpty()) {
                tasks.wait();
            }
            // remove the first task from the LinkedList and return it
            return tasks.removeFirst();
        }
    }

    public void submit(Runnable task) {
        // Add the task to the LinkedList and notifyAll
        synchronized (tasks) {
            tasks.add(task);
            tasks.notifyAll();
        }
    }

    public int getRunQueueLength() {
        // return the length of the LinkedList
        // remember to also synchronize!
        synchronized (tasks) {
            return tasks.size();
        }
    }

    @SuppressWarnings("deprecation")
    public void shutdown() {
        // this should call stop() on the worker threads.
        synchronized (workers) {
            for (Worker worker : workers) {
                worker.stop();
            }
        }
    }

    private class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }

        public void run() {
            // we run in an infinite loop:
            // remove the next task from the linked list using take()
            // we then call the run() method on the task
            while (true) {
                try {
                    take().run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
