package com.streams.test;

import com.streams.model.Dish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test3 {
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

        // Mapping to a numeric stream.

        // Calculate the sum of calorie in the menu.
        // Note: We already did it using reduce()
        // To use Stream.mapToInt()
        int calories = menu.stream()
                .mapToInt(Dish::getCalories) // returns IntStream
                .sum();
        // IntStream also supports other convenience methods like max(), min() and average()

        // Converting back to a stream of Objects
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        // Default values: OptionalInt
        // Primitive specialized version of Optional: OptionalInt, OptionalDouble, OptionalLong
        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
        int max = maxCalories.orElse(1); // It provides explicit default maximum if there's no value.
        System.out.println("The maximum calorie value : " + max);

        // Numeric ranges
        // Java 8 introduces 2 static methods available on IntStream and LongStream to help generate ranges.
        // Both methods take the starting value of the range as the first parameter and the end value of
        // the range as the second parameter. But range is exclusive, whereas rangeClosed is inclusive.
        IntStream evenNumbers = IntStream.rangeClosed(10, 100)
                .filter(n -> n%2 == 0);
        System.out.println("The number of even numbers between 1 to 100 : "  + evenNumbers.count());

        // Putting numerical streams into practice:
        // Pythagorean triples:
        // Pythagoras discovered that certain triples of numbers (a, b, c) satisfy the formula
        // a * a + b * b = c * c where a, b, and c are integers.
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> a != b && Math.sqrt(a*a + b*b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                        );
        pythagoreanTriples.limit(5)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .mapToObj(
                                                b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                                .filter(t -> t[2] % 1 == 0));


        // Building streams.
        // Stream from values.
        Stream<String> stream2 = Stream.of("Modern", "Java", "In", "Action");
        stream2.map(String::toUpperCase).forEach(System.out::println);

        // Stream from Array
        int[] nums = {2, 3, 4, 5, 7, 11, 13};
        int sum = Arrays.stream(nums).sum();
        System.out.println("Sum is : " + sum);

        // Stream from files
        long uniqueWords = 0;
        try(Stream<String> lines =
                    Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            System.out.println("IOException caught!");
            //e.printStackTrace();
        }

        // Streams from functions: creating infinite streams!
        // Stream.iterate() and Stream.generate()
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
