package com.gaurav.pattern.matching;

import static java.lang.System.out;

/**
 * 
 * http://www.programcreek.com/2014/06/leetcode-word-search-java/
 * 
 * @author gkushwaha
 *
 */
public class WordSerach {

    public static void main(final String args[]) {
        final char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        final WordSerach wordSerach = new WordSerach();
        out.println(wordSerach.exist(board, "ABCCED"));
        out.println(wordSerach.exist(board, "SEE"));
        out.println(wordSerach.exist(board, "ABCB"));
    }

    public boolean exist(final char[][] board, final String word) {
        final int m = board.length;
        final int n = board[0].length;

        boolean result = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0)) {
                    result = true;
                }
            }
        }

        return result;
    }

    public boolean dfs(final char[][] board, final String word, final int i, final int j, final int k) {
        final int m = board.length;
        final int n = board[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n) {
            return false;
        }

        if (board[i][j] == word.charAt(k)) {
            final char temp = board[i][j];
            board[i][j] = '#';
            if (k == word.length() - 1) {
                return true;
            } else if (dfs(board, word, i - 1, j, k + 1) || dfs(board, word, i + 1, j, k + 1)
                    || dfs(board, word, i, j - 1, k + 1) || dfs(board, word, i, j + 1, k + 1)) {
                return true;
            }
            board[i][j] = temp;
        }

        return false;
    }
}
