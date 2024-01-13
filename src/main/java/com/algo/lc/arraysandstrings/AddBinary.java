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

    //This logic works perfectly if both inputs are +ve numbers
    // Or may be if both inputs are -ve numbers
    public static int getSum(int a, int b) {
        int sum = 0, tempA = a, tempB = b, carryIn = 0, k = 1;
        while (tempA != 0 || tempB != 0) {
            int ak = (a & k);
            int bk = (b & k);
            sum |= (ak ^ bk ^ carryIn);
            int carryOut = (ak & bk) | (ak & carryIn) | (bk & carryIn);
            carryIn = carryOut << 1;
            k <<= 1;
            tempA >>= 1;
            tempB >>= 1;
        }
        return sum | carryIn;
    }

    public static int getSum2(int a, int b) {
        int x = Math.abs(a), y = Math.abs(b);
        // Ensure that abs(a) >= abs(b)
        if (x < y) {
            return getSum2(b, a);
        }

        // a determines the sign
        int sign = a > 0 ? 1 : -1;
        if (a > 0 && b > 0 || a < 0 && b < 0) {
            // Sum of 2 positive inteters x + y
            while (y != 0) {
                int answer = x ^ y;
                int carry = (x & y) << 1;
                x = answer;
                y = carry;
            }
        } else {
            // Diff of 2 positive integers x - y
            while (y != 0) {
                int answer = x ^ y;
                int borrow = ((~x) & y) << 1;
                x = answer;
                y = borrow;
            }
        }
        return sign == 1 ? x : -x;
    }

    // This is super easy solution
    // Java internally takes care of -ve integers, hence we can use the carry approach
    // i.e. x&y << 1
    public static int getSum3(int a, int b) {
        while (b != 0) {
            int answer = a ^ b;
            int carry = (a & b) << 1;
            a = answer;
            b = carry;
        }

        return a;
    }

    public static void main(String[] args) {
        String result = addBinary("110010", "10111");
        System.out.println("Result : " + result);

        int a = 987, b = 13;
        int sum = getSum(a, b);
        System.out.println("Sum of " + a + "and " + b + "is : " + sum);

        sum = getSum2(a, b);
        System.out.println("Sum of " + a + "and " + b + "is : " + sum);

        sum = getSum3(a, b);
        System.out.println("Sum of " + a + "and " + b + "is : " + sum);

        b = -13;
        sum = getSum2(a, b);
        System.out.println("Sum of " + a + "and " + b + "is : " + sum);

        sum = getSum3(a, b);
        System.out.println("Sum of " + a + "and " + b + "is : " + sum);

        a = -987;
        sum = getSum2(a, b);
        System.out.println("Sum of " + a + "and " + b + "is : " + sum);

        sum = getSum3(a, b);
        System.out.println("Sum of " + a + "and " + b + "is : " + sum);

        b = 13;
        sum = getSum2(a, b);
        System.out.println("Sum of " + a + "and " + b + "is : " + sum);

        sum = getSum3(a, b);
        System.out.println("Sum of " + a + "and " + b + "is : " + sum);
    }
}
