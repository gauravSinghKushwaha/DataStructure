package com.gaurav.misc.algo;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

public class HashRansomNote {
    final Map<String, Integer> magazineMap;
    final Map<String, Integer> noteMap;

    public HashRansomNote(final String magazine, final String note) {
        magazineMap = new ConcurrentHashMap<String, Integer>();
        noteMap = new ConcurrentHashMap<String, Integer>();
        final StringTokenizer st = new StringTokenizer(magazine);
        final StringTokenizer st2 = new StringTokenizer(note);
        final int magazineTokens = st.countTokens();
        final int noteTokens = st2.countTokens();
        if (magazineTokens >= 1 && magazineTokens <= 30000 && noteTokens >= 1 && noteTokens <= 30000) {
            while (st2.hasMoreElements()) {
                final String elem = (String) st2.nextElement();
                final int length = elem.length();
                if (length <= 5 && length >= 1) {
                    final Integer existing = noteMap.get(elem);
                    if (null == existing) {
                        noteMap.put(elem, 1);
                    } else {
                        noteMap.put(elem, existing + 1);
                    }
                }
            }
            while (st.hasMoreElements()) {
                final String elem = (String) st.nextElement();
                final int length = elem.length();
                if (length <= 5 && length >= 1) {
                    final Integer existing = magazineMap.get(elem);
                    if (null == existing) {
                        magazineMap.put(elem, 1);
                    } else {
                        magazineMap.put(elem, existing + 1);
                    }
                }
            }

            // for(String elem : magazine.split("[^a-zA-Z]+")) {
            // final int length = elem.length();
            // if (length <= 5 && length >= 1) {
            // final Integer existing = magazineMap.get(elem);
            // if (null == existing) {
            // magazineMap.put(elem, 1);
            // } else {
            // magazineMap.put(elem, existing + 1);
            // }
            // }
            // }
        }
    }

    public boolean solve() {
        final Iterator<String> iterator = noteMap.keySet().iterator();
        while (iterator.hasNext()) {
            final String next = iterator.next();
            if (!magazineMap.containsKey(next)) {
                return false;
            }
            if (magazineMap.get(next) < noteMap.get(next)) {
                return false;
            }
        }
        return true;
    }

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
        scanner.nextInt();

        // Eat whitespace to beginning of next line
        scanner.nextLine();

        final HashRansomNote s = new HashRansomNote(scanner.nextLine(), scanner.nextLine());
        scanner.close();

        final boolean answer = s.solve();
        if (answer) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }
}
