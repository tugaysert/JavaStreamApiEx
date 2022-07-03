package chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static chapter4.Dish.menu;


public class Main2 {

    public static void main(String[] args) {

        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(dish);

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        //REDUCE
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        int sum2 = numbers.stream()
                .reduce(0, Integer::sum);
        Optional<Integer> optionalSum = numbers.stream()
                .reduce(Integer::sum);


        //How would you count the number of dishes in a stream
        int count = menu.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        System.out.println(count);

            //or
        long count2 = menu.stream()
                .count();
        System.out.println(count2);

        //not effective
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);

        //effective
        int caloriesEffective = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int maxC = maxCalories.orElse(0);
    }

}
