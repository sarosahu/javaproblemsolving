package com.algo.lc.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 973. K Closest Points to Origin
 *
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane
 * and an integer k, return the k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the
 * Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique
 * (except for the order that it is in).
 */
public class KClosestPointsToOrigin {
    public List<int[]> kClosest(int[][] points, int k) {

        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) ->
                (b[0]*b[0] + b[1]*b[1]) - (a[0] * a[0] + a[1] * a[1]));

        for (int i = 0; i < k; ++i) {
            maxHeap.offer(points[i]);
        }
        for (int i = k; i < points.length; ++i) {
            int [] curr = points[i];
            int [] top = maxHeap.peek();
            int currLen = curr[0] * curr[0] + curr[1] * curr[1];
            int topLen = top[0] * top[0] + top[1] * top[1];
            if (currLen < topLen) {
                maxHeap.poll();
                maxHeap.offer(curr);
            }
        }
        List<int []> closestPoints = new ArrayList<>();
        //int i = 0;
        while (!maxHeap.isEmpty()) {
            closestPoints.add(maxHeap.poll());
        }
        return closestPoints;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin obj = new KClosestPointsToOrigin();
        int [][] points = {
                {1, 3}, {-2, 2}
        };
        List<int[]> kClosestPoints = obj.kClosest(points, 1);
        System.out.println("K closest points :");
        for (int[] point : kClosestPoints) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }
    }
}
