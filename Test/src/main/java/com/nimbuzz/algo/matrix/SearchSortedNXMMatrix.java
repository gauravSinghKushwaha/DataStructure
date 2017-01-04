package com.nimbuzz.algo.matrix;

public class SearchSortedNXMMatrix {
    public static void main(final String args[]) {
        final int arr[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        System.out.println(new SearchSortedNXMMatrix().find(arr, 9));
    }

    /**
     * Finding element i in arr
     * 
     * @param arr
     * @param i
     * @return
     */
    private boolean find(final int[][] arr, final int i) {
        final int rowIndeX = getRowIndeX(arr, i);
        if (rowIndeX > 0) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[rowIndeX][j] == i) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getRowIndeX(final int[][] arr, final int i) {
        for (int j = 0; j < arr.length; j++) {
            if (arr[j][0] == i) {
                return j;
            }
            if (arr[j][0] > i) {
                return --j;
            }
        }
        return -1;
    }
}
