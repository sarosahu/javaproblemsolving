package com.java.streams.test;

import com.java.streams.model.CaloricLevel;
import com.java.streams.model.Dish;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static java.util.Arrays.asList;

public class CollectingDataWithStream {
    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrime2(int candidate) {
        int candidateRoot = (int) Math.sqrt((double)candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        partitioningBy(candidate -> isPrime2(candidate))
                );
    }
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );

        // Count the number of dishes
        long howManyDishes = menu.stream().collect(counting());
        System.out.println("Number of dishes : " + howManyDishes);

        // We can write this far more directly as:
        long howManyDishes2 = menu.stream().count();

        // Find the maximum and minimum values in a stream.
        // We can use 2 collectors - Collectors.maxBy and Collectors.minBy, to
        // calculate the maximum and minimum value in a stream respectively.
        Comparator<Dish> dishCalorieComparator = comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCalorieComparator));
        mostCalorieDish.ifPresent(d -> System.out.println("The most calorie dish is : " + d));

        Optional<Dish> minCalorieDish = menu.stream().collect(minBy(dishCalorieComparator));
        minCalorieDish.ifPresent(d -> System.out.println("The min calorie dish : " + d));

        // Summarization

        // Collectors.summingInt() -- it accepts a function that maps an object into the int
        // that has to be summed and returns a collector that, when passed to the usual collect
        // collect method, performs the requested summarization.
        // e.g. we can find the total number of calories in our menu list with
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("Total calories : " + totalCalories);
        // Collectors.summingLong() and Collectors.summingDouble() methods behave
        // exactly the same way.

        // Collectors.averagingInt(), Collectors.averagingLong(), Collectors.averagingDouble()
        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println("Average calorie : " + avgCalories);

        double avgCalories2 = menu.stream().collect(averagingDouble(Dish::getCalories));
        System.out.println("Average calorie : " + avgCalories2);

        // Summarize statistics
        IntSummaryStatistics menuStatistics =
                menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("Statistics on calorie of menu: " +menuStatistics);
        // There are corresponding summarizingLong, summarizingDouble factory methods
        // with associated types LongSummaryStatistics and DoubleSummary-Statistics

        // Joining string

        // Concatenate the names of all the dishes in the menu as follows:
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        System.out.println("Concatenated menu items : " + shortMenu);

        // Concatenate the names with using delimiter of all the dishes in the menu as follows:
        String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println("Concatenated menu items with comma separated: " + shortMenu2);

        // Generalized summarization with reduction

        // Collectors.reducing factory method
        int totalCalories2 = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println("Total calories with reducing() factory : " + totalCalories2);

        // find the highest-calorie dish using the one-argument version of reducing
        Optional<Dish> maxCalorieDish = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        maxCalorieDish.ifPresent(d -> System.out.println("Max calorie dish : " + d.getName()));

        // Similarly we can find the dish of minimum calorie
        Optional<Dish> minCalorieDish2 = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() < d2.getCalories() ? d1 : d2));
        minCalorieDish2.ifPresent(d -> System.out.println("Min calorie dish : " +d.getName()));

        // Collection framework flexibility: doing the same operation in different ways
        int totalCalories3 = menu.stream().collect(reducing(0,
                Dish::getCalories,
                Integer::sum));
        System.out.println("Total calories 3 : " + totalCalories3);

        int totalCalories4 = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("Total calories 4 : " + totalCalories4);

        // Quiz 6.1: Joining strings with reducing
        // Replacement for -> String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        String shortMenu3 = menu.stream().map(Dish::getName)
                .collect(reducing((s1, s2) -> s1 + s2)).get();

        String shortMenu4 = menu.stream()
                .collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));

        System.out.println("Short menu 3 : " + shortMenu3);
        System.out.println("Short menu 4 : " + shortMenu4);

        /**
         * Note that even though statements 1 and 3 are valid replacements for the joining
         * collector, they’ve been used here to demonstrate how the reducing one can be seen,
         * at least conceptually, as a generalization of all other collectors discussed in this
         * chapter. Nevertheless, for all practical purposes we always suggest using the joining
         * collector for both readability and performance reasons.
         */

        // Grouping
        // Collectors.groupingBy factory method.

        // Classify the dishes in menu according to their type
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("Dishes by type : " + dishesByType);

        // Group the dishes according to it's calorie level as follows:
        // x <= 400 -> diet, 700 >= x > 400 -> normal, x > 700 -> fat

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
                menu.stream().collect(
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                );
        System.out.println("Dishes by caloric level : " + dishesByCaloricLevel);

        // Manipulating grouped elements
        // Suppose, for example we want to filter only the caloric dishes, let's say
        // the ones with more than 500 calories.
        Map<Dish.Type, List<Dish>> caloricDishesByType =
                menu.stream().filter(dish -> dish.getCalories() > 500)
                        .collect(groupingBy(Dish::getType));
        System.out.println("Caloric dishes (calori > 500) by type : " + caloricDishesByType);

        // The above solution has one issue, it doesn't show other type at all i.e. FISH type
        // Here is the solution for this, we have filtering() factory method
        Map<Dish.Type, List<Dish>> caloricDishesByType2 =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                filtering(dish -> dish.getCalories() > 500, toList())));
        System.out.println("Caloric dishes (calori > 500) by type 2: " + caloricDishesByType2);

        // Another groupingBy() to manipuate the grouped elements ->
        // Transform them through a mapping function
        //
        // Convert each Dish in the groups into their respective names in this way:
        Map<Dish.Type, List<String>> dishNamesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        System.out.println("Dishes name by type : " + dishNamesByType);

        // You could also use a third Collector in combination with the groupingBy to perform
        // a flatMap transformation instead of a plain map. To demonstrate how this works let’s
        // suppose that we have a Map associating to each Dish a list of tags as it follows:
        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

        // You are required to extract these tags for each group of
        // type of dishes you can easily achieve this using the flatMapping Collector:
        Map<Dish.Type, Set<String>> dishTagNamesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())
                        ));
        System.out.println("Dish tag names by type : " + dishTagNamesByType);

        // Multilevel grouping
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }))
                );

        System.out.println("Dishes by type caloric level : " + dishesByTypeCaloricLevel);

        // Collecting Data in subgroups
        // MOre generally, the second collector passed to the first groupingBy can be any type
        // of collector, not just another groupingBy. For instance, it's possible to count the
        // number of Dishes in the menu for each type, by passing the counting collector as a
        // second argument to the groupingBy collector:
        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting())
        );
        System.out.println("Dish types with count : " + typesCount);


        // We could rework the collector we already used to find the highest-calorie dish in the
        // menu to achieve a similar result, but now classified by the type of dish:
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream().collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));

        System.out.println("Most caloric dish by type: " + mostCaloricByType);

        // Adapting the collector result to a different type.
        // Collectors.collectingAndThen() factory method:
        // Finding the highest-calorie dish in each subgroup
        Map<Dish.Type, Dish> mostCaloricByType2 =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)), Optional::get)));

        System.out.println("Most caloric dish by type 2: " + mostCaloricByType2);

        // More generally, the collector passed as second argument to the groupingBy factory
        // method will be used to perform a further reduction operation on all the elements
        // in the stream classified into the same group.
        // E.g. we could also reuse the collector created to sum the calories of all the dishes
        // in the menu to obtain a similar result, but this time for each group of Dishes:

        Map<Dish.Type, Integer> totalCaloriesByType =
                menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));

        // Yet another collector, commonly used in conjuction with groupingBy, is one generated
        // by mapping method.  This method takes 2 arguements: a function transforming the
        // elements in a stream and a further collector accumulating the objects resulting
        // from this transformation.
        // E.g. Suppose we want to know which caloricLevels are available in the menu for each
        // type of Dish. We could achieve this result combining a groupingBy and mapping collectors:

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType,
                                mapping(dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;}, toSet()
                                ))
                );

        System.out.println("Caloric Levels by Type : " + caloricLevelsByType);

        // Note that in the previous example, there are no guarantees about what type of Set is
        // returned. But by using toCollection, you can have more control. E.g. we can ask for
        // a HashSet by passing a constructor reference to it:
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2 =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;}, toCollection(HashSet::new))));

        System.out.println("Caloric Levels by Type2: " + caloricLevelsByType2);

        // Partitioning
        //if you’re vegetarian or have invited a vegetarian friend to have dinner with you,
        // you may be interested in partitioning the menu into vegetarian and nonvegetarian dishes:
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println("Partition by <is vegetarian> :" + partitionedMenu);

        // partitioningBy() has an overload function i.e. it can take a collector in its second argument.
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                groupingBy(Dish::getType))
                );

        System.out.println("Dishes partitioned with veg/nonveg and each with type : " + vegetarianDishesByType);

        // We can reuse our earlier code to find the most caloric dish among both vegetarian and nonvegetarian dishes:
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                menu.stream().collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));

        System.out.println("Most caloric dish paritioned by veg/nonveg : " + mostCaloricPartitionedByVegetarian);

        //
        Map<Boolean, Map<Boolean, List<Dish>>> isVegetarianDishByMoreThan500 =
                menu.stream().collect(partitioningBy(Dish::isVegetarian,
                        partitioningBy(d -> d.getCalories() > 500)));

        System.out.println("isVegetarianDishByMoreThan500 : " + isVegetarianDishByMoreThan500);

        //
        Map<Boolean, Long> isVegetarianCounts =
                menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
        System.out.println("isVegetarianCounts : " + isVegetarianCounts);


        // Partitioning numbers into prime and nonprime
        // Suppose you want to write a method accepting as argument an int n and partitioning
        // the first n natural numbers into prime and nonprime. But first, it will be useful
        // to develop a predicate that tests to see if a given candidate number is prime or not:
        Map<Boolean, List<Integer>> primesAndNonPrimes = partitionPrimes(20);
        System.out.println("Primes and non primes : " + primesAndNonPrimes);
    }
}
