package com.algo.lc.other;

/**
 *
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder longest = new StringBuilder(strs[0]);
        for (int i = 1; i < strs.length; ++i) {
            String curr = strs[i];
            evaluateCommon(longest, curr);
            if (longest.length() == 0) {
                break;
            }
        }
        return longest.toString();
    }

    private void evaluateCommon(StringBuilder longestSoFar, String str) {
        int commonIndex = -1;
        for (int i = 0; i < Math.min(str.length(), longestSoFar.length()); ++i) {
            if (longestSoFar.charAt(i) != str.charAt(i)) {
                //commonIndex = i - 1;
                break;
            }
            commonIndex = i;
        }

        longestSoFar.delete(commonIndex + 1, longestSoFar.length());

    }

    public static void main(String[] args) {
        LongestCommonPrefix obj = new LongestCommonPrefix();
        //String[] strs = {"dog","racecar","car"};
        String[] strs = {"flower","flow","flight"};
        String common = obj.longestCommonPrefix(strs);
        System.out.println("Common : " +common);
    }
}
