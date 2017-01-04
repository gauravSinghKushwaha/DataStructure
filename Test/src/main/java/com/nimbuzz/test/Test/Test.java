package com.nimbuzz.test.Test;

import java.util.*;

public class Test {
    static Integer x = new Integer(10);

    public static void main(final String pathSegments[]) {
        final String list = "4,9,1,0,10,f,nine,negative eight,two,negative one,5,afdgd,afdf";
        sortRanks(list);

        final int N = 15;
        printFizzBuzz(N);

        update(x);
        System.out.println(x);

        update2(x);
        System.out.println(x);

        convertTo24();

    }

    private static void convertTo24() {
        Scanner scan = null;
        try {
            scan = new Scanner(System.in);
            final String a = scan.next();
            final String[] time = a.split(":");
            final int hour = Integer.valueOf(time[0]);
            int newHour = 0;
            final boolean isAM = time[2].contains("AM");
            if (hour == 12 && isAM) {
                newHour = 00;
            } else {
                if (!isAM && hour < 12) {
                    newHour = hour + 12;
                } else {
                    newHour = hour;
                }
            }
            time[0] = newHour < 10 ? "00" : newHour + "";
            final String timeIn24 = String.join(":", time).replace("PM", "").replace("AM", "");
            System.out.println(timeIn24);
        } finally {
            scan.close();
        }
    }

    private static void sortRanks(final String list) {
        final List<String> ls = new ArrayList<String>(Arrays.asList(list.split(",")));
        final Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("0", 0);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        map.put("8", 8);
        map.put("9", 9);
        map.put("10", 10);
        map.put("-1", -1);
        map.put("-2", -2);
        map.put("-3", -3);
        map.put("-4", -4);
        map.put("-5", -5);
        map.put("-6", -6);
        map.put("-7", -7);
        map.put("-8", -8);
        map.put("-9", -9);
        map.put("-10", -10);
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        map.put("ten", 10);
        map.put("negative one", -1);
        map.put("negative two", -2);
        map.put("negative three", -3);
        map.put("negative four", -4);
        map.put("negative five", -5);
        map.put("negative six", -6);
        map.put("negative seven", -7);
        map.put("negative eight", -8);
        map.put("negative nine", -9);
        map.put("negative ten", -10);

        final List<String> invalidList = new ArrayList<String>();
        final List<String> validList = new ArrayList<String>();

        for (final String string : ls) {
            if (map.get(string) == null) {
                invalidList.add(string);
            } else {
                validList.add(string);
            }
        }

        System.out.println("Input" + ls);
        System.out.println("validList" + validList);
        System.out.println("invalidList" + invalidList);

        class NumComparator implements Comparator<String> {
            @Override
            public int compare(final String o1, final String o2) {
                final Integer first = map.get(o1);
                final Integer second = map.get(o2);
                return first.compareTo(second);
            }

        }

        Collections.sort(validList, new NumComparator());
        validList.addAll(invalidList);
        System.out.println("Sorted" + validList);

        final String[] strarray = new String[validList.size()];
        final String[] array = validList.toArray(strarray);
        System.out.println(array);
    }

    private static void printFizzBuzz(final int N) {
        final String FIZZ = "Fizz";
        final String BUZZ = "Buzz";

        for (int i = 1; i <= N; i++) {
            if (i % 3 == 0) {
                System.out.println(FIZZ);
            }
            if (i % 5 == 0) {
                System.out.println(BUZZ);
            }
            System.out.println(i);
        }
    }

    private static void update2(int y) {
        try {
            y = 20;
        } catch (final Exception e) {
            y = new Integer(20);
        }
        y = new Integer(5);
        System.out.println("print me2 " + y);
    }

    private static void update(Integer y) {
        try {
            y = new Integer(20);
        } catch (final Exception e) {
            y = new Integer(20);
        }
        y = new Integer(5);
        System.out.println("print me " + y);
    }

}
