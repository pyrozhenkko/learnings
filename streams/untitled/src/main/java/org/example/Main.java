package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        List<String> array = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
        String[] array2 = {"a", "b", "c", "d", "e", "f", "g"};
        Stream<String> stream = array.stream();
        Stream <String> stream1 = Stream.of("a", "b", "c", "d", "e", "f", "g");
        Stream <String> stream2 = Arrays.stream(array2);

        int[] numbers = { -5, -40, -3, -2, -1, 0, 1, 2, 3, 4 };
        long count = Arrays.stream(numbers).filter(n -> n > 0).count();
        System.out.println(count);
        Arrays.stream(numbers).sorted().limit(5).forEach(n -> System.out.print(n + ", "));
        Stream.of("a", "b", "c", "d", "e", "f", "g").filter("c" ::equals).forEach(System.out::println);

    }
}
