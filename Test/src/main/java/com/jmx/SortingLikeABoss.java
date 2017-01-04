package com.jmx;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortingLikeABoss {
    public static <E, R extends List<E>> R parallelSort(final List<E> list, final Function<List<E>, R> listConstructor) {
        return listConstructor.apply(list.parallelStream().sorted().collect(Collectors.toList()));
    }
}