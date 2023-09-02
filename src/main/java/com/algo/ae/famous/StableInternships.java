package com.algo.ae.famous;

import java.util.*;

/**
 * A company has hired N interns to each join one of N different teams.
 * Each interna has ranked their preferences for which teams they wish
 * to join, and each team has ranked their preferences for which interns
 * they prefer.
 *
 * Given these preferences, assign 1 intern to each team. These assignments
 * should be "stable", meaning that there is no unmatched pair of an intern
 * and a team such that both that intern and that team would prefer they
 * be matched with each other.
 *
 * In the case there are multiple valid stable matchings, the solution that
 * is most optimal for the interns should be chosen (i.e. every intern
 * should be matched with the best team possible for them).
 *
 * Your function should take in 2 2-dimensional lists, one for interns and
 * one for teams. Each inner list represents a single intern or team's
 * preferences, ranked from most preferable to least preferable. These
 * lists will always be of length N, with integers as elements. Each of
 * these integers corresponds to the index of the team/intern being
 * ranked. Your function should return a 2-dimensional list of matchings
 * in no particular order. Each matching should be in the format
 * [internIndex, teamIndex].
 *
 * Sample Input:
 * interns = [
 *  [0, 1, 2],
 *  [1, 0, 2],
 *  [1, 2, 0]
 * ]
 *
 * teams = [
 *  [2, 1, 0],
 *  [1, 2, 0],
 *  [0, 2, 1]
 * ]
 *
 * Sample Output:
 * // This is the most optimal solution for interns
 * [
 *  [0, 0],
 *  [1, 1],
 *  [2, 2]
 * ]
 *
 * // This is also a stable matchings, but it is suboptimal
 * for the interns because interns 0 and 2 could have been
 * given better team matchings.
 * [
 *  [2, 0],
 *  [1, 1],
 *  [0, 2]
 * ]
 */
public class StableInternships {
    // Time: O(n^2), space: O(n^2) -- n is no of interns and teams
    public int[][] stableInternships(int[][] interns, int[][] teams) {
        Map<Integer, Integer> chosenInterns = new HashMap<>();
        Stack<Integer> freeInterns = new Stack<>();
        for (int i = 0; i < interns.length; ++i) {
            freeInterns.push(i);
        }
        int[] currentInternChoices = new int[interns.length];
        List<HashMap<Integer, Integer>> teamMaps = new ArrayList<>();
        for (int [] team : teams) {
            HashMap<Integer, Integer> rank = new HashMap<>();
            for (int i = 0; i < team.length; ++i) {
                rank.put(team[i], i);
            }
            teamMaps.add(rank);
        }

        while (!freeInterns.isEmpty()) {
            int internNum = freeInterns.pop();
            int[] intern = interns[internNum];
            int teamPreference = intern[currentInternChoices[internNum]];
            currentInternChoices[internNum]++;

            if (!chosenInterns.containsKey(teamPreference)) {
                chosenInterns.put(teamPreference, internNum);
                continue;
            }

            int prevIntern = chosenInterns.get(teamPreference);
            int prevInternRank = teamMaps.get(teamPreference).get(prevIntern);
            int currInternRank = teamMaps.get(teamPreference).get(internNum);

            if (currInternRank < prevInternRank) {
                freeInterns.push(prevIntern);
                chosenInterns.put(teamPreference, internNum);
            } else {
                freeInterns.push(internNum);
            }
        }

        int[][] matches = new int[interns.length][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> chosenIntern : chosenInterns.entrySet()) {
            matches[index] = new int[]{chosenIntern.getValue(), chosenIntern.getKey()};
            ++index;
        }
        return matches;
    }
}
