package com.algo.lc.treegraphs;

import java.util.*;

/**
 * 721. Accounts Merge (https://leetcode.com/problems/accounts-merge/description/)
 *
 * Given a list of accounts where each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name, and the rest of the elements
 * are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to
 * the same person if there is some common email to both accounts. Note that even
 * if two accounts have the same name, they may belong to different people as people
 * could have the same name. A person can have any number of accounts initially, but
 * all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first
 * element of each account is the name, and the rest of the elements are emails in
 * sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 *
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 *
 * Example 2:
 *
 * Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 *
 *
 * Constraints:
 *
 * 1 <= accounts.length <= 1000
 * 2 <= accounts[i].length <= 10
 * 1 <= accounts[i][j].length <= 30
 * accounts[i][0] consists of English letters.
 * accounts[i][j] (for j > 0) is a valid email.
 */
public class AccountsMerge {
    private HashSet<String> visited = new HashSet<>();
    private Map<String, List<String>> adjacent = new HashMap<>();

    // { DFS approach
    // Here NNN is the number of accounts and KKK is the maximum length of an account.
    // Time: O(NKlogNK), Space: O(NK)
    public List<List<String>> accountsMergeDfs(List<List<String>> accounts) {
        int accountListSize = accounts.size();

        for (List<String> account : accounts) {
            int accountSize = account.size();

            // Build adjacency list
            // Add edge between first email and all other emails in the account
            String firstEmail = account.get(1);
            if (!adjacent.containsKey(firstEmail)) {
                adjacent.put(firstEmail, new ArrayList<>());
            }
            for (int j = 2; j < accountSize; ++j) {
                String accountEmail = account.get(j);

                adjacent.get(firstEmail).add(accountEmail);
                if (!adjacent.containsKey(accountEmail)) {
                    adjacent.put(accountEmail, new ArrayList<>());
                }
                adjacent.get(accountEmail).add(firstEmail);
            }
        }

        // Traverse over all the accounts to store components
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (List<String> account : accounts) {
            String accountName = account.get(0);
            String firstEmail = account.get(1);

            if (!visited.contains(firstEmail)) {
                List<String> mergedAccount = new ArrayList<>();
                // Add account name at the 0th index
                mergedAccount.add(accountName);

                dfs(mergedAccount, firstEmail);
                Collections.sort(mergedAccount.subList(1, mergedAccount.size()));
                mergedAccounts.add(mergedAccount);
            }
        }

        return mergedAccounts;
    }
    private void dfs(List<String> mergedAccount, String email) {
        visited.add(email);
        mergedAccount.add(email);

        if (!adjacent.containsKey(email)) {
            return;
        }
        for (String neighbor : adjacent.get(email)) {
            if (!visited.contains(neighbor)) {
                dfs(mergedAccount, neighbor);
            }
        }
    }
    // }

    // { Using disjoint set (Union find) approach
    public List<List<String>> accountsMergeDset(List<List<String>> accountList) {
        int accountListSize = accountList.size();
        UnionFind uf = new UnionFind(accountListSize);

        // Maps emails to their component index
        Map<String, Integer> emailGroup = new HashMap<>();
        for (int i = 0; i < accountListSize; ++i) {
            List<String> account = accountList.get(i);
            //String accountName = account.get(0);
            int accountSize = account.size();
            for (int j = 1; j < accountSize; ++j) {
                String email = account.get(j);

                // If this is the first time seeing this email then
                // assign component group as the account index.
                if (!emailGroup.containsKey(email)) {
                   emailGroup.put(email, i);
                } else {
                    // If we have seen this email before then union this
                    // group with the previous group of the email.
                    uf.union(i, emailGroup.get(email));
                }
            }
        }

        // Store emails corresponding to the component's representative
        Map<Integer, List<String>> components = new HashMap<>();
        for (String email : emailGroup.keySet()) {
            int group = emailGroup.get(email);
            int groupRep = uf.find(group);
            if (!components.containsKey(groupRep)) {
                components.put(groupRep, new ArrayList<>());
            }
            components.get(groupRep).add(email);
        }

        // Sort the components and add the name
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (int group : components.keySet()) {
            List<String> component = components.get(group);
            component.sort(Comparator.reverseOrder());
            String accountName = accountList.get(group).get(0);
            component.add(accountName);
            Collections.reverse(component);
            mergedAccounts.add(component);
        }

        return mergedAccounts;
    }

    static class UnionFind {
        private int rep[];
        private int size[];

        UnionFind(int sz) {
            rep = new int[sz];
            size = new int[sz];
            for (int i = 0; i < sz; ++i) {
                // Initialize each group is its own representative
                rep[i] = i;
                // Initialize the size of all group to 1
                size[i] = 1;
            }
        }

        // Finds the representative of group x
        public int find(int x) {
            if (x == rep[x]) {
                return x;
            }
            rep[x] = find(rep[x]);

            return rep[x];
        }

        // Union the group that contains "a" with the group that contains "b"
        // Union by size (rank)
        public void union(int a, int b) {
            int repA = find(a);
            int repB = find(b);
            if (repA == repB) {
                return;
            }

            // Union by size: Point the rep of smaller group to the rep of the larger group
            if (size[repA] > size[repB]) {
                rep[repB] = repA;
                size[repA] += size[repB];
            } else {
                rep[repA] = repB;
                size[repB] += size[repA];
            }
        }
    }
}
