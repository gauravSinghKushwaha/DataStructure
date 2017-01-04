package com.gaurav.misc.algo;

import java.io.IOException;
import java.util.Arrays;

/**
 * 
 Find the number of Islands | Set 2 (Using Disjoint Set)
 * 
 * Given a boolean 2D matrix, find the number of islands.
 * 
 * A group of connected 1s forms an island. For example, the below matrix contains 5 islands
 * 
 * {1, 1, 0, 0, 0}, {0, 1, 0, 0, 1}, {1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 0, 1, 0, 1}
 * 
 * A cell in 2D matrix can be connected to 8 neighbors.
 * 
 * This is an variation of the standard problem: “Counting number of connected components in a undirected graph”. We
 * have discussed a DFS based solution in below set 1.
 * 
 * Find the number of islands
 * 
 * We can also solve the question using disjoint set data structure explained here. The idea is to consider all 1 values
 * as individual sets. Traverse the matrix and do union of all adjacent 1 vertices. Below are detailed steps.
 * 
 * 
 * Approach: 1) Initialize result (count of islands) as 0 2) Traverse each index of the 2D matrix. 3) If value at that
 * index is 1, check all its 8 neighbours. If a neighbour is also equal to 1, take union of index and its neighbour. 4)
 * Now define an array of size row*column to store frequencies of all sets. 5) Now traverse the matrix again. 6) If
 * value at index is 1, find its set. 7) If frequency of the set in the above array is 0, increment the result be 1.
 * 
 * @author gkushwaha
 *
 */
public class NumberOfIslandViaDisjointSet {

    public static void main(final String[] args) throws IOException {
        final int[][] a =
                new int[][] { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 },
                { 1, 0, 1, 0, 1 } };
        System.out.println("Number of Islands is: " + countIslands(a));
    }

    // Returns number of islands in a[][]
    static int countIslands(final int a[][]) {
        final int numRows = a.length;
        final int numCols = a[0].length;

        final DisjointUnionSets dus = new DisjointUnionSets(numRows * numCols);

        /*
         * The following loop checks for its neighbours and unites the indexes if both are 1.
         */
        for (int j = 0; j < numRows; j++) {
            for (int k = 0; k < numCols; k++) {
                // If cell is 0, nothing to do
                if (a[j][k] == 0) {
                    continue;
                }

                // Check all 8 neighbours and do a union
                // with neighbour's set if neighbour is
                // also 1
                if (j + 1 < numRows && a[j + 1][k] == 1) {
                    dus.union(j * numCols + k, (j + 1) * numCols + k);
                }
                if (j - 1 >= 0 && a[j - 1][k] == 1) {
                    dus.union(j * numCols + k, (j - 1) * numCols + k);
                }
                if (k + 1 < numCols && a[j][k + 1] == 1) {
                    dus.union(j * numCols + k, j * numCols + k + 1);
                }
                if (k - 1 >= 0 && a[j][k - 1] == 1) {
                    dus.union(j * numCols + k, j * numCols + k - 1);
                }
                if (j + 1 < numRows && k + 1 < numCols && a[j + 1][k + 1] == 1) {
                    dus.union(j * numCols + k, (j + 1) * numCols + k + 1);
                }
                if (j + 1 < numRows && k - 1 >= 0 && a[j + 1][k - 1] == 1) {
                    dus.union(j * numCols + k, (j + 1) * numCols + k - 1);
                }
                if (j - 1 >= 0 && k + 1 < numCols && a[j - 1][k + 1] == 1) {
                    dus.union(j * numCols + k, (j - 1) * numCols + k + 1);
                }
                if (j - 1 >= 0 && k - 1 >= 0 && a[j - 1][k - 1] == 1) {
                    dus.union(j * numCols + k, (j - 1) * numCols + k - 1);
                }
            }
        }


        // Array to note down frequency of each set
        final int[] c = new int[numRows * numCols];
        System.out.println(dus);
        System.out.println(Arrays.toString(c));
        int numberOfIslands = 0;
        for (int j = 0; j < numRows; j++) {
            for (int k = 0; k < numCols; k++) {
                // if any field in array is 1
                if (a[j][k] == 1) {
                    final int index = j * numCols + k;
                    final int x = dus.find(index);
                    System.out.println("index " + index + " x = " + x + " c[x] = " + c[x]);
                    // If frequency of set is 0,
                    // increment numberOfIslands
                    if (c[x] == 0) {
                        numberOfIslands++;
                        c[x]++;
                    } else {
                        c[x]++;
                    }
                }
            }
        }
        return numberOfIslands;
    }
}

/**
 * array based implementation of Disjoint sets
 * 
 * @author gkushwaha
 *
 */
class DisjointUnionSets {
    int[] rank, parent;
    int n;

    public DisjointUnionSets(final int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }

    /**
     * Initially all the elements are in their own set
     */
    void makeSet() {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // Finds the representative of the set that x
    // is an element of
    int find(final int x) {
        // if x is not the parent of itself,
        // then x is not the representative of
        // its set.
        // so we recursively call Find on its parent
        // and move i's node directly under the
        // representative of this set
        if (parent[x] != x) {
            return find(parent[x]);
        }
        return x;
    }

    // Unites the set that includes x and the set
    // that includes y
    void union(final int x, final int y) {
        // Find the representatives (or the root nodes)
        // for x an y
        final int xRoot = find(x);
        final int yRoot = find(y);

        // Elements are in the same set, no need
        // to unite anything.
        if (xRoot == yRoot) {
            return;
        }

        // If x's rank is less than y's rank
        // Then move x under y so that depth of tree
        // remains less
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
        } else // Else if their ranks are the same
        {
            // Then move y under x (doesn't matter
            // which one goes where)
            parent[yRoot] = xRoot;

            // And increment the the result tree's
            // rank by 1
            rank[xRoot] = rank[xRoot] + 1;
        }
    }

    @Override
    public String toString() {
        return "DisjointUnionSets [parent=" + Arrays.toString(parent) + "]";
    }

}
