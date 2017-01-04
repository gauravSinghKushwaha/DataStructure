package com.design.pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandQueue {

    interface Command {
        void execute();
    }

    static class DomesticEngineer implements Command {
        @Override
        public void execute() {
            System.out.println("take out the trash");
        }
    }

    static class Politician implements Command {
        @Override
        public void execute() {
            System.out.println("take money from the rich, take votes from the poor");
        }
    }

    static class Programmer implements Command {
        @Override
        public void execute() {
            System.out.println("sell the bugs, charge extra for the fixes");
        }
    }

    public static List produceRequests() {
        final List queue = new ArrayList();
        queue.add(new DomesticEngineer());
        queue.add(new Politician());
        queue.add(new Programmer());
        return queue;
    }

    public static void workOffRequests(final List queue) {
        for (final Iterator it = queue.iterator(); it.hasNext();) {
            ((Command) it.next()).execute();
        }
    }

    public static void main(final String[] args) {
        final List queue = produceRequests();
        workOffRequests(queue);
    }
}
