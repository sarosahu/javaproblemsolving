package com.algo.lc.monotonicqueue;

public class KEmptySlots {
    public static int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        int[] days = new int[n];
        for (int i = 0; i < n; ++i) {
            days[bulbs[i] - 1] = i + 1;
        }

        int ans = Integer.MAX_VALUE;
        int left = 0, right = k + 1;

        while (right < n) {
            int startIdx = left + 1;
            while (startIdx < right) {
                if (days[startIdx] < days[left] || days[startIdx] < days[right]) {
                    break;
                }
                ++startIdx;
            }

            // Check if there is no subset within days array of k length where
            // each values are > left and right values i.e. startIdx != right
            if (startIdx != right) {
                left = startIdx;
                right = left + k + 1;
                continue;
            }
            ans = Math.min(ans, Math.max(days[left], days[right]));
            left = right;
            right = left + k + 1;
        }
        return ans < Integer.MAX_VALUE ? ans : -1;
    }

    public static void main(String[] args) {
        //int [] bulbs = {1, 3, 2, 5, 8, 4, 7, 6};
        int [] bulbs = {2, 1, 3, 5, 8, 4, 6, 7};
        int day = kEmptySlots(bulbs, 2 );
        System.out.println("Day : " + day);
    }
}
