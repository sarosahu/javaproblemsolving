package com.algo.ae.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Rectangle Mania
 *
 * Write a function that takes in a list of Cartesian coordinates (i.e., (x, y) coordinates)
 * and returns the number of rectangles formed by these coordinates.
 *
 * A rectangle must have its four corners amongst the coordinates in order to be counted,
 * and we only care about rectangles with side parallel to the x and y axes(i.e. with
 * horizontal and vertical sides -- no diagonal sides).
 *
 * You can also assume that no coordinates will be farther than 100 units from the origin.
 *
 * Sample Input:
 *
 * coords = [
 *     [0, 0],
 *     [0, 1],
 *     [1, 1],
 *     [1, 0],
 *     [2, 1],
 *     [2, 0],
 *     [3, 1],
 *     [3, 0]
 * ]
 *
 * Sample Output: 6
 */
public class RectangleMania {
    // Time: O(n^2), space : O(n) -- where n is the number of coordinates
    public static int rectangleMania(List<Integer[]> coords) {
        Set<String> pointsTable = getPointsTable(coords);
        return getRectangleCount(coords, pointsTable);
    }

    public static Set<String> getPointsTable(List<Integer[]> coords) {
        Set<String> pointsTable = new HashSet<>();
        for (Integer[] point : coords) {
            String pointStr = locationToString(point);
            pointsTable.add(pointStr);
        }
        return pointsTable;
    }

    public static int getRectangleCount(List<Integer[]> coords,
                                        Set<String> pointsTable)
    {
        int count = 0;
        for (Integer[] point1 : coords) {
            for (Integer[] point2 : coords) {
                if (!isInUpperRight(point1, point2)) {
                    continue;
                }

                String upperLocationStr = locationToString(new Integer[]{point1[0], point2[1]});
                if (!pointsTable.contains(upperLocationStr)) {
                    continue;
                }
                String rightLocationStr = locationToString(new Integer[]{point2[0], point1[1]});
                if (!pointsTable.contains(rightLocationStr)) {
                    continue;
                }
                ++count;
            }
        }
        return count;
    }

    public static String locationToString(Integer[] location) {
        return Integer.toString(location[0]) + "-" + Integer.toString(location[1]);
    }

    public static boolean isInUpperRight(Integer[] loc1, Integer[] loc2) {
        return loc2[0] > loc1[0] && loc2[1] > loc1[1];
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
