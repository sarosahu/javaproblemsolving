package com.algo.ae.greedyalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class Photos
 *
 * It's photo day at the local school, and you'are the photographer assigned
 * to take class photos. The class that you'll be photographing has an even
 * number of students, and all these students are wearing red or blue shirts.
 * In fact, exactly half of the class is wearing red shirts, and other half
 * is wearing blue shirts. You're responsible for arranging the students in
 * two rows before taking the photo. Each row should contain the same number
 * of students and should adhere to the following guidelines:
 *
 *  - All students wearing red shirts must be in the same row.
 *  - All students wearing blue shirts must be in the same row.
 *  - Each student in the back row must be strictly taller than the student
 *    directly in front of them in the front row.
 *
 * You're given 2 input arrays: one containing the heights of all the students
 * with red shirts and another one containing the heights of all students
 * with blue shirts. These arrays will always have the same length, and each
 * height will be a positive integer. Write a function that returns whether
 * or not a class photo that follows the stated guidelines can be taken.
 *
 * Note: You can assume that each class has at least 2 students.
 *
 * Sample input:
 * RedShirtHeights = [5, 8, 1, 3, 4]
 * BlueShirtHeights= [6, 9, 2, 4, 5]
 *
 * Sample output: true
 */
public class ClassPhotos {
    // Time: O(nlog(n)), space : O(1) -- where n is the number of students
    public static boolean classPhotos(
            List<Integer> redShirtHeights,
            List<Integer> blueShirtHeights) {

        if (redShirtHeights.size() != blueShirtHeights.size()) {
            return false;
        }
        Collections.sort(redShirtHeights);
        Collections.sort(blueShirtHeights);
        int diff = redShirtHeights.get(0) - blueShirtHeights.get(0);
        if (diff == 0) {
            return false;
        }
        boolean redHigherOrder = diff > 0;
        boolean blueHigherOrder = diff < 0;

        for (int i = 1; i < redShirtHeights.size(); ++i) {
            int heightRedShirt = redShirtHeights.get(i);
            int heightBlueShirt = blueShirtHeights.get(i);
            int currDiff = heightRedShirt - heightBlueShirt;
            if (currDiff == 0) {
                return false;
            }
            if ((redHigherOrder && currDiff < 0) ||
                    (blueHigherOrder && currDiff > 0)) {
                return false;
            }
        }
        return true;
    }

    public static boolean classPhotosS(
            List<Integer> redShirtHeights,
            List<Integer> blueShirtHeights) {

        if (redShirtHeights.size() != blueShirtHeights.size()) {
            return false;
        }
        Collections.sort(redShirtHeights);
        Collections.sort(blueShirtHeights);
        int diff = redShirtHeights.get(0) - blueShirtHeights.get(0);

        for (int i = 0; i < redShirtHeights.size(); ++i) {
            int heightRedShirt = redShirtHeights.get(i);
            int heightBlueShirt = blueShirtHeights.get(i);
            int currDiff = heightRedShirt - heightBlueShirt;

            if ((currDiff == 0) ||
                    (currDiff < 0 && diff > 0) ||
                    (currDiff > 0 && diff < 0)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] redShirtHeights = {5, 8, 1, 3, 4};
        int[] blueShirtHeights = {6, 9, 2, 4, 5};
        boolean isClassPhotoPossible =
                classPhotos(Arrays.asList(5, 8, 1, 3, 4),
                        Arrays.asList(6, 9, 2, 4, 5));
        if (isClassPhotoPossible) {
            System.out.println("Possible");
        } else {
            System.out.println("Not possible");
        }
        isClassPhotoPossible =
                classPhotosS(Arrays.asList(5, 8, 1, 3, 4),
                        Arrays.asList(6, 9, 2, 4, 5));
        if (isClassPhotoPossible) {
            System.out.println("Possible");
        } else {
            System.out.println("Not possible");
        }
    }
}
