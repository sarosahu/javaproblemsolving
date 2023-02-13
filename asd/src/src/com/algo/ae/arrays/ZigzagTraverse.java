package com.algo.ae.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Zigzag Traverse
 *
 * Write a function that takes in an nXm two-dimensional array(that can be square-shaped
 * when n == m) and returns a one-dimensional array of all the array's elements in zigzag order.
 *
 * Zigzag order starts at the top left corner of the two-dimensional array, goes down by one
 * element, and proceeds in a zigzag pattern all the way to the bottom right corner.
 *
 * Sample Input:
 *
 * array = [
 *     [1, 3, 4, 10],
 *     [2, 5, 9, 11],
 *     [6, 8, 12, 15],
 *     [7, 13, 14, 16]
 * ]
 *
 * Sample Output:
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
 */
public class ZigzagTraverse {
    public static class Point {
        public int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public enum State {
        INITIAL, DOWN, RIGHT, CORNORUP, CORNORDOWN
    }
    // Time : O(N), Space : O(N)
    public static List<Integer> zigzagTraverse(List<List<Integer>> array) {
        // Write your code here.
        List<Integer> result = new ArrayList<>();
        State state = State.INITIAL;
        State nextState = State.INITIAL;
        Point lastPoint = new Point(-1, -1);
        int row = 0, col = 0;
        int rowSize = array.size(), colSize = array.get(0).size();

        while (row != rowSize - 1 || col != colSize - 1) {
            if (state == State.INITIAL || state == State.DOWN) {
                if (state == State.INITIAL) {
                    result.add(array.get(row).get(col));
                    lastPoint.x = row;
                    lastPoint.y = col;
                    nextState = State.DOWN;
                } else {
                    printDown(row, col, array, lastPoint, result);
                    if (lastPoint.x == row && lastPoint.y == col) {
                        // print down is not possible.
                        printRight(row, col, array, lastPoint, result);
                    }
                    nextState = State.CORNORUP;
                }
            } else if (state == State.CORNORUP) {
                printCornorUp(row, col, array, lastPoint, result);
                nextState = State.RIGHT;
            } else if (state == State.RIGHT) {
                printRight(row, col, array, lastPoint, result);
                if (lastPoint.x == row && lastPoint.y == col) {
                    printDown(row, col, array, lastPoint, result);
                }
                nextState = State.CORNORDOWN;
            } else if (state == State.CORNORDOWN) {
                printCornorDown(row, col, array, lastPoint, result);
                nextState = State.DOWN;
            }
            state = nextState;
            row = lastPoint.x;
            col = lastPoint.y;
        }
        if (row == 0 && col == 0) {
            // 1 element array only present.
            result.add(array.get(row).get(col));
        }
        return result;
    }

    private static void printDown(int row, int col, List<List<Integer>> array, Point point, List<Integer> result) {
        if (row + 1 < array.size()) {
            result.add(array.get(row+1).get(col));
            point.x = row + 1;
        }
    }

    private static void printRight(int row, int col, List<List<Integer>> array, Point point, List<Integer> result) {
        if (col + 1 < array.get(0).size()) {
            result.add(array.get(row).get(col + 1));
            point.y = col + 1;
        }
    }

    private static void printCornorUp(int row, int col, List<List<Integer>> array, Point point, List<Integer> result) {
        while (row - 1 >= 0 && col + 1 < array.get(0).size()) {
            result.add(array.get(row - 1).get(col + 1));
            point.x = row - 1;
            point.y = col + 1;
            --row;
            ++col;
        }
    }

    private static void printCornorDown(int row, int col, List<List<Integer>> array, Point point, List<Integer> result) {
        while (row + 1 < array.size() && col - 1 >= 0) {
            result.add(array.get(row + 1).get(col - 1));
            point.x = row + 1;
            point.y = col - 1;
            ++row;
            --col;
        }
    }

    // This is from solution, simple one
    // Time : O(N), Space : O(N)
    public static List<Integer> zigzagTraverse2(List<List<Integer>> array) {
        int height = array.size() - 1;
        int width = array.get(0).size() - 1;
        List<Integer> result = new ArrayList<>();

        int row = 0;
        int col = 0;
        boolean goingDown = true;
        while (!isOutOfBounds(row, col, height, width)) {
            result.add(array.get(row).get(col));
            if (goingDown) {
                if (col == 0 || row == height) {
                    goingDown = false;

                    if (row == height) {
                        col++;
                    } else {
                        ++row;
                    }
                } else {
                    ++row;
                    --col;
                }
            } else {
                if (row == 0 || col == width) {
                    goingDown = true;
                    if (col == width) {
                        ++row;
                    } else {
                        ++col;
                    }
                } else {
                    --row;
                    ++col;
                }
            }
        }
        return result;
    }

    public static boolean isOutOfBounds(int row, int col, int height, int width) {
        return row < 0 || row > height || col < 0 || col > width;
    }
}
