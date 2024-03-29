package com.algo.ae.greedyalgo;

import java.util.*;

/**
 * Task Assignment
 *
 * You're given an integer k representing a number of workers and an array of
 * positive integers representing durations of tasks that must be completed
 * by the workers. Specifically, each worker must complete 2 unique tasks and
 * can only work on one task at a time.The number of tasks will always be
 * equal to 2k, such that each worker always has exactly 2 tasks to complete.
 * All tasks are independent of one another and can be completed in any order.
 * Workers will complete their assigned tasks in parallel, and the time taken
 * to complete all tasks will be equal to the time taken to complete the
 * longest pair of tasks (see the sample output for an explanation).
 *
 * Write a function that returns the optimal assignment of tasks to each
 * worker such that the tasks are completed as fast as possible. Your function
 * should return a list of pairs, where each pair stores the indices of the
 * tasks that should be completed by one worker. The pairs should be in the
 * following format: [task1, task2], where the order of task1 and task2 does
 * not matter. Your function can return the pairs in any order. If multiple
 * optimal assignments exist, any correct answer will be accepted.
 *
 * Note: You'll always be given a least one worker (i.e. k will always be > 0)
 *
 * Sample Input:
 * k = 3
 * tasks = [1, 3, 5, 3, 1, 4]
 *
 * Sample Output:
 * [
 *  [0, 2], // tasks[0] = 1, tasks[2] = 5 (1 + 5 = 6)
 *  [4, 5], // tasks[4] = 1, tasks[5] = 4 (1 + 4 = 5)
 *  [1, 3], // tasks[1] = 3, tasks[3] = 3 (3 + 3 = 6)
 * ]
 */
public class TaskAssignment {
     // Time: O(nlog(n)), space : O(n) -- n is no of tasks
    public ArrayList<ArrayList<Integer>> taskAssignment(int k, ArrayList<Integer> tasks) {
        ArrayList<ArrayList<Integer>> pairedTasks = new ArrayList<>();
        Map<Integer, List<Integer>> taskDurationToIndices =
                getTaskDurationToIndices(tasks);

        Collections.sort(tasks);
        int left = 0, right = tasks.size() - 1;
        while (left <= right) {
            ArrayList<Integer> pairList = new ArrayList<>();

            List<Integer> task1ToIndexList = taskDurationToIndices.get(tasks.get(left));
            int taskIndex1 = task1ToIndexList.remove(task1ToIndexList.size() -1);
            List<Integer> task2ToIndexList = taskDurationToIndices.get(tasks.get(right));
            int taskIndex2 = task2ToIndexList.remove(task2ToIndexList.size() -1);
            pairedTasks.add(new ArrayList<>(Arrays.asList(taskIndex1, taskIndex2)));
            ++left;
            --right;
        }
        return pairedTasks;
    }

    public Map<Integer, List<Integer>> getTaskDurationToIndices(ArrayList<Integer> tasks) {
        Map<Integer, List<Integer>> taskDurationsToIndices =
                new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < tasks.size(); ++i) {
            int task = tasks.get(i);
            if (!taskDurationsToIndices.containsKey(task)) {
                List<Integer> indices = new ArrayList<Integer>();
                taskDurationsToIndices.put(tasks.get(i), indices);
            }
            taskDurationsToIndices.get(task).add(i);
        }

        return taskDurationsToIndices;
    }
}
