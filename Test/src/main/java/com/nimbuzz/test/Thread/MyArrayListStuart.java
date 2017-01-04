package com.nimbuzz.test.Thread;

import java.util.Arrays;

/**
 * 
 * THIS CLASS HAS CONCURRENY ISSUE......
 * 
 * http://www.javaspecialists.eu/archive/Issue241.html?inf_contact_key=
 * e3469feee401fd10a3b6e147044c1a6bcfe829dd075f8de38c8ed59ca8033926
 * 
 * @author gkushwaha
 *
 */
public class MyArrayListStuart {
    private final Object READ_LOCK = new Object();
    private final Object WRITE_LOCK = new Object();
    private int[] arr = new int[10];
    private int size = 0;

    public int size() {
        synchronized (READ_LOCK) {
            return size;
        }
    }

    public int get(final int index) {
        synchronized (READ_LOCK) {
            rangeCheck(index);
            return arr[index];
        }
    }

    public boolean add(final int e) {
        synchronized (WRITE_LOCK) {
            if (size + 1 > arr.length) {
                arr = Arrays.copyOf(arr, size + 10);
            }
            arr[size++] = e;
            return true;
        }
    }

    public int remove(final int index) {
        synchronized (WRITE_LOCK) {
            rangeCheck(index);
            final int oldValue = arr[index];
            final int numMoved = size - index - 1;
            if (numMoved > 0) {
                System.arraycopy(arr, index + 1, arr, index, numMoved);
            }
            arr[--size] = 0;
            return oldValue;
        }
    }

    private void rangeCheck(final int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private static volatile boolean goAdd;

    public static void main(final String[] args) {
        for (int i = 0; i < 100000; i++) {
            goAdd = false;
            final MyArrayListStuart list = new MyArrayListStuart();
            new Thread(new Main(list, true)).start();
            new Thread(new Main(list, false)).start();
            new Thread(new Main(list, false)).start();
            new Thread(new Main(list, false)).start();
        }
    }

    static class Main implements Runnable {
        MyArrayListStuart list;
        boolean update;

        public Main(final MyArrayListStuart list, final boolean update) {
            this.list = list;
            this.update = update;
        }

        @Override
        public void run() {
            if (update) {
                while (!goAdd) {
                    ;
                }
                goAdd = false;
                for (int i = 1; i < 1000; i++) {
                    list.add(i);
                }
                for (int i = 1; i < 250; i++) {
                    list.remove(7);
                }
            } else {
                // wait until we're certain
                // index 6 has a value
                while (list.size() < 7) {
                    goAdd = true;
                }
                for (int i = 1; i < 1000; i++) {
                    int x;
                    if ((x = list.get(6)) != 7) {
                        System.out.println(x + " and " + list.size());
                    }
                }
            }
        }
    }
}