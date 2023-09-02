package com.algo.ae.recursion;

/**
 * Nth Fibonacci
 *
 * The Fibonacci sequence is defined as follows: the first number of the sequence
 * is 0, the second number is 1, and the nth number is sum of the (n-1)th and
 * (n-2)th numbers. Write a function that takes in an integer n and returns the
 * nth Fibonacci number.
 *
 * Important node: the Fibonacci sequence is often defined with its first 2
 * numbers as F0 = 0 and F1 = 1. For the purpose of this question, the first
 * Fibonacci number is F0; therefore, getNthFib(1) is equal to F0, getNthFib(2)
 * is equal to F1, etc.
 *
 * Sample Input#1
 * n = 2
 *
 * Sample output#1
 * 1 // 0, 1
 *
 * Sample input#2
 * n = 6
 *
 * Sample output#2
 * 5 // 0, 1, 1, 2, 3, 5
 */
public class NthFibonacii {

    /**
     * Time : O(2^N), Space O(N)
     */
    public static int getNthFib(int n) {
        // Write your code here.
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        }
        return getNthFib(n-1) + getNthFib(n-2);
    }

    /**
     * Time : O(N), space : O(1)
     */
    public static int getNthFibE(int n) {
        // Write your code here.
        if (n == 1 || n == 2) {
            return n - 1;
        }
        int prevToPrev = 0, prev = 1;
        for (int i = 3; i <= n; ++i) {
            int curr = prevToPrev + prev;
            prevToPrev = prev;
            prev = curr;
        }
        return prev;
    }

    public static void main(String[] args) {
        System.out.println("Fibonacci number for 6 : " + getNthFib(6));
        System.out.println("Fibonacci number for 6 : " + getNthFibE(6));
    }
}
