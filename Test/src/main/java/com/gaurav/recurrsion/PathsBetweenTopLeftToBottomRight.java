package com.gaurav.recurrsion;

/**
 * http://algorithms.tutorialhorizon.com/print-all-paths-from-top-left-to-bottom-right-in-two-dimensional-array/
 * 
 * @author gkushwaha
 *
 */
public class PathsBetweenTopLeftToBottomRight {

    public static void main(final String args[]) {
        final PathsBetweenTopLeftToBottomRight obj = new PathsBetweenTopLeftToBottomRight();
        final int[][] arr = obj.createArray();
        obj.printArray(arr);
        final int endX = arr.length - 1;
        final int endY = arr[0].length - 1;
        obj.getPaths2(arr, 0, 0, endX, endY, "path->>>");
    }

    private boolean getPaths2(final int[][] arr, final int currX, final int currY, final int endX, final int endY,
                              String path) {
        // System.out.println("currX = " + currX + " currY= " + currY + " endX = " + endX + " endY = " + endY);
        // path = path + "->" + currX + ":" + currY;
        path = path + "->" + arr[currX][currY];
        // reachedDestination
        if (currX == endX && currY == endY) {
            System.out.println(path);
            return true;
        }
        // move horrizontal
        if (currX < endX) {
            getPaths2(arr, currX + 1, currY, endX, endY, path);
        }

        // move vertical
        if (currY < endY) {
            getPaths2(arr, currX, currY + 1, endX, endY, path);
        }
        return false;
    }

    private int[][] createArray() {
        return new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    }

    private void printArray(final int sol[][]) {
        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol[0].length; j++) {
                System.out.print(" " + sol[i][j] + " ");
            }
            System.out.println();
        }
    }
}
