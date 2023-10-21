package com.algo.lc.arraysandstrings;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 */
public class AddBinary {

    public static String addBinary(String a, String b) {
        int i = a.length(), j = b.length();
        StringBuilder res = new StringBuilder();
        int carry = 0;
        while (i > 0 || j > 0) {
            int sum = carry;
            if (i > 0) {
                sum += a.charAt(i - 1) - '0';
            }
            if (j > 0) {
                sum += b.charAt(j - 1) - '0';
            }
            carry = sum > 1 ? 1 : 0;
            res.append(sum % 2);
            --i;
            --j;
        }


        if (carry > 0) {
            res.append('1');
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String result = addBinary("110010", "10111");
        System.out.println("Result : " + result);
    }
}
