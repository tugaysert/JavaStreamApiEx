package numericstreams;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class main1 {

    public static void main(String[] args) {

        //deneme
        IntStream evenNumbers0to100 =
                IntStream.rangeClosed(1, 100)
                        .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers0to100.count()); // inclusive 50


        Stream<double[]> pyhTriplets =
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                        .filter(c -> c[2] % 1 == 0);
        pyhTriplets.limit(5)
                .forEach(k -> System.out.println(k[0] + " " + k[1] + " " + k[2]));

        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();


    }
}
