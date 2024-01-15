package com.algo.epi.primitive;

/**
 * Compute x/y
 * Given 2 positive integers, compute their quotient, using only the addition,
 * subtraction, and shifting operators.
 */
public class DivideXY {
    public static int divideXY(int x, int y) {
        int result = 0;
        int power = 32;
        long yPower = (long) y << power;
        while (x >= y) {
            while (yPower > x) {
                yPower >>= 1;
                --power;
            }
            result += 1 << power;
            x -= yPower;
        }
        return result;
    }

    public static void main(String[] args) {
        int x = 1000, y = 24;
        int quotient = divideXY(x, y);
        System.out.println("Quotient : " + quotient);
    }
}
