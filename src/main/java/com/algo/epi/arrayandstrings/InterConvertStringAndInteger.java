package com.algo.epi.arrayandstrings;

/**
 * EPI String 7.1
 * Interconvert Strings and integers
 *
 * IMplement methods that take a string representing an integer and return the corresponding
 * integer and vice versa. Your code should handle -ve integers. You can't use library functions
 * like stoi() in c++ and parseInt() in java.
 */
public class InterConvertStringAndInteger {
    public String intToString(int x) {
        boolean isNeg = x < 0;
        if (isNeg) {
            x *= -1;
        }
        StringBuilder sb = new StringBuilder();
        while (x != 0) {
            sb.append(x % 10);
            x /= 10;
        }
        if (isNeg) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }

    public int stringToInt(String s) {
        int x = 0;
        boolean isNeg = false;
        for (char c : s.toCharArray()) {
            if (c == '-') {
                isNeg = true;
                continue;
            }
            x *= 10;
            x += c - '0';
        }
        if (isNeg) {
            x *= -1;
        }
        return x;
    }

    public static void main(String[] args) {
        int x = 1234;
        InterConvertStringAndInteger obj = new InterConvertStringAndInteger();
        String xstr = obj.intToString(x);
        System.out.println("String equivalent of " + x + " = " + xstr);
        x *= -1;
        xstr = obj.intToString(x);
        System.out.println("String equivalent of " + x + " = " + xstr);

        String s = "1234";
        int sint = obj.stringToInt(s);
        System.out.println("Integer equivalent of " + s + " = " + sint);

        s = "-12345";
        sint = obj.stringToInt(s);
        System.out.println("Integer equivalent of " + s + " = " + sint);
    }
}
