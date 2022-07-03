package numericstreams;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class IterateGenerateEx {
    public static void main(String[] args) {

        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        //Fibonacci tuples series (0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21).

        Stream.iterate(new int[] {0,1}, t -> new int[] {t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1]+ ")"));

    }
}
