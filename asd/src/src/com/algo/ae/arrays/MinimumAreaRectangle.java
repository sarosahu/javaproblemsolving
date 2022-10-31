package com.algo.ae.arrays;

import java.util.HashSet;

public class MinimumAreaRectangle {
    public static int minimumAreaRectangle(int[][] points) {
        HashSet<String> pointSet = createPointSet(points);
        int minArea = Integer.MAX_VALUE;

        for (int currIdx = 1; currIdx < points.length; ++currIdx) {
            int p2x = points[currIdx][0];
            int p2y = points[currIdx][1];

            for (int prevIdx = 0; prevIdx < currIdx; ++prevIdx) {
                int p1x = points[prevIdx][0];
                int p1y = points[prevIdx][1];

                // Check if the points p1 and p2 are diagonals or not
                // if, they are diagonals then return true, we need to continue
                boolean pointsShareValue = p1x == p2x || p1y == p2y;
                if (pointsShareValue) {
                    continue;
                }

                // if (p1x, p2y) and (p2x, p1y) exist, then we'have found a rectangle
                boolean hasOppositeDiagonalToPoint1Exist = pointSet.contains(convertPointToString(p1x, p2y));
                boolean hasOppositeDiagonalToPoint2Exist = pointSet.contains(convertPointToString(p2x, p1y));
                boolean oppositeDiagonalExists = hasOppositeDiagonalToPoint1Exist && hasOppositeDiagonalToPoint2Exist;

                if (oppositeDiagonalExists) {
                    int currArea = Math.abs(p2x - p1x) * Math.abs(p2y - p1y);
                    minArea = Math.min(minArea, currArea);
                }
            }
        }

        return minArea != Integer.MAX_VALUE ? minArea : 0;
    }

    public static String convertPointToString(int x, int y) {
        return String.valueOf(x) + ":" + String.valueOf(y);
    }

    public static HashSet<String> createPointSet(int[][] points) {
        HashSet<String> pointSet = new HashSet<String>();
        for (int [] point : points) {
            int x = point[0];
            int y = point[1];
            String pointString = convertPointToString(x, y);
            pointSet.add(pointString);
        }
        return pointSet;
    }
    public static void main(String[] args) {
        int[][] points = {
                {1, 5},
                {5, 1},
                {4, 2},
                {2, 4},
                {2, 2},
                {1, 2},
                {4, 5},
                {2, 5},
                {-1, -2},
        };

        int minArea = minimumAreaRectangle(points);
        System.out.println("Minimum area : " + minArea);
    }
}
