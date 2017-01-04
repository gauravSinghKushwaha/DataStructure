package com.nimbuzz.algo.backtracking;

/**
 * 
 * Approach: In this approach we will see the basic solu­tion with O(N^2) extra space we will improve it fur­ther to
 * O(N) space. Click here to see the solu­tion.
 * 
 * Cre­ate a solu­tion matrix of the same struc­ture as chess board. When­ever place a queen in the chess board, mark
 * that par­tic­u­lar cell in solu­tion matrix. At the end print the solu­tion matrix, the marked cells will show the
 * posi­tions of the queens in the chess board. Algo­rithm:
 * 
 * Place the queens col­umn wise, start from the left most column If all queens are placed. return true and print the
 * solu­tion matrix. Else Try all the rows in the cur­rent column. Check if queen can be placed here safely if yes mark
 * the cur­rent cell in solu­tion matrix as 1 and try to solve the rest of the prob­lem recursively. If plac­ing the
 * queen in above step leads to the solu­tion return true. If plac­ing the queen in above step does not lead to the
 * solu­tion , BACKTRACK, mark the cur­rent cell in solu­tion matrix as 0 and return false. If all the rows are tried
 * and noth­ing worked, return false and print NO SOLUTION.
 * 
 * 
 * 
 * @author gkushwaha
 *
 */
public class NQueensBT {
    public int[][] solution;

    public NQueensBT(final int N) {
        solution = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solution[i][j] = 0;
            }
        }
    }

    public void solve(final int N) {
        if (placeQueens(0, N)) {
            // print the result
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(" " + solution[i][j]);
                }
                System.out.println();
            }
        } else {
            System.out.println("NO SOLUTION EXISTS");
        }
    }

    public boolean placeQueens(final int queen, final int N) {
        // will place the Queens one at a time, for column wise
        if (queen == N) {
            // if we are here that means we have solved the problem
            return true;
        }
        for (int row = 0; row < N; row++) {
            // check if queen can be placed row,col
            if (canPlace(solution, row, queen)) {
                // place the queen
                solution[row][queen] = 1;
                // solve for next queen
                if (placeQueens(queen + 1, N)) {
                    return true;
                }
                // if we are here that means above placement didn't work
                // BACKTRACK
                solution[row][queen] = 0;
            }
        }
        // if we are here that means we haven't found solution
        return false;

    }

    // check if queen can be placed at matrix[row][column]
    public boolean canPlace(final int[][] matrix, final int row, final int column) {
        // since we are filling one column at a time,
        // we will check if no queen is placed in that particular row
        for (int i = 0; i < column; i++) {
            if (matrix[row][i] == 1) {
                return false;
            }
        }
        // we are filling one column at a time,so we need to check the upper and
        // diagonal as well
        // check upper diagonal
        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
            if (matrix[i][j] == 1) {
                return false;
            }
        }

        // check lower diagonal
        for (int i = row, j = column; i < matrix.length && j >= 0; i++, j--) {
            if (matrix[i][j] == 1) {
                return false;
            }
        }
        // if we are here that means we are safe to place Queen at row,column
        return true;
    }

    public static void main(final String[] args) {
        final int N = 8;
        final NQueensBT q = new NQueensBT(N);
        q.solve(N);

    }

}