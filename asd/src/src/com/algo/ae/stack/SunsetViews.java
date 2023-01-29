package com.algo.ae.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Sunset Views
 *
 * Given an array of buildings and a direction that all of the buildings face,
 * return an array of the indices of the buildings that can see the sunset.
 *
 * A building can see the sunset if it's strictly taller than all of the buildings
 * that come after it in the direction that it faces.
 *
 * The input array named buildings contains +ve, non-zero integers representing
 * the heights of the buildings. A building at index i thus has a height
 * denoted by buildings[i]. All of the buildings face the same direction,
 * and this direction is either east or west, denoted by the input string
 * named direction, which will always be equal to either "EAST" or "WEST".
 * In relation to the input array, you can interpret these directions as right
 * for east and left for west.
 *
 * Important note: the indices in the output array should be sorted in ascending
 * order.
 *
 * Sample input#1
 * buildings = [3, 5, 4, 4, 3, 1, 3, 2]
 * direction = "EAST"
 *
 * Sample output#1 : [1, 3, 6, 7]
 */
public class SunsetViews {
    static class Building {
        int idx;
        int height;
        public Building(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }

    public List<Integer> sunsetViews(int[] buildings, String direction) {
        List<Integer> buildingsWithSunsetViews = new ArrayList<>();
        int startIdx = buildings.length - 1;
        int step = -1;

        if (direction.equals("WEST")) {
            startIdx = 0;
            step = 1;
        }

        int idx = startIdx;
        int runningMaxHeight = 0;

        while (idx >= 0 && idx < buildings.length) {
            int currBuildingHeight = buildings[idx];

            if (currBuildingHeight > runningMaxHeight) {
                buildingsWithSunsetViews.add(idx);
            }

            runningMaxHeight = Math.max(runningMaxHeight, currBuildingHeight);
            idx += step;
        }

        if (direction.equals("EAST")) {
            Collections.reverse(buildingsWithSunsetViews);
        }

        return buildingsWithSunsetViews;
    }

    public static List<Integer> sunsetViews2(int[] buildings, String direction) {
        Stack<Building> candidateBuildings = new Stack<>();
        int startIdx = buildings.length - 1;
        int step = -1;

        if (direction.equals("EAST")) {
            startIdx = 0;
            step = 1;
        }
        int idx = startIdx;
        while (idx < buildings.length && idx >= 0) {
            int currBuildingHeight = buildings[idx];
            while (!candidateBuildings.isEmpty() &&
                    currBuildingHeight >= candidateBuildings.peek().height) {
                candidateBuildings.pop();
            }
            candidateBuildings.push(new Building(idx, currBuildingHeight));
            idx += step;
        }
        List<Integer> candidateBuildingHeights = new ArrayList<>();
        while (!candidateBuildings.isEmpty()) {
            int currIndex = candidateBuildings.peek().idx;
            candidateBuildingHeights.add(currIndex);
            candidateBuildings.pop();
        }
        if (direction.equals("EAST")) {
            Collections.reverse(candidateBuildingHeights);
        }
        return candidateBuildingHeights;
    }

    public static void main(String[] args) {
        int[] heights = {3, 5, 4, 4, 3, 1, 3, 2};
        List<Integer> buildingsWithSunsetView = sunsetViews2(heights, "EAST");
        for (int height : buildingsWithSunsetView) {
            System.out.printf("%d, ", height);
        }
    }
}
