package com.algo.lc.sortandsearch.bsearch;

/**
 * 1482. Minimum Number of Days to Make m Bouquets
 * You are given an integer array bloomDay, an integer m and an integer k.
 *
 * You want to make m bouquets. To make a bouquet, you need to use k adjacent
 * flowers from the garden.
 *
 * The garden consists of n flowers, the ith flower will bloom in the bloomDay[i]
 * and then can be used in exactly one bouquet.
 *
 * Return the minimum number of days you need to wait to be able to make m bouquets
 * from the garden. If it is impossible to make m bouquets return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
 * Output: 3
 * Explanation: Let us see what happened in the first three days. x means flower bloomed and
 * _ means flower did not bloom in the garden.
 * We need 3 bouquets each should contain 1 flower.
 * After day 1: [x, _, _, _, _]   // we can only make one bouquet.
 * After day 2: [x, _, _, _, x]   // we can only make two bouquets.
 * After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
 *
 *
 * Example 2:
 *
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
 * Output: -1
 * Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers.
 * We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
 *
 *
 * Example 3:
 *
 * Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * Output: 12
 * Explanation: We need 2 bouquets each should have 3 flowers.
 * Here is the garden after the 7 and 12 days:
 * After day 7: [x, x, x, x, _, x, x]
 * We can make one bouquet of the first three flowers that bloomed. We cannot make another
 * bouquet from the last three flowers that bloomed because they are not adjacent.
 * After day 12: [x, x, x, x, x, x, x]
 * It is obvious that we can make two bouquets in different ways.
 *
 *
 * Constraints:
 *
 * bloomDay.length == n
 * 1 <= n <= 10^5
 * 1 <= bloomDay[i] <= 10^9
 * 1 <= m <= 10^6
 * 1 <= k <= n
 */
public class MinDaysToMakeMBouquets {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if ((m * k) > n) {
            return -1;
        }
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for (int e : bloomDay) {
            low = Math.min(low, e);
            high = Math.max(high, e);
        }
        //int ans = -1;
        while (low < high) {
            int mid = low + (high - low)/2;
            int nBouquet = bouquetFormed(bloomDay, mid, k);
            if (nBouquet >= m) {
                //ans = mid;
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int bouquetFormed(int [] arr, int day, int k) {
        int nBouquet = 0;
        int n = arr.length;
        int nFlower = 0;
        for (int i = 0; i < n; ++i) {
            if (arr[i] <= day) {
                nFlower++;
            } else {
                nBouquet += nFlower/k;
                nFlower = 0;
            }
        }
        nBouquet += nFlower/k;
        return nBouquet;
    }

    public static void main(String[] args) {
        MinDaysToMakeMBouquets obj = new MinDaysToMakeMBouquets();
        int minDays = obj.minDays(new int[] {7,7,7,7,12,7,7}, 2, 3);
        System.out.println("Min days : " + minDays);
    }
}
