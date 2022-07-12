package mapVSflatMapEx;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class mapVSflatMapMain {

    public static void main(String[] args) {

        List<Customer> customers = ExDataBase.getAll();

        //list of emails - one to one
        List<String> emails = customers.stream()
                .map(c -> c.getEmail())
                .collect(Collectors.toList());
        //System.out.println(emails);

        //phone numbers
        List<List<String>> phoneNumbers = customers.stream()
                .map(c -> c.getPhoneNumbers())
                .collect(Collectors.toList());
        System.out.println(phoneNumbers); //[[397937955, 21654725], [89563865, 2487238947], [38946328654, 3286487236], [389246829364, 948609467]]

        //phone numbers2
        List<String> phoneNumbers2 = customers.stream()
                .flatMap(c -> c.getPhoneNumbers().stream())
                .collect(Collectors.toList());
        System.out.println(phoneNumbers2); //[397937955, 21654725, 89563865, 2487238947, 38946328654, 3286487236, 389246829364, 948609467]

        //given the list of words ["Hello", "World"] you’d like to return the list ["H", "e", "l", "o","W", "r", "d"].
        List<String> words = Arrays.asList("Hello", "Word");
        List<String> transform = words.stream()
                .flatMap(s -> Arrays.stream(s.split("")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(transform);

        //givenalist [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)].
        //Forsimplicity, you can represent a pair as an array with two elements
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);

        list1.stream()
                .flatMap(i -> list2.stream().map(j -> new int[]{i,j}))
                .forEach(k -> System.out.println(Arrays.toString(k)));

        //return only pairs whose sum is divisible by 3? For example, (2, 4) and (3, 3) are valid.
        list1.stream()
                .flatMap(i -> list2.stream().map(j -> new int[]{i,j}).filter(k-> (k[0] + k[1]) %3 ==0))
                .forEach(t -> System.out.println(Arrays.toString(t)));

    }
}
