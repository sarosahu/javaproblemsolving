package com.algo.ae.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * There is an algorithms tournament taking place in which teams of programmers
 * compete against each other to solve algorithmic problems as fast as possible.
 * Teams compete in a round robin, where each team faces off against all other
 * teams. Only 2 teams compete against each other at a time, and for each
 * competition, one team is designated the home team, while other team is the
 * away team. In each competition there's always one winner and one loser;
 * there are no ties. A team receives 3 points if it wins and 0 points if it
 * loses. The winner of the tournament is the team that receives the most
 * amount of points.
 *
 * Given an array of pairs representing the teams that have competed against
 * each other and an array containing the results of each  competition, write
 * a function that returns the winner of the tournament. The input arrays are
 * named competitions and results, respectively. The competitions array has
 * elements in the form of [homeTeam, awayTeam], where each team is a string
 * of at most 30 characters representing the name of the team. The results
 * array contains information about the winner of each corresponding competition
 * in the competitions array. Specifically, results[i]  denotes the winner
 * of competitions[i], where 1 in the results array means that the home team
 * in the corresponding competition won and a 0 means that the away team won.
 *
 * It's guaranteed that exactly one team will win the tournament and that each
 * team will compete against all other teams exactly once. It's also guaranteed
 * that the tournament will always have at least two teams.
 *
 * Sample Input:
 *
 * competitions = [
 *     ["HTML", "C#"],
 *     ["C#", "Python"],
 *     ["Python", "HTML"]
 *   ],
 *   results: [0, 0, 1]
 *
 *   Sample Output: "Python"
 */
public class TournamentWinner {
    // Time: O(n), space : O(k) where n is the number of competitions and k is number of teams.
    public String tournamentWinner(
            ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
        // Write your code here.
        String finalWinner = "";
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put(finalWinner, 0);
        for (int i = 0; i < competitions.size(); ++i) {
            ArrayList<String> competition = competitions.get(i);
            String homeTeam = competition.get(0);
            String awayTeam = competition.get(1);

            String winningTeam = results.get(i).equals(1) ? homeTeam : awayTeam;

            updateScore(hashMap, winningTeam, 3);

            if (hashMap.get(winningTeam) > hashMap.get(finalWinner)) {
                finalWinner = winningTeam;
            }
        }
        return finalWinner;
    }

    private void updateScore(Map<String, Integer> scores, String winner, int score) {
        if (!scores.containsKey(winner)) {
            scores.put(winner, 0);
        }
        scores.put(winner, scores.get(winner) + score);
    }
}
