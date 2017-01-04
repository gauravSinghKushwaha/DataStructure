package com.gaurav.java8.collection;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapExample {

    /**
     * 
     * @param args
     */

    public static void main(final String[] args) {

        final ConcurrentSkipListMap<String, String> concurrentSkipListMap = new ConcurrentSkipListMap<String, String>();

        concurrentSkipListMap.put("1111", "Tom Smith");

        concurrentSkipListMap.put("2222", "David Jones");

        concurrentSkipListMap.put("3333", "Jim Anderson");

        concurrentSkipListMap.put("4444", "John Abraham");

        concurrentSkipListMap.put("5555", "Brad Pitt");

        System.out.println("The name associated with id 1111 is " + concurrentSkipListMap.get("1111"));

        final NavigableSet<String> navigableKeySet = concurrentSkipListMap.keySet();

        System.out.println("The keys associated with this map are ");

        for (final Iterator<String> iterator = navigableKeySet.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }

        final ConcurrentNavigableMap<String, String> subMap = concurrentSkipListMap.subMap("1111", "3333");

        final NavigableSet<String> navigableSubKeySet = subMap.keySet();

        System.out.println("The keys associated with the submap keys from 1111 to 3333 are");

        for (final Iterator<String> subMapIterator = navigableSubKeySet.iterator(); subMapIterator.hasNext();)

        {

            System.out.println(subMapIterator.next());

        }

        final ConcurrentNavigableMap<String, String> headerMap = concurrentSkipListMap.headMap("2222");

        System.out.println("The keys associated with the headMap less than 2222");

        final NavigableSet<String> navigableHeadMapKeySet = headerMap.keySet();

        for (final Iterator<String> headMapIterator = navigableHeadMapKeySet.iterator(); headMapIterator.hasNext();)

        {

            System.out.println(headMapIterator.next());

        }

        final ConcurrentNavigableMap<String, String> tailMap = concurrentSkipListMap.tailMap("1111");

        System.out.println("Thekeys associated with the tailMap less than 1111");

        final NavigableSet<String> navigableTailMapKeySet = tailMap.keySet();

        for (final Iterator<String> tailMapIterator = navigableTailMapKeySet.iterator(); tailMapIterator.hasNext();)

        {

            System.out.println(tailMapIterator.next());

        }

    }

}