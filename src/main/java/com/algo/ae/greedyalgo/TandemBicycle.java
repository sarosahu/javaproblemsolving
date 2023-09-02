package com.algo.ae.greedyalgo;

import java.util.Arrays;

/**
 * Tandem Bicycle
 *
 * A tandem bicycle is a bicycle that is operated by 2 people: person A and person B.
 * Both people pedal the bicycle, but the person that pedals faster dictates the
 * speed of the bicycle. So if person A pedals at a speed of 5, and person B pedals
 * at a speed of 4, the tandem bicycle moves at a speed of 5 (i.e. tandemSpeed =
 * max(speedA, speedB).
 *
 * You're given 2 list of +ve integers: one that contains the speeds of riders
 * wearing red shirts and one that contains the speeds of riders wearing blue
 * shirts. Each rider is represented by a single +ve integer, which is the speed
 * that they pedal a tandem bicycle at. Both lists have the same length, meaning
 * that there are as many red-shirt riders as there are blue-shirt riders. Your
 * goal is to pair every rider wearing a red shirt with a rider wearing a blue
 * shirt to operate a tandem bicycle.
 *
 * Write a function that returns the maximum possible total speed or the minimum
 * possible total speed of all of the tandem bicycle being ridden based on an input
 * parameter, fastest. If fastest = true, your function should return the maximum
 * possible total speed; otherwise it should return the minimum total speed.
 *
 * "Total speed" is defined as the sum of the speeds of all the tandem bicycles
 * being ridden. For example, if there are 4 riders (2 red-shirt riders and 2 blue
 * shirt riders) who have speeds of 1, 3, 4, 5 and if they're paired on tandem
 * bicycles as follows: [1, 4], [5, 3], then the total speed of these tandem
 * bicycles is 4 + 5 = 9.
 *
 * Sample Input:
 * redShirtSpeeds = [5, 5, 3, 9, 2]
 * blueShirtSpeeds = [3, 6, 7, 2, 1]
 * fastest = true
 *
 * Sample Output: 32
 */
public class TandemBicycle {
     // Time: O(nlog(n)), space : O(1) -- where n is no of tandem bicycles
    public int tandemBicycle(int[] redShirtSpeeds, int[] blueShirtSpeeds, boolean fastest) {
        Arrays.sort(redShirtSpeeds);
        Arrays.sort(blueShirtSpeeds);
        int result = 0;
        for(int i = 0; i < redShirtSpeeds.length; ++i) {
            if (fastest) {
                result += Math.max(redShirtSpeeds[i], blueShirtSpeeds[redShirtSpeeds.length-1-i]);
            } else {
                result += Math.max(redShirtSpeeds[i], blueShirtSpeeds[i]);
            }
        }
        return result;
    }
}
