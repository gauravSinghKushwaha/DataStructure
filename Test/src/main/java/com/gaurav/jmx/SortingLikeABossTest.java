package com.gaurav.jmx;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.junit.Test;

public class SortingLikeABossTest {
    @SuppressWarnings("unchecked")
    @Test
    public void testBossSorting() {
        final List<Double> jumble =
                ThreadLocalRandom.current().doubles(10_000_000).parallel().boxed().collect(Collectors.toList());
        final List<Double> sorted = new ArrayList<>(jumble);
        Collections.sort(sorted);
        final ArrayList<Double> al = SortingLikeABoss.parallelSort(jumble, ArrayList::new);
        assertEquals(sorted, al);
        final LinkedList<Double> ll = SortingLikeABoss.parallelSort(jumble, LinkedList::new);
        assertEquals(sorted, ll);
        final CopyOnWriteArrayList<Double> cowal = SortingLikeABoss.parallelSort(jumble, CopyOnWriteArrayList::new);
        assertEquals(sorted, cowal);
    }
}