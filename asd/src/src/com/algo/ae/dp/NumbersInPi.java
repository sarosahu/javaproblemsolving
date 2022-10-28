/**
 * Numbers In Pi
 *
 * Given a string representation of the first n digits of Pi and a list of positive
 * integers (all in string format), write a function that returns the smallest number
 * of spaces that can be added to the n digits of Pi, such that all resulting numbers
 * are found in the list of integers.
 *
 * Note that a single number can appear multiple times in the resulting numbers.
 * For example, if Pi is "3141" and the numbers are ["1", "3", "4"], the number
 * "1" is allowed to appear twice in the list of resulting numbers after three
 * spaces are added: "3|1|4|1".
 *
 * If no number of spaces to be added exists such that all resulting numbers are
 * found in the list of integers, the function should return -1.
 */

package com.algo.ae.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumbersInPi {
    public static int numbersInPi(String pi, String[] numbers) {
        int [] dp = new int[pi.length() + 1];
        Arrays.fill(dp, pi.length() + 1);
        dp[0] = 0;

        Set<String> set = new HashSet<>();
        for (String num : numbers) {
            set.add(num);
        }

        for (int i = 1; i <= pi.length(); ++i) {
            for (int j = i - 1; j >= 0; --j) {
                if (dp[j] >= 0 && set.contains(pi.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[pi.length()] == (pi.length() + 1) ? -1 : dp[pi.length()] - 1;
    }

    public static void main(String[] args) {
        String pi = "3141592";
        String[] numbers = {"3141", "5", "31", "2", "4159", "9", "42"};
        int minSpaces = numbersInPi(pi, numbers);
        System.out.println("Minimum number of spaces required : " + minSpaces);
    }
}
