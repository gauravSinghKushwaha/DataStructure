package com.nimbuzz.algo.tree.heap;

import java.util.Arrays;

public class Heap {

    private final Integer arr[];
    private int elemCount;

    public Heap(final int size) {
        arr = new Integer[size];
    }

    public void insert(final Integer e) {
        arr[elemCount] = e;
        siftUp(elemCount, e);
        elemCount++;
    }

    public Integer remove() {
        final int value = arr[0];
        elemCount--;
        arr[0] = arr[elemCount];
        arr[elemCount] = null;
        siftDown();
        return value;
    }

    private void siftDown() {
        int index = 0;
        while (index < elemCount - 1) {
            final int leftIndex = (index << 1) + 1;
            final int rightIndex = (index << 1) + 2;
            final Integer leftElement = arr[leftIndex] != null ? arr[leftIndex] : -1;
            final Integer rightElement = arr[rightIndex] != null ? arr[rightIndex] : -1;
            if (leftElement < 0 && rightElement < 0) {
                return;
            }
            //swapping with the one who is greater ..left or right
            int swapIndex = leftIndex;
            Integer swapElement = leftElement;
            if (leftElement < rightElement) {
                swapIndex = rightIndex;
                swapElement = rightElement;
            }
            if (arr[index] < arr[swapIndex]) {
                arr[swapIndex] = arr[index];
                arr[index] = swapElement;
                index = swapIndex;
            } else {
                break;
            }
        }
    }

    private void siftUp(final int elemCount, final Integer e) {
        int temp = elemCount;
        while (temp > 0) {
            final int parent = temp - 1 >>> 1;
            // swap
            final Integer root = arr[parent];
            if (e > root) {
                arr[temp] = root;
                arr[parent] = e;
            }
            temp = parent;
        }
    }

    public static void main(final String args[]) {
        final Heap intHeap = new Heap(1000);
        intHeap.insert(4);
        intHeap.insert(3);
        intHeap.insert(7);
        intHeap.insert(9);
        intHeap.insert(1);
        intHeap.insert(6);
        System.out.println(intHeap);
        System.out.println(intHeap.remove());
        System.out.println(intHeap.remove());
        System.out.println(intHeap.remove());

        intHeap.insert(2);
        intHeap.insert(10);
        intHeap.insert(5);

        System.out.println(intHeap.remove());
        System.out.println(intHeap.remove());
        System.out.println(intHeap.remove());
        System.out.println(intHeap.remove());
        System.out.println(intHeap.remove());
        System.out.println(intHeap.remove());
    }

    @Override
    public String toString() {
        return "Heap [arr=" + Arrays.toString(arr) + ", elemCount=" + elemCount + "]";
    }

}
