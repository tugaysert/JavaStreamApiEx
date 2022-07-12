package chapter4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chapter4.Dish.menu;

public class chapter6Grouping {

    public enum CaloricLevel{DIET, NORMAL, FAT}

    public static void main(String[] args) {

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType); //{MEAT=[pork, beef, chicken], OTHER=[french fries, rice, season fruit, pizza], FISH=[prawns, salmon]}

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
                .collect(Collectors.groupingBy(d -> {
                    if(d.getCalories() <=400) return CaloricLevel.DIET;
                    else if (d.getCalories()<=700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        System.out.println(dishesByCaloricLevel); //{NORMAL=[beef, french fries, pizza, salmon], DIET=[chicken, rice, season fruit, prawns], FAT=[pork]}


    }
}
