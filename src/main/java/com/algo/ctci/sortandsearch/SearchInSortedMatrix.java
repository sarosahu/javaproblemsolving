package com.algo.ctci.sortandsearch;

public class SearchInSortedMatrix {
    static class Coordinate implements Cloneable {
        public int row, column;
        public Coordinate(int r, int c) {
            this.row = r;
            this.column = c;
        }

        public boolean inbounds(int[][] matrix) {
            return row >= 0 && column >= 0 &&
                    row < matrix.length && column < matrix[0].length;
        }

        public boolean isBefore(Coordinate p) {
            return row <= p.row && column <= p.column;
        }

        public Object clone() {
            return new Coordinate(row, column);
        }

        public void setToAverage(Coordinate min, Coordinate max) {
            this.row = (min.row + max.row) / 2;
            this.column = (min.column + max.column) / 2;
        }
    }

    public Coordinate findElement(int[][] matrix, int x) {
        Coordinate origin = new Coordinate(0, 0);
        Coordinate dest = new Coordinate(matrix.length - 1, matrix[0].length - 1);
        return findElement(matrix, origin, dest, x);
    }

    public Coordinate findElement(int[][] matrix, Coordinate origin, Coordinate dest, int x) {
        if (!origin.inbounds(matrix) || !dest.inbounds(matrix)) {
            return null;
        }
        if (matrix[origin.row][origin.column] == x) {
            return origin;
        } else if (!origin.isBefore(dest)) {
            return null;
        }

        // Set start to start of diagonal and end to end of the diagonal. Since the grid may not
        // be square, the end of the diagonal may not equal to dest.
        Coordinate start = (Coordinate) origin.clone();
        int diaDist = Math.min(dest.row - origin.row, dest.column - origin.column);
        Coordinate end = new Coordinate(start.row + diaDist, start.column + diaDist);
        Coordinate p = new Coordinate(0, 0);

        // Do binary search on diagonal , looking for first element.
        while (start.isBefore(end)) {
            p.setToAverage(start, end);
            if (x > matrix[p.row][p.column]) {
                start.row = p.row + 1;
                start.column = p.column + 1;
            } else {
                end.row = p.row - 1;
                end.column = p.column - 1;
            }
        }

        // Split the grid into quadrants. Search the bottom left and top right.
        return partitionAndSearch(matrix, origin, dest, start, x);
    }

    Coordinate partitionAndSearch(int[][] matrix, Coordinate origin, Coordinate dest, Coordinate pivot, int x) {
        Coordinate lowerLeftOrigin = new Coordinate(pivot.row, origin.column);
        Coordinate lowerLeftDest = new Coordinate(dest.row, pivot.column - 1);
        Coordinate upperRightOrigin = new Coordinate(origin.row, pivot.column);
        Coordinate upperRightDest = new Coordinate(pivot.row - 1, dest.column);

        Coordinate lowerLeft = findElement(matrix, lowerLeftOrigin, lowerLeftDest, x);
        if (lowerLeft == null) {
            return findElement(matrix, upperRightOrigin, upperRightDest, x);
        }
        return lowerLeft;
    }
}
