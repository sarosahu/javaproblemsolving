package com.streams.test;
// The questions will be on Trader and Transaction model class

import com.streams.model.Trader;
import com.streams.model.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test2 {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // { Q1. Find all the transactions in the year 2011 and sort them by value (small to high)
        List<Transaction> transactions2011 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        for (Transaction t : transactions2011) {
            System.out.println(t);
        }
        // }
        // { Q2. What are all the unique cities where the traders work ?
        List<String> uniqeCities = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        for (String city : uniqeCities) {
            System.out.println(city);
        }
        // }
        // { Q3. Find all the traders from Cambridge and sort them by name.
        List<Trader> tradersFromCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        for (Trader t : tradersFromCambridge) {
            System.out.println(t);
        }
        // }
        // { Return a string of all traders' name sorted alphabetically.
        System.out.println("Return a string of all traders' name sorted alphabetically");
        /* List<Trader> allTradersSortedByName = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        for (Trader t : allTradersSortedByName) {
            System.out.println(t);
        } */
        String traderStr = transactions.stream()
                .map(t -> t.getTrader().getName())
                        .distinct()
                                .sorted()
                                        .reduce("", (a, b) -> a + b);
        System.out.println(traderStr);
        String tradeStr2 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(tradeStr2);
        // }
        // { Are any traders based in Milan ?
        System.out.println("Are any traders based in Milan ?");
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Milan"))
                .map(Transaction::getTrader)
                .findAny()
                .ifPresent(t -> System.out.println(t));

        boolean milanBased = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println("Milan based trader present : " + milanBased);
        // }
        // { Print the values of all transactions from the traders living in Cambridge.
        System.out.println("Print the values of all transactions from the traders living in Cambridge.");
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        System.out.println("Done.");
        // }
        // { What's the highest value of all the transactions ?
        Optional<Integer> highValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        highValue.ifPresent(t -> System.out.println("Highest value : " + t));
        // }
        // { Find the transaction with the smallest value.
        Optional<Integer> smallestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        smallestValue.ifPresent(t -> System.out.println("Smallest value : " + t));

        Optional<Transaction> smallestTransaction = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        smallestTransaction.ifPresent(t -> System.out.println("Transaction with smallest value : " + t));

        Optional<Transaction> smallestTransaction2 = transactions
                .stream()
                .min(Comparator.comparing(Transaction::getValue));

        smallestTransaction2.ifPresent(t -> System.out.println("Transaction with smallest value : " + t));
        // }
    }
}
