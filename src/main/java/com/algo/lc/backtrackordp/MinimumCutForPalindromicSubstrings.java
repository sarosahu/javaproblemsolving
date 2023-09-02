package com.algo.lc.backtrackordp;

public class MinimumCutForPalindromicSubstrings {
    public static int
    minimumCut(String s) {
        // Backtracking, Time: O(N.2^N)
        return findMinCut(s, 0, s.length() - 1, s.length() - 1);
    }

    private static int
    findMinCut(String s, int start, int end, int minCutVal) {
        if (start == end || isPalindrom(s, start, end)) {
            return 0;
        }

        for (int currEndIndex = start; currEndIndex <= end; ++currEndIndex) {
            if (isPalindrom(s, start, currEndIndex)) {
                minCutVal = Math.min(minCutVal,
                        1 + findMinCut(s, currEndIndex + 1, end, minCutVal));
            }
        }
        return minCutVal;
    }

    private static boolean
    isPalindrom(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    // Dynamic programming, bottom up approach
    public static int
    minimumCutDP(String s) {
        int [] cutsDp = new int[s.length()];
        boolean[][] palindromeDp = new boolean[s.length()][s.length()];
        for (int end = 0; end < s.length(); ++end) {
            int minimumCut = end;
            for (int start = 0; start <= end; ++start) {
                // Check if substring (start, end) is a palindrome
                if (s.charAt(start) == s.charAt(end) &&
                        (end - start <= 2 || palindromeDp[start + 1][end - 1])) {
                    palindromeDp[start][end] = true;
                    minimumCut = start == 0 ? 0 :
                            Math.min(minimumCut, 1 + cutsDp[start - 1]);
                }
            }
            cutsDp[end] = minimumCut;
        }
        return cutsDp[s.length() - 1];
    }

    public static void main(String[] args) {
        String s = "aab";
        int minCutVal = minimumCut(s);
        int minCutVal2 = minimumCutDP(s);
        System.out.println("Minimum cut required is : " + minCutVal + " " + minCutVal2);
    }
}
