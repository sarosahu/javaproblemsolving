package com.streams;

import java.util.*;

import static java.util.Map.entry;

public class CollectionApi {
    public static void main(String[] args) {
        List<String> friends
                = Arrays.asList("Raphael", "Olivia", "Thibaut");
        System.out.println("Friend list 1 : " + friends);
        try {
            friends.add("Saroj");
        } catch(Exception ex) {
            System.out.println("Exception msg 1 : " + ex.toString());
            //throw ex;
        }
        friends.set(0, "Saroj");
        System.out.println("Friend list 1 again : " + friends);

        List<String> friends2 = List.of("Raphael", "Olivia", "Thibaut");
        System.out.println("Friend list 2 : " + friends2);
        try {
            friends2.add("Saroj");
        } catch(Exception ex) {
            System.out.println("Exception msg 2 : " + ex.toString());
        }
        try {
            friends2.set(1, "Saroj");
        } catch (Exception ex) {
            System.out.println("Exception msg 2 : " + ex.toString());
        }
        System.out.println("Friend list 2 : " + friends2);
        System.out.println();

        // Set factory
        Set<String> setOfFriends = Set.of("Raphael", "Olivia", "Thibaut");
        System.out.println("Friends 3 : " + setOfFriends);

        try {
            Set<String> setOfFriends2 = Set.of("Saroj", "Sachin", "Saroj");
        } catch(Exception ex) {
            System.out.println("Exception caught : " + ex.toString());
        }

        // Map factory
        Map<String, Integer> ageOfFriends
                = Map.of(
                        "Raphael", 30,
                "Olivia", 25,
                "Thibaut", 26);
        System.out.println("Map of friend with age : " +  ageOfFriends);

        try {
            ageOfFriends.put("Saroj", 40);
        } catch(Exception ex) {
            System.out.println("Exception caught : " + ex.toString());
        }

        // This following statement fails with compilation
        // as we have added more than 10 entries.
        /*
        Map<String, Integer> ageOfFriends3
                = Map.of(
                "Raphael", 30,
                "Olivia", 25,
                "Thibaut", 26,
                "Saroj", 40,
                "Sachin", 50,
                "Rahul", 50,
                "Sourav", 50,
                "Ram", 10000,
                "Laxman", 100000,
                "Hanuman", 1000000,
                "Ravan", 80);
        System.out.println("Map of friend with age : " +  ageOfFriends3);
         */
        Map<String, Integer> ageOfFriends2
                = Map.ofEntries(
                    entry("Raphael", 30),
                    entry("Manoj", 30),
                    entry("Olivia", 25),
                    entry("Thibaut", 26),
                    entry("Saroj", 40),
                    entry("Sachin", 50),
                    entry("Rahul", 50),
                    entry("Sourav", 50),
                    entry("Ram", 10000),
                    entry("Laxman", 100000),
                    entry("Hanuman", 1000000));
        System.out.println("Map of friend with age 2: " + ageOfFriends2);
        try {
            ageOfFriends2.put("Saroj", 41);
        } catch(Exception ex) {
            System.out.println("Exception caught 2: " + ex.toString());
        }
        System.out.println("Map of friend with age 2: " + ageOfFriends2);


        // removeIf()
        String[] names = {"Saroj", "Sachin", "Rahul", "Viru"};
        List<String> listOfString = new ArrayList<>();
        for (String name : names) {
            listOfString.add(name);
        }
        listOfString.removeIf(s -> s.charAt(0) == 'S');
        System.out.println("List of strings : " + listOfString);

        // replaceAll()
        List<String> listOfNames = new ArrayList<>();
        for (String name : names) {
            listOfNames.add(name);
        }
        for (ListIterator<String> iterator = listOfNames.listIterator(); iterator.hasNext();) {
            String name = iterator.next();
            iterator.set(Character.toLowerCase(name.charAt(0)) + name.substring(1));
        }
        System.out.println("List of names : " + listOfNames);

        listOfNames.replaceAll(name -> Character.toUpperCase(name.charAt(0)) + name.substring(1));

        System.out.println("List of names : " + listOfNames);

        // Map forEach()
        ageOfFriends.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));

        // Map sorting
        // Entry.comparingByValue()
        // Entry.comparingByKey()
        ageOfFriends
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);

        // computeIfAbsent
        Map<String, List<Integer>> nameToNums = new HashMap<>();
        nameToNums.put("Saroj", new ArrayList<>());
        nameToNums.put("Sachin", new ArrayList<>());
        nameToNums.get("Saroj").add(10);
        nameToNums.get("Sachin").add(20);
        nameToNums.computeIfAbsent("Rahul", name -> new ArrayList<>()).add(30);
        nameToNums.computeIfAbsent("Saroj", name -> new ArrayList<>()).add(100);
        nameToNums.forEach((a, b) -> System.out.println(a + " has " + b));

        // Map replaceAll
        Map<String, String> nameToMovie = new HashMap<>();
        nameToMovie.put("Saroj", "Mummy");
        nameToMovie.put("Soumya", "Papa");
        nameToMovie.put("Abhi", "Chipmunk");
        nameToMovie.replaceAll((name, movie) -> movie.toUpperCase());
        nameToMovie.forEach((a, b) -> System.out.println(a + " likes " + b));
    }
}
