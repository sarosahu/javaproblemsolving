package com.algo.epi.primitive;

/**
 * Compute X * Y without multiply or add.
 *
 * Write a program that multiplies 2 non-negative integers. The only operators
 * you are allowed to use are assignment and the bitwise operators, i.e. >>, <<,
 * |, &, ~, ^ . You can't use increment or decrement. You may use loops, conditionals,
 * and functions that you write yourself. You may use equality checks, boolean
 * combinations thereof, and nothing else in your conditional expression.
 * e.g. you can't use x < y.
 *
 *
 */
public class MultiplyTwoNumbers {
    public static long multiplyNoOperator(long x, long y) {
        long sum = 0;
        while (x != 0) {
            // Examine each bit of x
            if ((x & 1) == 1) {
                sum = addNoOperator(sum, y);
            }
            x >>= 1;
            y <<= 1;
        }
        return sum;
    }

    private static long addNoOperator(long a, long b) {
        long tempA = a, tempB = b, k = 1, sum = 0, carryIn = 0;
        while (tempA != 0 || tempB != 0) {
            long ak = a & k;
            long bk = b & k;
            sum |= (ak ^ bk ^ carryIn);
            long carryOut = (ak & bk) | (ak & carryIn) | (bk & carryIn);
            carryIn = carryOut << 1;
            k <<= 1;
            tempA >>= 1;
            tempB >>= 1;
        }
        return sum ^ carryIn;
    }

    public static void main(String[] args) {
        long x = 19, y = 20;
        long result = multiplyNoOperator(x, y);
        System.out.println("Multiplication result for " + x + " and " + y + " is : " + result);
    }
}
