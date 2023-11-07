package com.algo.lc.dynamicprogramming;

import java.util.Arrays;

/**
 * Minimum Difficulty of a Job Schedule
 *
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 *
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 *
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 *
 *
 * Example 1:
 *      |6|
 *      |5|
 *      |4|
 *      |3|
 *      |2|         1
 *      ---       ---
 *      day1      day2
 *
 *  Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 *
 *
 * Example 2:
 *
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day.
 * you cannot find a schedule for the given jobs.
 *
 * Example 3:
 *
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 *
 * Constraints:
 *
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 */
public class MinDifficultyOfJobSchedule {
    private int[] hardestJob;
    // Top-down approach

    /**
     *
     * Complexity Analysis
     *
     * Let n be the length of the jobDifficulty array, and d be the total number of days.
     *
     * Time complexity: O(n2⋅d), since there are n⋅d possible states, and we need O(n)
     * time to calculate the result for each state.
     *
     * Space complexity: O(n⋅d) space is required to memoize all n⋅d states.
     */
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }

        int[][] mem = new int[n][d+1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(mem[i], -1);
        }

        this.hardestJob = new int[n];
        int maxDiff = 0;
        for (int i = n - 1; i >= 0; --i) {
            maxDiff = Math.max(maxDiff, jobDifficulty[i]);
            hardestJob[i] = maxDiff;
        }
        return minDiff(0, d, jobDifficulty, mem);
    }

    private int minDiff(int currJobIdx,
                        int daysRemaining,
                        int[] jobDifficulty,
                        int[][] mem)
    {
        // Use memoization to avoid repeated computation of states
        if (mem[currJobIdx][daysRemaining] != -1) {
            return mem[currJobIdx][daysRemaining];
        }

        // Base case: finish all remaining jobs in the last day
        if (daysRemaining == 1) {
            return hardestJob[currJobIdx];
        }

        int res = Integer.MAX_VALUE;
        int dailyMaxJobDiff = 0;
        int n = jobDifficulty.length;
        for (int j = currJobIdx; j < n - daysRemaining + 1; ++j) {
            dailyMaxJobDiff = Math.max(dailyMaxJobDiff, jobDifficulty[j]);
            res = Math.min(res, dailyMaxJobDiff +
                    minDiff(j + 1, daysRemaining - 1, jobDifficulty, mem));
        }
        mem[currJobIdx][daysRemaining] = res;
        return res;
    }

    /**
     * Bottom - up approach
     * @param jobDifficulty
     * @param d
     * Complexity Analysis
     *
     * Let n be the length of the jobDifficulty array, and d be the total number of days.
     *
     * Time complexity: O(n^2⋅d), since there are n*d possible states, and we need O(n) time
     * to calculate the result for each state.
     *
     * Space complexity: O(n⋅d) is required for the (n+1)×(d+1) DP array.
     *
     * TODO
     * Note: As of now, this logic is difficult to understand
     * Can it be simplified ?
     */
    public int minDifficultyBU(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        //Initialize the minDiff matrix to record the minimum difficulty
        // of the job schedule
        int[][] minDiff = new int[d + 1][n + 1];
        for (int daysRemaining = 0; daysRemaining <= d; ++daysRemaining) {
            for (int i = 0; i < n; ++i) {
                minDiff[daysRemaining][i] = Integer.MAX_VALUE;
            }
        }
        for (int daysRemaining = 1; daysRemaining <= d; daysRemaining++) {
            for (int i = 0; i < n - daysRemaining + 1; ++i) {
                int dailyMaxJobDiff = 0;
                for (int j = i + 1; j < n - daysRemaining + 2; ++j) {
                    dailyMaxJobDiff = Math.max(dailyMaxJobDiff, jobDifficulty[j - 1]);
                    if (minDiff[daysRemaining - 1][j] != Integer.MAX_VALUE) {
                        minDiff[daysRemaining][i] =
                                Math.min(minDiff[daysRemaining][i],
                                        dailyMaxJobDiff + minDiff[daysRemaining - 1][j]
                                );
                    }
                }
            }
        }
        return minDiff[d][0] < Integer.MAX_VALUE ? minDiff[d][0] : -1;
    }
}
