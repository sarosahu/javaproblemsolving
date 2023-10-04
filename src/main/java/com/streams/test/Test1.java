package com.streams.test;

import com.streams.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test1 {
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

        // Calculate the list of vegetarian dishes
        List<Dish> vegetarianDishes = menu.stream()
                .filter(d -> d.isVegetarian())
                .collect(Collectors.toList());
        System.out.println("Vegetarian dishes name : ");
        for (Dish dish : vegetarianDishes) {
            System.out.println(dish.getName());
        }
        System.out.println(">>>>>");
        //System.out.println("Done.");
        // Calculate the list of vegetarian dishes with mehod reference
        List<Dish> vegetarianDishes2 = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        System.out.println("Vegetarian dishes name : ");
        for (Dish dish : vegetarianDishes2) {
            System.out.println(dish.getName());
        }
        System.out.println(">>>>>");

        // Filtering unique elements
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        // Find the unique even numbers and print them
        System.out.println("Even numbers:");
        numbers.stream()
                .filter(i -> (i % 2) == 0)
                .distinct()
                .forEach(System.out::println);

        // Slicing a stream : Java 9 added takeWhile() and dropWhile()
        List<Dish> specialMenu = Arrays.asList(
                //new Dish("abc", true, 420, Dish.Type.OTHER),
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));

        // select the dishes that have fewer than 320 calories
        // We can use Stream.filter() method, but it will be inefficient, since list is already sorted by
        // calorie value, so we can use takeWhile()
        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(d -> d.getCalories() < 320)
                .collect(Collectors.toList());
        System.out.println("Dishes with calori < 320 :");
        for (Dish dish : slicedMenu1) {
            System.out.println(dish.getName());
        }

        // Using dropWhile() -- compute the other elements other than above scenario i.e.
        // return all the dishes of calorie >= 320
        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(d -> d.getCalories() < 320)
                .collect(Collectors.toList());
        System.out.println("Dishes with calori >= 320 :");
        for (Dish dish : slicedMenu2) {
            System.out.println(dish.getName());
        }
        System.out.println("<<<<<<<<<<<<<<<<<<");

        // Truncating a stream using limit(n)
        // Collect the first three dishes that have more than 300 calories
        List<Dish> dishes = specialMenu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Here are the first 3 dishes of having more than 300 calorie");
        for (Dish dish : dishes) {
            System.out.println(dish.getName());
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<");

        // Skipping elements using skip()
        // Streams support the skip(n) method to return a stream that discards the first n elements.
        // For example, the following code skips the first two dishes that have more than 300
        // calories and returns the rest.
        List<Dish> dishes2 = specialMenu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());

        System.out.println("Here are the dishes of having more than 300 calorie with skipped first 2");
        for (Dish dish : dishes2) {
            System.out.println(dish.getName());
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<");

        // Mapping --
        //extract the names of the dishes in the stream
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("Dish names : ");
        for (String dishName : dishNames) {
            System.out.println(dishName);
        }
        System.out.println("<<<<<<<<<");

        // find out the length of the name of each dish
        List<Integer> lengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Lengths :");
        for (Integer len : lengths) {
            System.out.println(len.intValue());
        }
        System.out.println("<<<<<<<<<<<");

        // Flattening stream
        // Given a list of words ["hello", "world"], you would like to return list of
        // characters ["H", "e", "l", "o", "w", "r", "d"]
        List<String> words = Arrays.asList("Goodbye", "World");
        List<String> uniqueChars = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Unique characters: ");
        for(String c : uniqueChars) {
            System.out.println(c);
        }
        System.out.println(">>>>>>>>>>>>>>>>");

        // Given a list of numbers, how would you return a list of the square of each number?
        // For example, given [1, 2, 3, 4, 5] you should return [1, 4, 9, 16, 25].
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squres = nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Squres :");
        for (Integer num : squres) {
            System.out.println(num.intValue());
        }
        System.out.println(">>>>>>>>>>>>>>>>>");

        // Given two lists of numbers, how would you return all pairs of numbers?
        // For example, given a list [1, 2, 3] and a list [3, 4] you should return
        // [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]. For simplicity --
        // you can represent a pair as an array with two elements.
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);
        List<int[]> pairs = nums1.stream()
                .flatMap(i -> nums2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        System.out.println("Pairs : ");
        for(int[] pair : pairs) {
            System.out.printf("(%d, %d)\n", pair[0], pair[1]);
        }
        System.out.println(">>>>>>>>>>");

        // How would you extend the previous example to return only pairs whose sum is divisible by 3?
        List<int[]> pairs2 = nums1.stream()
                .flatMap(i -> nums2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        System.out.println("Pairs whose sum is divisible by 3.");
        for (int[] pair : pairs2) {
            System.out.printf("(%d, %d)\n", pair[0], pair[1]);
        }
        System.out.println(">>>>>>>>>>>>>>");

        // Finding and matching.

        // Checking to see if a predicate matches at least one element.
        // The anyMatch method returns a boolean and is therefore a terminal operation.
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        // Checking to see if a predicate matches all elements.
        // allMatch()
        // Find out if menu is healthy (all dishes are below 1000 calories)
        boolean isHealthy = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);
        if (isHealthy) {
            System.out.println("The menu is healthy.");
        }

        // nonMatch() -- the opposite of allMatch() is noneMatch(). It ensures that
        // no elements in the stream match the given predicate.
        // Let's rewrite the previous example using nonMatch() i.e. to find out
        // if menu is healthy(all dishes are below 1000 calories)
        boolean isHealthy2 = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        if (isHealthy2) {
            System.out.println("The menu is healthy!");
        }

        // Finding an element
        // findAny()
        // Find a dish that is vegetarian.
        // We can combine the filter() and findAny() to express the query.
        Optional<Dish> vegDish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        if (vegDish.isPresent()) {
            System.out.println("Found a vegearian dish " + vegDish.get().getName());
        }
        vegDish.ifPresent(d -> System.out.println("Found a vegetarian dish : " + d.getName()));

        // Finding the first element.
        // Given a list of numbers, find the first square that's visible by 3.
        List<Integer> someNums = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNums.stream().map(n -> n * n)
                        .filter(n -> n % 3 == 0)
                        .findFirst();
        firstSquareDivisibleByThree.ifPresent(n -> System.out.println("Number is : " + n));

        // Reducing

        // Summing the elements
        List<Integer> nums3 = Arrays.asList(1, 2, 3, 4, 5);
        int sum = nums3.stream().reduce(0, Integer::sum);
        System.out.println("The sum is : " + sum);

        // Maximum and minimum
        Optional<Integer> maxNum = nums3.stream().reduce(Integer::max);
        maxNum.ifPresent(n -> System.out.println("The max element is : " + n));
        Optional<Integer> maxNum2 = nums3.stream().reduce((a, b) -> a > b ? a : b);
        maxNum2.ifPresent(n -> System.out.println("The max element is :: " + n));

        Optional<Integer> minNum = nums3.stream().reduce(Integer::min);
        minNum.ifPresent(n -> System.out.println("The min element is : " + n));
        Optional<Integer> minNum2 = nums3.stream().reduce((a, b) -> a < b ? a : b);
        minNum2.ifPresent(n -> System.out.println("The min element is :: " + n));

        // Count the number of dishes in a stream using map and reduce.
        Optional<Integer> count = nums3.stream().map(d -> 1).reduce(Integer::sum);
        count.ifPresent(n -> System.out.println("The number of dishes are : " + n));
    }

}
