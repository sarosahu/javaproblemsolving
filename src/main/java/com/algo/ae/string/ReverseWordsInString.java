package com.algo.ae.string;

import java.util.Arrays;

/**
 * Reverse Words In  String
 *
 * Write a function that takes in a string of words separated by one or more whitespaces
 * and returns a string that has these words in reverse order. For example, given the
 * string "tim is great", your function should return "great is tim".
 *
 * For this problem, a word can contain special characters, punctuation and numbers. The
 * words in the string will be separated by one or more whitespaces, and the reversed
 * string must contain the same whitespaces as the original string. For example, given
 * the string "whitespaces    4", you would be expected to return "4    whitespaces".
 *
 * Note that you're not allowed to use any built-in split or reverse methods/functions.
 * However, you are allowed to use a built-in join method/function.
 *
 * Also note that the input string isn't guaranteed to always contain words.
 *
 * Sample Input: string = "Nature is best!"
 *
 * Sample Output: "best! is Nature"
 */
public class ReverseWordsInString {
    public String reverseWordsInString(String string) {
        StringBuilder revSb = reverseString(new StringBuilder(string), 0, string.length() - 1);
        int beginIdx = -1, endIdx = -1;
        for (int i = 0; i < revSb.length(); ++i) {
            char c = revSb.charAt(i);
            if (!Character.isWhitespace(c)) {
                if (beginIdx != -1) {
                    endIdx = i;
                } else {
                    beginIdx = i;
                }
            } else {
                reverseString(revSb, beginIdx, endIdx);
                endIdx = -1;
                beginIdx = -1;
            }
        }
        if (beginIdx != -1 && endIdx != -1) {
            reverseString(revSb, beginIdx, endIdx);
        }
        return revSb.toString();
    }

    public StringBuilder reverseString(StringBuilder sb, int beginIdx, int endIdx) {
        //StringBuilder sb = new StringBuilder();
        while (beginIdx < endIdx) {
            char c = sb.charAt(beginIdx);
            sb.setCharAt(beginIdx, sb.charAt(endIdx));
            sb.setCharAt(endIdx, c);
            ++beginIdx;
            --endIdx;
        }
        return sb;
    }

    public static String reverseWordsInString2(String string) {
        char[] chars = string.toCharArray();
        reverseListRange(chars, 0, chars.length - 1);

        int startOfWord = 0;
        while (startOfWord < chars.length) {
            int endOfWord = startOfWord;
            while (endOfWord < chars.length && !Character.isWhitespace(chars[endOfWord])) {
                ++endOfWord;
            }
            reverseListRange(chars, startOfWord, endOfWord - 1);
            startOfWord = endOfWord + 1;
        }

        return new String(chars);
    }

    public static void reverseListRange(char [] str, int start, int end) {
        while (start < end) {
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            ++start;
            --end;
        }
    }
}
