package com.algo.lc.sortandsearch.bsearch;

/**
 * 875. Koko Eating Bananas
 *
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has
 * piles[i] bananas. The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she
 * chooses some pile of bananas and eats k bananas from that pile. If the
 * pile has less than k bananas, she eats all of them instead and will not
 * eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas
 * before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Example 2:
 *
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 * Example 3:
 *
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= piles.length <= 10^4
 * piles.length <= h <= 10^9
 * 1 <= piles[i] <= 10^9
 */
public class KokoEatingBanana {
    // Brute-force approach
    // Time: O(NM), space : O(1)
    // Where N be the length of input array piles and M be the upper bound of elements in piles.
    public int minEatingSpeedBF(int[] piles, int h) {
        // Start with an eating speed of 1
        int speed = 1;
        while (true) {
            // hourSpent stands for the total hour Koko spends
            // with the given eating speed.
            int hourSpent = 0;

            // Iterate over the piles and calculate the hourSpent.
            for (int pile : piles) {
                hourSpent += Math.ceil((double) pile / speed);
                if (hourSpent > h) {
                    break;
                }
            }

            // Check if Koko can finish all the piles within h hours
            // If so, return speed, else increase the speed by 1 and
            // repeat the previous iteration.
            if (hourSpent <= h) {
                return speed;
            } else {
                speed += 1;
            }
        }
    }

    // Binary search way
    // Time :O(N.Log(M)), Space: O(1)
    // Where N be the length of input array piles and M be the upper
    // bound of elements in piles.
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 1;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        while (left < right) {
            int mid = left + (right - left)/2;
            int hourSpent = 0;

            //Iterate over piles and calculate hourSpent.
            // We increase the hourSpent by ceil(pile/middle)
            for (int pile : piles) {
                hourSpent += Math.ceil((double) pile / mid);
            }

            // Check if mid is a workable speed and cut the search space by half
            if (hourSpent <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // Once the left and right boundaries coincide, we find the target
        // value that is, the minimum workable eating speed.
        return left;
    }

    public static void main(String[] args) {
        KokoEatingBanana obj = new KokoEatingBanana();
        int [] piles = {30,11,23,4,20};
        int h = 5;
        int speed = obj.minEatingSpeedBF(piles, h);
        System.out.println("Minimum eating speed : " + speed);

        speed = obj.minEatingSpeed(piles, h);
        System.out.println("Minimum eating speed : " + speed);
    }
}
