package chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExMain1 {

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

        //1.Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> transactionsInTheYear2011 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(transactionsInTheYear2011);

        //2.What are all the unique cities where the traders work?
        List<String> uniqueCities = transactions.stream()
                .map(trs -> trs.getTrader().getCity())
                .distinct()
                .sorted(Comparator.comparing(String::valueOf))
                .collect(Collectors.toList());
        System.out.println(uniqueCities);

        //3.Find all traders from Cambridge and sort them by name.
        List<Trader> tradersFromCambridge = transactions.stream()
                .map(trs -> trs.getTrader())
                .filter(tr -> tr.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(tradersFromCambridge);

        //4.Return a string of all traders’ names sorted alphabetically.
        List<Trader> allOfTradersSorted = transactions.stream()
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        String s = allOfTradersSorted.stream()
                .map(Trader::getName)
                .reduce("", (a, b) -> a + b + " ");

        System.out.println(s);
        //4. efficient way
        String traderStr = transactions.stream()
                .map(trs -> trs.getTrader().getName())
                .sorted()
                .collect(Collectors.joining());
        System.out.println(traderStr); //AlanBrianMarioMarioRaoulRaoul


        //5.Are any traders based in Milan?
        boolean milanBased = transactions.stream()
                .anyMatch(trs -> trs.getTrader()
                        .getCity()
                        .equals("Milan"));


        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Milan"))
                .findAny()
                .ifPresent(t -> System.out.println(t.getName()));

        //6.Print all transactions’ values from the traders living in Cambridge
        List<Integer> valuesFromCambridge = transactions.stream()
                .filter(trs -> trs.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
        System.out.println(valuesFromCambridge);

        //7.What’s the highest value of all the transactions?
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(max);

        //8. Find the transaction with the smallest value
        Optional<Transaction> min2 = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(min2.get().getValue());

        //8.or
        Optional<Transaction> min3 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));


    }
}
