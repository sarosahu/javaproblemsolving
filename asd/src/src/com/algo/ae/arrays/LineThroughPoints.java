package com.algo.ae.arrays;

import java.util.HashMap;
import java.util.Map;

public class LineThroughPoints {

    public static int lineThroughPoints(int [][] points) {
        int n = points.length, maxPoints = 1;
        for (int i = 0; i < n; ++i) {
            Map<Float, Integer> hm = new HashMap<>();
            for (int j = i + 1; j < n; ++j) {
                int slopeYComp = points[j][1] - points[i][1];
                int slopeXComp = points[j][0] - points[i][0];
                Float slope = slopeXComp == 0 ?
                        Float.MAX_VALUE :
                        slopeYComp/(float)slopeXComp;

                if (hm.containsKey(slope)) {
                    hm.put(slope, hm.get(slope) + 1);
                } else {
                    hm.put(slope, 2);
                }
                maxPoints = Math.max(maxPoints, hm.get(slope));
            }
        }
        return maxPoints;
    }

    public static void main(String[] args) {
        int [][] points = {
                {1, 1},
                {2, 2},
                {3, 3},
                {0, 4},
                {-2, 6},
                {4, 0},
                {2, 1},
        };

        int maxPoints = lineThroughPoints(points);
        System.out.println("Maximum points in any line : " + maxPoints);
    }
}
