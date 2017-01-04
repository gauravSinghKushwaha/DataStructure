package com.java8.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Person {
    public String name;
    public Integer id;

    public Person(final String name, final int id) {
        super();
        this.name = name;
        this.id = id;
    }

    public static void main(final String args[]) {
        final Set<Person> list = new HashSet<Person>();
        list.add(new Person("rajesh", 1));
        list.add(new Person("dhines", 2));
        list.add(new Person("gaura", 3));
        list.add(new Person("shil", 4));
        list.add(new Person("rajesh", 5));
        list.add(new Person("rajesh", 6));
        final Iterator<Person> iterator = list.stream().filter(e -> e.name.equals("rajesh")).iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        final HashMap<Integer, Person> map = new HashMap<Integer, Person>();
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", id=" + id + "]";
    }

}
