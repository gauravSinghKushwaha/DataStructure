package com.gaurav.matrix.backtracking;

import java.util.Arrays;

/**
 * 
 * This ear­lier approach we have seen solu­tion matrix, at every row we have only one entry as 1 and rest of the
 * entries are 0. Solu­tion matrix takes O(N2) space. We can reduce it to O(N). We will solve it by tak­ing one
 * dimen­sional array and con­sider solution[1] = 2 as “Queen at 1st row is placed at 2nd column.
 * 
 * result[i]=j means queen at i-th row is placed at j-th column.
 * 
 * Check if Queens placed at (x1, y1) and (x2,y2) are safe
 * 
 * x1==x2 means same rows, y1==y2 means same columns |x2-x1|==|y2-y1| means they are placed in diagonals.
 * 
 * 
 * 
 * @author gkushwaha
 *
 */
public class NQueensInONComplexity {
    static int[] result; // this array will store the result

    // result[i]=j; means queen at i-th row is placed at j-th column.
    // Queens placed at (x1, y1) and (x2,y2)
    // x1==x2 means same rows, y1==y2 means same columns, |x2-x1|==|y2-y1| means
    // they are placed in diagonals.
    public boolean canPlace(final int row, final int col) {
        // row = x2 col =y2
        // This function will check if queen can be placed (x2,y2), or we can
        // say that Can queen at x2 row is placed at y2 column.
        // for finding the column for x2 row, we will check all the columns for
        // all the rows till x2-1.
        for (int i = 0; i < row; i++) {
            // result[i] == y2 => columns are same
            // |i - x2| == |result[i] - y2| => in diagonals.
            if (result[i] == col || Math.abs(i - row) == Math.abs(result[i] - col)) {
                return false;
            }
        }
        return true;
    }

    public void placeQueens(final int row, final int size) {
        for (int col = 0; col < size; col++) {
            // check if queen at xth row can be placed at i-th column.
            if (canPlace(row, col)) {
                result[row] = col; // place the queen at this position.
                if (row == size - 1) {
                    System.out.println("Order of " + size + " queens" + Arrays.toString(result));
                }
                placeQueens(row + 1, size);
            }
        }
    }

    public static void main(final String[] args) {
        final int n = 5;
        result = new int[n];
        final NQueensInONComplexity i = new NQueensInONComplexity();
        i.placeQueens(0, n);
    }
}
