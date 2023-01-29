package com.algo.ae.string;

/**
 * Run-Length Encoding
 *
 * Write a function that takes in a non-empty string and returns its run-length
 * encoding.
 * From Wikipedia, "run-length encoding is a form of lossless data compression
 * in which runs of data are stored as single dta value and count, rather than
 * as the original run." For this problem, a run of data is any sequence of
 * consecutive, identical characters. So the run "AAA" would be run-length-encoded
 * as "3A".
 *
 * To make things more complicated, however, the input string can contain all sorts
 * of special characters, including numbers. And since encoded data must be
 * decodable, this means that we can't naively run-length-encode long runs.
 * For example, the run "AAAAAAAAAAAA" (12 A s), can't naively be encoded as
 * "12A", since this string can be decoded as either "AAAAAAAAAAAA" or "1AA".
 * Thus, long runs(runs of 10 or more characters) should be encoded in a
 * split fashion; the aforementioned run should be encoded as "9A3A".
 *
 * Sample Input
 * string = "AAAAAAAAAAAAABBCCCCDD"
 *
 * Sample Output: "9A4A2B4C2D"
 */
public class RunLengthEncoding {
    public String runLengthEncoding(String string) {
        if (string.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char prevChar = string.charAt(0);
        int count = 1;
        for (int i = 1; i < string.length(); ++i) {
            char currChar = string.charAt(i);
            if (currChar == prevChar && count < 9) {
                ++count;
            } else {
                sb.append(String.valueOf(count) + String.valueOf(prevChar));
                count = 1;
            }
            prevChar = currChar;
        }
        sb.append(String.valueOf(count) + String.valueOf(prevChar));

        return sb.toString();
    }

    public String runLengthEncoding2(String string) {
        if (string.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char prevChar = string.charAt(0);
        int count = 1;
        for (int i = 1; i < string.length(); ++i) {
            char currChar = string.charAt(i);
            if (currChar != prevChar || count == 9) {
                sb.append(String.valueOf(count) + String.valueOf(prevChar));
                count = 0;
            }
            prevChar = currChar;
            ++count;
        }
        if (count != 0) {
            sb.append(String.valueOf(count) + String.valueOf(prevChar));
        }

        return sb.toString();
    }
}
