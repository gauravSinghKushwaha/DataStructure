package com.java8.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestJava8Collection {

    public static void main(final String args[]) {
        final List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 0, 4, 5, 2, 0, 19, 8));
        list.forEach(System.out::println);
        list.sort((e1, e2) -> {
            return e1 - e2;
        });
        list.forEach(e -> System.out.println(e));

        list.stream().filter(e -> e % 2 != 0).distinct().map((e) -> (e > 0)).forEach(System.out::println);

        list.removeIf(e -> e == 0);
        list.forEach(System.out::println);

        System.out.println(list.parallelStream().filter(e -> e % 2 != 0).reduce(0, (a, b) -> a + b));
    }

}
