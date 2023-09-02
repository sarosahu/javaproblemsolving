package com.algo.ae.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Detect Arbitrage
 *
 * You're given a two-dimensional array (a matrix) of equal height and width that
 * represents the exchange rates of arbitrary currencies. The length of the array
 * is the number of currencies, and every currency can be converted to every other
 * currency. Each currency is represented by a row in the array, where values in
 * that row are the floating-point exchange rates between the row's currency and
 * all other currencies, as in the example below.
 *
 *            0:USD     1:CAD     2:GBP
 *    0:USD     1.0      1.27     0.718
 *    1:CAD    0.74       1.0      0.56
 *    2:GBP    1.39      1.77       1.0
 *
 * In the matrix above, you can see that row 0 represents USD, which means that
 * row 0 contains the exchange rates for 1 USD to all other currencies. Since
 * row 1 represents CAD, index 1 in the USD row contains the exchange for 1 USD
 * to CAD. The currency labels are listed above to help you visualize the problem,
 * but they won't actually be included in any inputs and aren't relevant to solving
 * this problem.
 *
 * Write a function that returns a boolean representing whether an arbitrage
 * opportunity exists with the given exchange rates. an arbitrage occurs if
 * you can start with C units of one currency and execute a series of exchanges
 * that lead you to having more than C units of the same currency you started with.
 *
 * Note: Currency exchange rates won't represent real-world exchange rates, and
 * there might be multiple ways to generate an arbitrage.
 *
 * Sample Input:
 *
 * exchangeRates = [
    [1, 0.8631, 0.5903],
    [1.1586, 1, 0.6849],
    [1.6939, 1.46, 1]
 * ]
 *
 * Sample Output: true
 */
public class DetectArbitrage {

    // Time: O(N^3) | Space: O(N^2) -- N is the number of currencies
    public boolean detectArbitrage(ArrayList<ArrayList<Double>> exchangeRates) {
        // To use exchange rates as edge weights, we must be able to add them.
        // Since log(a*b) = log (a) + log (b), we can convert all rates to
        // -log10(rate) to use them as edge weights.
        ArrayList<ArrayList<Double>> logExchangeRates = convertToLogMatrix(exchangeRates);

        // A negative weight cycle indicates an arbitrage.
        return foundNegativeWeightCycle(logExchangeRates, 0);
    }

    // Runs the Bellman-Ford Algorithm to detect any negative-weight cycles.
    public boolean foundNegativeWeightCycle(ArrayList<ArrayList<Double>> graph, int start) {
        double[] distanceFromStart = new double[graph.size()];
        Arrays.fill(distanceFromStart, Double.MAX_VALUE);
        distanceFromStart[start] = 0;

        for (int i = 0; i < graph.size(); ++i) {
            // If no updates => no -ve cycle
            if (!relaxEdgesAndUpdateDistance(graph, distanceFromStart)) {
                return false;
            }
        }
        return relaxEdgesAndUpdateDistance(graph, distanceFromStart);
    }

    public boolean relaxEdgesAndUpdateDistance(ArrayList<ArrayList<Double>> graph, double[] distances) {
        boolean updated = false;

        for (int sourceIdx = 0; sourceIdx < graph.size(); sourceIdx++) {
            ArrayList<Double> edges = graph.get(sourceIdx);
            for (int destinationIdx = 0; destinationIdx < edges.size(); ++destinationIdx) {
                double edgeWeight = edges.get(destinationIdx);
                double newDistanceToDestination = distances[sourceIdx] + edgeWeight;
                if (newDistanceToDestination < distances[destinationIdx]) {
                    updated = true;
                    distances[destinationIdx] = newDistanceToDestination;
                }
            }
        }
        return updated;
    }

    public ArrayList<ArrayList<Double>>
    convertToLogMatrix(ArrayList<ArrayList<Double>> matrix) {
        ArrayList<ArrayList<Double>> newMatrix = new ArrayList<>();

        for (int row = 0; row < matrix.size(); ++row) {
            ArrayList<Double> rates = matrix.get(row);
            newMatrix.add(new ArrayList<Double>());
            for (Double rate : rates) {
                newMatrix.get(row).add(-Math.log10(rate));
            }
        }
        return newMatrix;
    }
}
