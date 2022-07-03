package chapter4;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chapter4.Dish.menu;

public class Main1 {


    public static void main(String[] args) {

        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName).limit(3)
                .collect(Collectors.toList());

        System.out.println(threeHighCaloricDishNames);

        List<String> names = menu.stream().filter(d -> {
            System.out.println("filtering " + d.getName());
            return d.getCalories() > 300;
        }).map(

                d -> {
                    System.out.println("mapping " + d.getName());
                    return d.getName();
                }).limit(3).collect(Collectors.toList());

        //System.out.println(names);

        menu.stream().forEach(System.out::println);


        List<Dish> vegetarianDishes = new ArrayList<>();
        for (Dish d : menu) {
            if (d.isVegetarian()) vegetarianDishes.add(d);
        }

        List<Dish> vegetarianDishes2 = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        System.out.println(vegetarianDishes); //[french fries, rice, season fruit, pizza]
        System.out.println(vegetarianDishes2); //[french fries, rice, season fruit, pizza]

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());

        System.out.println(dishes); //[chicken, french fries, rice, pizza, prawns, salmon]


        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        //Flattening streams
        //given the list of words ["Hello", "World"] you’d like to return the list ["H", "e", "l", "o","W", "r", "d"].
        List<String> words = Arrays.asList("Hello", "Word");
        List<String> transformWords = words.stream()
                .map(word -> {
                            String[] kk = word.split("");
                            System.out.println("burasi:");
                            System.out.println(Arrays.toString(kk));
                            return kk;
                        }
                )
                .flatMap((String[] strings) -> Arrays.stream(strings))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(transformWords);

        //Given a list of numbers, how would you return a list of the square of eachnumber?Forexample,
        // given [1, 2, 3, 4, 5] you should return [1, 4,
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers2.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(squares);


        //givenalist [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)].
        //Forsimplicity, you can represent a pair as an array with two elements
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);

        List<int[]> pairs = list1.stream()
                .flatMap(i -> list2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        pairs.stream().forEach(i -> System.out.printf(Arrays.toString(i)));
        System.out.println();

        //return only pairs whose sum is divisible by 3? For example, (2, 4) and (3, 3) are valid.
        List<int[]> divisibleByThreePairs = list1.stream()
                .flatMap(i -> list2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        divisibleByThreePairs.forEach(i -> System.out.printf(Arrays.toString(i)));

    }

}

