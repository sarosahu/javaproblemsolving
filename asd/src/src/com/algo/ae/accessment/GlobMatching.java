package com.algo.ae.accessment;

public class GlobMatching {
    public static boolean globMatching(String fileName, String pattern) {
        boolean[][] dp = new boolean[pattern.length() + 1][fileName.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; ++i) {
            dp[i][0] = false;
        }

        for (int i = 1; i < dp[0].length; ++i) {
            dp[0][i] = false;
        }

        for (int i = 1; i < dp.length; ++i) {
            char c1 = pattern.charAt(i - 1);
            for (int j = 1; j < dp[i].length; ++j) {
                char c2 = fileName.charAt(j - 1);
                if (c2 == '*') {
                    if (dp[i - 1][j] == true || dp[i][j - 1] == true || dp[i - 1][j - 1] == true) {
                        dp[i][j] = true;
                    }
                } else if (c2 == '?') {
                    if (dp[i - 1][j - 1] == true) {
                        dp[i][j] = true;
                    }
                } else {
                    if (c1 == c2) {
                        dp[i][j] = dp[i - 1][j - 1] == true ? true : false;
                    }
                }
            }
        }
        return dp[pattern.length()][fileName.length()];
    }

    public static void main(String[] args) {
        boolean isMatching = globMatching("abcdefg", "a*e?g");
        if (isMatching) {
            System.out.println("Matching");
        } else {
            System.out.println("Not matching");
        }
    }
}
