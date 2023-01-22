package com.algo.ae.greedyalgo;

import java.util.Arrays;

/**
 * Minimum Waiting Time
 *
 * You are given a non-empty array of +ve integers representing the amounts of time
 * that speceific queries take to execute. Only one query can be executed at a time,
 * but the queries can be executed in any order.
 *
 * A query's waiting time is defined as the amount of time that it must wait before
 * its execution starts. In other words, if a query is executed second, then its
 * waiting time is the duration of the first query;if a query is executed third,
 * then its waiting time is the sum of the durations of first 2 queries.
 *
 * Write a function that returns the minimum amount of total waiting time for all
 * of the queries. For example, if you're given the queries of durations[1, 4, 5],
 * then the total waiting time, if the queries were executed in order of [5, 1, 4]
 * would be (0) + (5) + (5 + 1) = 11.
 *
 * Note: you are allowed to mutate the input array.
 *
 * Sample input:
 * queries = [3, 2, 1, 2, 6]
 *
 * Sample output: 17
 */
public class MinimumWaitingTime {
    public static int minimumWaitingTime(int[] queries) {
        if (queries.length <= 1) {
            return 0;
        }
        Arrays.sort(queries);
        int prevQueryExecTime = 0;
        int totalWaitingTime = 0;
        int totalQueryExecTimeSoFar = 0;
        for (int i = 0; i < queries.length; ++i) {
            int currQueryExecTime = queries[i];
            totalQueryExecTimeSoFar += prevQueryExecTime;
            int currWaitingTime = totalQueryExecTimeSoFar;
            totalWaitingTime += currWaitingTime;
            prevQueryExecTime = currQueryExecTime;
        }
        return totalWaitingTime;
    }

    public static int minimumWaitingTime2(int[] queries) {
        Arrays.sort(queries);
        int totalWaitingTime = 0;
        for (int i = 0; i < queries.length; ++i) {
            int duration = queries[i];
            int numQueriesUsesThisDuration = queries.length - (i + 1);
            totalWaitingTime += duration * numQueriesUsesThisDuration;
        }
        return totalWaitingTime;
    }

    public static void main(String[] args) {
        int [] queriesTime = {3, 2, 1, 2, 6};
        int minWaitingTime = minimumWaitingTime(queriesTime);
        System.out.println("Minimum waiting time : " + minWaitingTime);
    }
}
