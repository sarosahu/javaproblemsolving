package com.algo.ae.graph;

import java.util.*;

/**
 * Airport Connections
 *
 * For the purpose of this question, the phrases "airport route" and
 * "airport connection" are used interchangeably.
 *
 * You are given a list of airports (3 letter codes like "JFK"), a list
 * of routes(one-way flights from one airport to another like ["JFK", "SFO"],
 * and a starting airport.
 *
 * Write a function that returns the minimum number of airport connections (
 * one-way flights) that need to be added in order for someone to be able to
 * reach any airport in the list, starting at the starting airport.
 *
 * Note that routes only allow you to fly in one direction; for instance, the
 * route["JFK", "SFO"] only allows you to fly from "JFK" to "SFO".
 *
 * Also note that the connections don't have to be direct;it's okay if an
 * airport can only be reached from the starting airport by stopping at other
 * airports first.
 *
 * Sample Input:
 *
 * airports = [
 * "BGI", "CDG", "DEL", "DOH", "DSM", "EWR", "EYW", "HND", "ICN",
 * "JFK", "LGA", "LHR", "ORD", "SAN", "SFO", "SIN", "TLV", "BUD",
 * ]
 *
 * routes = [
 * ["DSM", "ORD"],
 * ["ORD", "BGI"],
 * ["BGI", "LGA"],
 * ["SIN", "CDG"],
 * ["CDG", "SIN"],
 * ["CDG", "BUD"],
 * ["DEL", "DOH"],
 * ["DEL", "CDG"],
 * ["TLV", "DEL"],
 * ["EWR", "HND"],
 * ["HND", "ICN"],
 * ["HND", "JFK"],
 * ["ICN", "JFK"],
 * ["JFK", "LGA"],
 * ["EYW", "LHR"],
 * ["LHR", "SFO"],
 * ["SFO", "SAN"],
 * ["SFO", "DSM"],
 * ["SAN", "EYW"],
 * ]
 *
 * startingAirport = "LGA"
 *
 * Sample output: 3 ["LGA", "TLV"], ["LGA", "SFO"], and ["LGA", "EWR"]
 */
public class AirportConnections {
    // O(a * (a + r) + a + r + alog(a)) time | O(a + r) space -- a is no of airports
    // r is no of routes.
    public static int airportConnections(List<String> airports,
                                         List<List<String>> routes,
                                         String startingAirport) {
        Map<String, AirportNode> airportGraph =
                createAirportGraph(airports, routes);
        List<AirportNode> unreachableAirportNodes =
                getUnreachableAirportNodes(airportGraph, airports, startingAirport);
        markUnreachableConnections(airportGraph, unreachableAirportNodes);
        return getMinNumberOfNewConnections(airportGraph, unreachableAirportNodes);
    }

    // O(a + r) time | O(a + r) space
    public static Map<String, AirportNode>createAirportGraph(List<String> airports,
                                                             List<List<String>> routes) {
        Map<String, AirportNode> airportGraph = new HashMap<>();
        for (String airport : airports) {
            airportGraph.put(airport, new AirportNode(airport));
        }

        for (List<String> route : routes) {
            String airport = route.get(0);
            String connection = route.get(1);
            airportGraph.get(airport).connections.add(connection);
        }
        return airportGraph;
    }

    // O(a + r) time | O(a) space
    public static List<AirportNode>
    getUnreachableAirportNodes(Map<String, AirportNode> airportGraph,
                               List<String> airports,
                               String startingAirport) {
        Set<String> visitedAirports = new HashSet<>();
        depthFirstTraverseAirports(airportGraph, startingAirport, visitedAirports);

        List<AirportNode> unreachableAirportNodes = new ArrayList<>();
        for (String airport : airports) {
            if (visitedAirports.contains(airport)) {
                continue;
            }
            AirportNode airportNode = airportGraph.get(airport);
            airportNode.isReachable = false;
            unreachableAirportNodes.add(airportNode);
        }
        return unreachableAirportNodes;
    }

    public static void depthFirstTraverseAirports(Map<String, AirportNode> airportGraph,
                                                  String airport,
                                                  Set<String> visitedAirports) {
        if (visitedAirports.contains(airport)) {
            return;
        }
        visitedAirports.add(airport);
        List<String> connections = airportGraph.get(airport).connections;
        for (String connection : connections) {
            depthFirstTraverseAirports(airportGraph, connection, visitedAirports);
        }
    }

    // O(a * (a + r)) time, O(a) space
    public static void markUnreachableConnections(Map<String, AirportNode> airportGraph,
                                                  List<AirportNode> unreachableAirportNodes)
    {
        for (AirportNode airportNode : unreachableAirportNodes) {
            String airport = airportNode.airport;
            List<String> unreachableConnections = new ArrayList<>();
            Set<String> visitedAirports = new HashSet<>();
            depthFirstAddUnreachableConnections(airportGraph, airport,
                    unreachableConnections,
                    visitedAirports);
            airportNode.unreachableConnections = unreachableConnections;
        }
    }

    public static void depthFirstAddUnreachableConnections(Map<String, AirportNode> airportGraph,
                                                           String airport,
                                                           List<String> unreachableConnections,
                                                           Set<String> visitedAirports)
    {
        if (airportGraph.get(airport).isReachable) {
            return;
        }
        if (visitedAirports.contains(airport)) {
            return;
        }
        visitedAirports.add(airport);
        unreachableConnections.add(airport);
        List<String> connections = airportGraph.get(airport).connections;
        for (String connection : connections) {
            depthFirstAddUnreachableConnections(airportGraph, connection,
                    unreachableConnections, visitedAirports);
        }
    }

    // O(alog(a) + a + r) time, Space : O(1)
    public static int getMinNumberOfNewConnections(Map<String, AirportNode> airportGraph,
                                                   List<AirportNode> unreachableAirportNodes)
    {
        unreachableAirportNodes.sort(
                (a1, a2) -> a2.unreachableConnections.size() - a1.unreachableConnections.size()
        );
        int numNewConnections = 0;
        for (AirportNode airportNode : unreachableAirportNodes) {
            if (airportNode.isReachable) {
                continue;
            }
            ++numNewConnections;
            for (String connection : airportNode.unreachableConnections) {
                airportGraph.get(connection).isReachable = true;
            }
        }
        return numNewConnections;
    }

    static class AirportNode {
        String airport;
        List<String> connections;
        boolean isReachable;
        List<String> unreachableConnections;

        public AirportNode(String airport) {
            this.airport = airport;
            this.connections = new ArrayList<>();
            this.isReachable = true;
            this.unreachableConnections = new ArrayList<>();
        }
    }
}
