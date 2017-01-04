package com.gaurav.jmx;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class ListSorting {
    private static final ByteWatcher byteWatcher = new ByteWatcher();
    private final static ThreadMXBean tmbean = ManagementFactory.getThreadMXBean();

    public static void main(final String... args) throws IOException {
        for (int i = 0; i < 10; i++) {
            testAll();
            System.out.println();
        }
    }

    private static void measureSort(final String type, final List<Double> list, final Runnable sortJob) {
        try {
            long time = tmbean.getCurrentThreadUserTime();
            byteWatcher.reset();
            sortJob.run();
            final long bytes = byteWatcher.calculateAllocations();
            time = tmbean.getCurrentThreadUserTime() - time;
            time = TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS);
            System.out.printf("%s sort %s %,3d in %dms and bytes %s%n", type, list.getClass().getName(), list.size(),
                    time, Memory.format(bytes, Memory.BYTES, 2));
        } catch (final UnsupportedOperationException ex) {
            System.out.println("Old sort: Cannot sort " + list.getClass().getName() + " " + ex);
        }
    }

    /**
     * {@linkplain java.util.Collections#sort Copied from Java 7}
     */
    @SuppressWarnings({ "unchecked" })
    public static <E> void sort(final List<E> list) {
        final Object[] a = list.toArray();
        Arrays.sort(a);
        final ListIterator<E> i = list.listIterator();
        for (final Object e : a) {
            i.next();
            i.set((E) e);
        }
    }

    private static void sortNew(final List<Double> list) {
        measureSort("New", list, () -> list.sort(null));
    }

    private static void sortOld(final List<Double> list) {
        measureSort("Old", list, () -> sort(list));
    }

    private static void test(final UnaryOperator<List<Double>> listConstr, final List<Double> list) {
        sortOld(listConstr.apply(list));
        sortNew(listConstr.apply(list));
    }

    @SuppressWarnings("unchecked")
    private static void testAll() {
        for (int size = 100_000; size <= 10_000_000; size *= 10) {
            // list of random double values, list size is size
            final List<Double> jumble = ThreadLocalRandom.current().doubles(size).boxed().collect(Collectors.toList());
            test(ArrayList::new, jumble);
            test(LinkedList::new, jumble);
            test(Vector::new, jumble);
            test(CopyOnWriteArrayList::new, jumble);
            test(doubles -> Arrays.asList(jumble.stream().toArray(Double[]::new)), jumble);
        }
    }
}