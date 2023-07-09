package com.java.streams.model;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumbersCollector implements Collector<Integer,
        Map<Boolean, List<Integer>>,
        Map<Boolean, List<Integer>>>
{
    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int)Math.sqrt((double) candidate);
        return primes.stream().takeWhile(i -> i <= candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
          acc.get(isPrime(acc.get(true), candidate)).add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1,
                Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));

            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public Map<Boolean, List<Integer>>
    partitionPrimeWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    public static void main(String[] args) {
        PrimeNumbersCollector obj = new PrimeNumbersCollector();
        Map<Boolean, List<Integer>> mapOfPrimeAndNonPrimeNums = obj.partitionPrimeWithCustomCollector(20);
        List<Integer> primeNums = mapOfPrimeAndNonPrimeNums.get(true);
        List<Integer> nonPrimes = mapOfPrimeAndNonPrimeNums.get(false);
        System.out.println("Prime numbers : ");
        for (int n : primeNums) {
            System.out.printf("%d ", n);
        }
        System.out.println();
        System.out.println("Non prime numbers : ");
        for (int n: nonPrimes) {
            System.out.printf("%d ", n);
        }
        System.out.println();
    }
}
