package com.algo.lc.treegraphs.disjointset;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are n people in a social group labeled from 0 to n - 1.
 * You are given an array logs where logs[i] = [timestampi, xi, yi] indicates that
 * xi and yi will be friends at the time timestampi.
 *
 * Friendship is symmetric. That means if a is friends with b, then b is friends with a.
 * Also, person a is acquainted with a person b if a is friends with b, or a is a friend
 * of someone acquainted with b.
 *
 * Return the earliest time for which every person became acquainted with every other
 * person. If there is no such earliest time, return -1.
 *
 * Example 1:
 *
 * Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],
 *                [20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], n = 6
 * Output: 20190301
 * Explanation:
 * The first event occurs at timestamp = 20190101, and after 0 and 1 become friends,
 * we have the following friendship groups [0,1], [2], [3], [4], [5].
 *
 * The second event occurs at timestamp = 20190104, and after 3 and 4 become friends,
 * we have the following friendship groups [0,1], [2], [3,4], [5].
 *
 * The third event occurs at timestamp = 20190107, and after 2 and 3 become friends,
 * we have the following friendship groups [0,1], [2,3,4], [5].
 *
 * The fourth event occurs at timestamp = 20190211, and after 1 and 5 become friends,
 * we have the following friendship groups [0,1,5], [2,3,4].
 *
 * The fifth event occurs at timestamp = 20190224, and as 2 and 4 are already friends, nothing happens.
 * The sixth event occurs at timestamp = 20190301, and after 0 and 3 become friends, we all become friends.
 *
 *
 * Example 2:
 *
 * Input: logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
 * Output: 3
 * Explanation: At timestamp = 3, all the persons (i.e., 0, 1, 2, and 3) become friends.
 */
public class EarliestMomentWhenAllBecomeFriends {
    static class DisjointSet {
        private int[] parent;
        private int[] rank;
        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        public int find(int idx) {
            if (idx != parent[idx]) {
                parent[idx] = find(parent[idx]);
            }
            return parent[idx];
        }
        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return;
            }
            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootB] < rank[rootA]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA] += 1;
            }
        }

        public boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }
    }
    public int earliestAcq(int[][] logs, int n) {
        // In order to ensure that we find the earliest moment,
        // we need to sort the events in chronological order.
        Arrays.sort(logs, new Comparator<int[]>() {
            @Override
            public int compare(int [] a, int [] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        // Initially, we treat each individual as a separate group.
        int groupCount = n;
        DisjointSet ds = new DisjointSet(n);
        for (int [] log : logs) {
            int f1 = log[1];
            int f2 = log[2];
            if (!ds.isConnected(f1, f2)) {
                ds.union(f1, f2);
                --groupCount;
            }
            if (groupCount == 1) {
                return log[0];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] logs = {
                {20190101,0,1},{20190104,3,4},{20190107,2,3},{20190211,1,5},
                {20190224,2,4},{20190301,0,3},{20190312,1,2},{20190322,4,5}
        };
        EarliestMomentWhenAllBecomeFriends obj = new EarliestMomentWhenAllBecomeFriends();
        int earliestTime = obj.earliestAcq(logs, 6);
        System.out.println("Earliest time : " + earliestTime);
    }
}
