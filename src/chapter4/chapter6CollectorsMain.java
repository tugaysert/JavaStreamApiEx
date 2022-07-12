package chapter4;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

import static chapter4.Dish.menu;

public class chapter6CollectorsMain {

    public static void main(String[] args) {

        //REDUCING
        long howManyDishes = menu.stream()
                .collect(Collectors.counting());

        long howManyDishes2 = menu.stream()
                .count();

        //Finding max and min
        Optional<Dish> maxCalorieDish = menu.stream()
                .max(Comparator.comparingInt(Dish::getCalories));
        System.out.println(maxCalorieDish.get());

        //Finding max and min by Collectors
        Optional<Dish> maxCalorieDishByCollectors = menu.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        System.out.println(maxCalorieDishByCollectors.get());

        //burada int'e transform ediliyor doğru değil
        int maxCalorieDishByCollectorsGeneral = menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 > d2 ? d1 : d2));
        System.out.println(maxCalorieDishByCollectorsGeneral);
        //doğru
        Optional<Dish> maxCalorieDishByCollectorsGeneral2 = menu.stream()
                .collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(maxCalorieDishByCollectorsGeneral2.get());


        //SUMMARIZATON
        int totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
        int totalCalories2 = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        int totalCalories3 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        int totalCalories4 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories + " " + totalCalories2 + " " + totalCalories3 + " " + totalCalories4);

        IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics); //IntSummaryStatistics{count=9, sum=4300, min=120, average=477,777778, max=800}

        //JOINING
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(shortMenu); //porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon

        String shortMenuEffective = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(shortMenuEffective); //pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon


        //THE Collectors.reducing FACTORY METHOD IS A GENERALIZATION OF ALL OF THEM.
        int totalCalories5 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        int totalCalories6 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories5 + " " + totalCalories6);

        Optional<Dish> maxCalorieDishByCollectorsGeneral3 = menu.stream()
                .collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(maxCalorieDishByCollectorsGeneral3.get());

    }

}
