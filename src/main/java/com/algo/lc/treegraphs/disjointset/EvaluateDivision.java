package com.algo.lc.treegraphs.disjointset;

import com.algo.util.Pair;

import java.util.*;

/**
 * Evaluate division.
 */
public class EvaluateDivision {
    private Set<String> visited;

    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {

        Map<String, Pair<String, Double>> gidWeight = new HashMap<>();

        // Step 1). build the union groups
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String dividend = equation.get(0), divisor = equation.get(1);
            double quotient = values[i];

            union(gidWeight, dividend, divisor, quotient);
        }

        // Step 2). run the evaluation, with "lazy" updates in find() function
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String dividend = query.get(0), divisor = query.get(1);

            if (!gidWeight.containsKey(dividend) || !gidWeight.containsKey(divisor))
                // case 1). at least one variable did not appear before
                results[i] = -1.0;
            else {
                this.visited = new HashSet<>();
                Pair<String, Double> dividendEntry = find(gidWeight, dividend);
                Pair<String, Double> divisorEntry = find(gidWeight, divisor);

                String dividendGid = dividendEntry.getKey();
                String divisorGid = divisorEntry.getKey();
                Double dividendWeight = dividendEntry.getValue();
                Double divisorWeight = divisorEntry.getValue();

                if (!dividendGid.equals(divisorGid))
                    // case 2). the variables do not belong to the same chain/group
                    results[i] = -1.0;
                else
                    // case 3). there is a chain/path between the variables
                    results[i] = dividendWeight / divisorWeight;
            }
        }

        return results;
    }

    private Pair<String, Double>
    find(Map<String, Pair<String, Double>> gidWeight,
         String nodeId) {

        if (this.visited != null) {
            if (this.visited.contains(nodeId)) {
                return gidWeight.get(nodeId);
            }
            this.visited.add(nodeId);
        }
        if (!gidWeight.containsKey(nodeId)) {
            gidWeight.put(nodeId, new Pair<>(nodeId, 1.0));
        }

        Pair<String, Double> entry = gidWeight.get(nodeId);
        // found inconsistency, trigger chain update
        if (!entry.getKey().equals(nodeId)) {
            Pair<String, Double> newEntry = find(gidWeight, entry.getKey());
            gidWeight.put(nodeId, new Pair<String, Double>(
                    newEntry.getKey(), entry.getValue() * newEntry.getValue()));
        }

        return gidWeight.get(nodeId);
    }

    private void
    union(Map<String, Pair<String, Double>> gidWeight,
          String dividend,
          String divisor,
          Double value) {

        Pair<String, Double> dividendEntry = find(gidWeight, dividend);
        Pair<String, Double> divisorEntry = find(gidWeight, divisor);

        String dividendGid = dividendEntry.getKey();
        String divisorGid = divisorEntry.getKey();
        Double dividendWeight = dividendEntry.getValue();
        Double divisorWeight = divisorEntry.getValue();

        // merge the two groups together,
        // by attaching the dividend group to the one of divisor
        if (!dividendGid.equals(divisorGid)) {
            gidWeight.put(dividendGid, new Pair<>(divisorGid,
                    divisorWeight * value / dividendWeight));
        }
    }

    public static void main(String[] args) {
        List<String> eq1 = Arrays.asList("a", "b");
        List<String> eq2 = Arrays.asList("b", "c");
        List<String> eq3 = Arrays.asList("bc", "cd");
        List<String> eq4 = Arrays.asList("a", "d");
        List<List<String>> equations = Arrays.asList(eq1, eq2, eq3, eq4);
        double[] values = {1.5, 2.5, 5.0, 2.0};

        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"),
                Arrays.asList("cd", "bc")
        );
        EvaluateDivision obj = new EvaluateDivision();
        double[] results = obj.calcEquation(equations, values, queries);
        for (int i = 0; i < queries.size(); ++i) {
            List<String> query = queries.get(i);

            System.out.println(query.get(0) + "/" + query.get(1) + ":" + results[i]);
        }
    }
}
