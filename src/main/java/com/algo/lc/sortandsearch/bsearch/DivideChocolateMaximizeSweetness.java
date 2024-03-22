package com.algo.lc.sortandsearch.bsearch;

import java.util.Arrays;

/**
 * 1231. Divide Chocolate
 *
 * You have one chocolate bar that consists of some chunks. Each chunk has its own
 * sweetness given by the array sweetness.
 *
 * You want to share the chocolate with your k friends so you start cutting the
 * chocolate bar into k + 1 pieces using k cuts, each piece consists of some consecutive chunks.
 *
 * Being generous, you will eat the piece with the minimum total sweetness and give the other
 * pieces to your friends.
 *
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 *
 * Example 1:
 *
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], k = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 *
 * Example 2:
 *
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], k = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 *
 * Example 3:
 *
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], k = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 *
 * Constraints:
 *
 * 0 <= k < sweetness.length <= 10^4
 * 1 <= sweetness[i] <= 10^5
 */
public class DivideChocolateMaximizeSweetness {
    public int maximizeSweetness(int[] sweetness, int k) {
        // Initialize the left and right boundaries
        // left = 1 and right = total sweetness / no of people
        int n = k + 1;
        int left = Arrays.stream(sweetness).min().getAsInt();
        int right = Arrays.stream(sweetness).sum() / n;

        while (left < right) {
            // Get the middle index between left and right boundary indexes.
            // curr_sweetness stands for the total sweetness for the current person.
            // people_with_chocolate stands for the no of people that have
            // a piece of chocolate of sweetness greater than or equal to mid.

            //int mid = (left + right + 1) / 2;
            int mid = left + (right - left + 1)/2;
            int currSweetness = 0;
            int peopleWithChoco = 0;

            // Start assigning chunks to the current people
            for (int s : sweetness) {
                currSweetness += s;

                // If the total sweetness for him/her is no less than mid,
                // meaning we have done with him/her and should move on assigning
                // chunks to the next people.
                if (currSweetness >= mid) {
                    peopleWithChoco += 1;
                    currSweetness = 0;
                }
            }

            // Check if we successfully give everyone a piece of chocolate with sweetness
            // no less than mid, and eliminate the search space by half.
            if (peopleWithChoco >= n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the maximum possible sweetness we can get.
        return left;
    }

    public static void main(String[] args) {
        DivideChocolateMaximizeSweetness obj = new DivideChocolateMaximizeSweetness();
        int[] sweetness = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int maxTotalSweetness = obj.maximizeSweetness(sweetness, 5);
        System.out.println("Maximum total sweetness : " + maxTotalSweetness);
    }
}
