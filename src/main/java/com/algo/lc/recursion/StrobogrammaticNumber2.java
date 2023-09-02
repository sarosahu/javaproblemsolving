package com.algo.lc.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StrobogrammaticNumber2 {
    static char[][] reversiblePairs = {
            {'0', '0'},
            {'1', '1'},
            {'6', '9'},
            {'8', '8'},
            {'9', '6'}
    };

    public List<String> findStrobogrammatic(int n) {
        Queue<String> queue = new LinkedList<>();
        int currStringsLen;
        // When n is even, it means when decreasing by 2 we will go till 0.
        if (n % 2 == 0) {
            // We will start with 0-digit strobogrammatic numbers.
            currStringsLen = 0;
            queue.add("");
        } else {
            // We will start with 1-digit strobogrammatic numbers.
            currStringsLen = 1;
            queue.add("0");
            queue.add("1");
            queue.add("8");
        }

        while (currStringsLen < n) {
            currStringsLen += 2;
            int currLevelSize = queue.size();
            while (currLevelSize > 0) {
                String number = queue.poll();

                for (char[] pair : reversiblePairs) {
                    if (currStringsLen != n || pair[0] != '0') {
                        queue.add(pair[0] + number + pair[1]);
                    }
                }
                --currLevelSize;
            }
        }

        List<String> stroboNums = new ArrayList<>();
        while (!queue.isEmpty()) {
            stroboNums.add(queue.poll());
        }
        return stroboNums;
    }

    public static void main(String[] args) {
        StrobogrammaticNumber2 obj = new StrobogrammaticNumber2();
        List<String> numList = obj.findStrobogrammatic(3);
        System.out.println(">>>>>>>>>>>>>>>>>>");
        for (String num : numList) {
            System.out.println(num);
        }
        System.out.println("<<<<<<<<<<<<<<<<<<");
    }
}
