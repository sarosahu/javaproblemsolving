package com.algo.ae.greedyalgo;

/**
 * Valid Starting city
 *
 * Imagine you have a set of cities that are laid out in a circle, connected by
 * a circular road that runs clockwise. Each city has a gas station that provides
 * gallons of fuel, and each city is some distance away from the next city.
 *
 * You have a car that can drive some number of miles per gallon of fuel, and your
 * goal is to pick a starting city such that you can fill up your car with that
 * city's fuel, drive to the next city, refill up your car with that city's fuel,
 * drive to the next city, and so on and so forth until you return back to the
 * starting city with 0 or more gallons of fuel left.
 *
 * This city is called a valid starting city, and it's guaranteed that there will
 * always be exactly one valid starting city.
 *
 * For the actual problem, you'll be given an array of distances such that city i
 * is distances[i] away from city i + 1. Since the cities are connected via a
 * circular road, the last city is connected to the first city. In other words,
 * the last distance in the distances array is equal to the distance from the last
 * city to the first city. You will also be given an array of fuel available at
 * each city, where fuel[i] is equal to the fuel availability at city i. The total
 * amount of fuel available (from all cities combined) is exactly enough to travel
 * to all cities. Your fuel tank always starts out empty, and you are given a
 * positive integer value for the number of miles that your car can travel per
 * gallon of fuel (miles per gallon, or MEG). You can assume that you will always
 * be given at least two cities.
 *
 * Write a function that returns the index of the valid start city.
 *
 * Sample input:
 * distances = [5, 25, 15, 10, 15]
 * mpg = 10
 *
 * Sample output: 4
 */
public class ValidStartingCity {
    // BF method, time: O(N^2)
    public int validStartingCityBF(int[] distances, int[] fuel, int mpg) {
        int numCities = distances.length;

        for (int startCityIdx = 0; startCityIdx < numCities; ++startCityIdx) {
            int milesRemaining = 0;

            for (int currCityIdx = startCityIdx;
                 currCityIdx < startCityIdx + numCities; ++currCityIdx) {
                if (milesRemaining < 0) {
                    continue;
                }

                int currCityIdxRotated = currCityIdx % numCities;
                int fuelFromCurrCity = fuel[currCityIdxRotated];
                int distanceToNextCity = distances[currCityIdxRotated];
                milesRemaining += fuelFromCurrCity * mpg - distanceToNextCity;
            }
            if (milesRemaining >= 0) {
                return startCityIdx;
            }
        }

        return -1;
    }

    // Greedy approach, Time: O(N) , Space : O(1)
    public int validStartingCity(int[] distances, int[] fuel, int mpg) {
        int numCities = distances.length;
        int milesRemaining = 0;

        int indexOfStartCity = 0;
        int milesRemainingAtStartCity = 0;

        for (int cityIdx = 1; cityIdx < numCities; ++cityIdx) {
            int distanceFromPrevCity = distances[cityIdx - 1];
            int fuelFromPrevCity = fuel[cityIdx - 1];
            milesRemaining += fuelFromPrevCity * mpg - distanceFromPrevCity;

            if (milesRemaining < milesRemainingAtStartCity) {
                milesRemainingAtStartCity = milesRemaining;
                indexOfStartCity = cityIdx;
            }
        }
        return indexOfStartCity;
    }
}
