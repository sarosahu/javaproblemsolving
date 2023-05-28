package com.algo.lc.arraysandstrings;

public class MultiplyTwoNumberInString {
    private static boolean isNegative(String digit) {
        return digit.length() >= 1 && digit.charAt(0) == '-';
    }

    /**
     * Complexity Analysis
     *
     * Here N and M are the number of digits in num1 and num2 respectively.
     *
     * Time complexity: O(M⋅N)
     *
     * During multiplication, we perform N operations for each of the M digits of the second number,
     * so we need M⋅N time for it.
     *
     * Space complexity: O(M+N)
     *
     * The space used to store the output is not included in the space complexity.
     * However, because strings are immutable in Python, Java, and Javascript, a
     * temporary data structure, using O(M+N) space, is required to store the answer while it is updated.
     *
     * On the other hand, in C++, strings are mutable, so we do not need a temporary data structure to
     * store answer and can update answer directly. Thus, the C++ approach is a constant space solution.
     */
    public static String multiply(String num1, String num2) {
        boolean isNegative1 = isNegative(num1);
        num1 = isNegative1 ? num1.substring(1) : num1;
        boolean isNegative2 = isNegative(num2);
        num2 = isNegative2 ? num2.substring(1) : num2;
        boolean isResultNegative = (!isNegative1 && isNegative2) || (isNegative1 && !isNegative2);
        int extraSpaceForNegative = isResultNegative ? 1 : 0;

        StringBuilder sb1 = new StringBuilder(num1);
        sb1.reverse();
        StringBuilder sb2 = new StringBuilder(num2);
        sb2.reverse();

        int [] res = new int[sb1.length() + sb2.length()];
        for (int i = 0; i < sb1.length(); ++i) {
            int x = sb1.charAt(i) - '0';
            for (int j = 0; j < sb2.length(); ++j) {
                int y = sb2.charAt(j) - '0';
                int n = x * y;
                res[i + j] += n;
                res[i + j + 1] += res[i + j] / 10;
                res[i + j] %= 10;
            }
        }
        // Find out the trailing zeros
        int numTrailingZeros = 0;
        for (int i = res.length - 1; i >= 0; --i) {
            if (res[i] == 0) {
                ++numTrailingZeros;
            } else {
                break;
            }
        }
        if (numTrailingZeros == res.length) {
            return "0";
        }
        char[] result = new char[res.length - numTrailingZeros + extraSpaceForNegative];
        int k = 0;
        if (isResultNegative) {
            result[k++] = '-';
        }
        for (int i = res.length - numTrailingZeros - 1; i >= 0; --i) {
            result[k++] = (char)('0' + res[i]);
        }
        String finalResult = String.valueOf(result);
        return finalResult;
    }
    public static void main(String[] args) {
        String res = multiply("123", "-456");
        System.out.println("Result is : " + res);
    }
}
