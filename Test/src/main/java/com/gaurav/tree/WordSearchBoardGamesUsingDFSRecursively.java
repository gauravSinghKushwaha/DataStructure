package com.gaurav.tree;

/**
 * http://www.programcreek.com/2014/06/leetcode-word-search-ii-java/
 * 
 * 
 * 
 * @author gkushwaha
 *
 */
public class WordSearchBoardGamesUsingDFSRecursively {

    private static final String[] dict = { "oath", "pea", "eat", "rain" };
    private static final char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
        { 'i', 'f', 'l', 'v' } };

    public static void main(final String args[]) {
        final WordSearchBoardGamesUsingDFSRecursively obj = new WordSearchBoardGamesUsingDFSRecursively();

        obj.findWord("oath");
        obj.findWord("eat");
        obj.findWord("pea");
        obj.findWord("rain");
    }

    public static char[][] getBoard() {
        return board;
    }

    public static String[] getDict() {
        return dict;
    }

    private boolean findWord(final String word) {
        final char[][] board = getBoard();
        for (int row = 0; row < board.length; row++) {// rows
            for (int col = 0; col < board[0].length; col++) {// cols
                if (dfs(row, col, 0, word)) {
                    System.out.println("found " + word);
                    return true;
                }
            }
        }
        System.out.println("not-found " + word);
        return false;
    }

    /**
     * Searching recursively...like DFS
     * 
     * @param k
     *            TODO
     * @param word
     * @param ith
     *            row
     * @param jth
     *            col in board
     */
    private boolean dfs(final int row, final int col, final int k, final String word) {

        if (row < 0 || col < 0 || row >= getBoard().length || col >= getBoard()[0].length || k > word.length() - 1) {
            return false;
        }

        if (word.charAt(k) != getBoard()[row][col]) {
            return false;
        }
        System.out.println(row + " :: " + col);
        if (k == word.length() - 1) {
            for (final String dictWord : getDict()) {
                if (dictWord.equals(word)) {
                    System.out.println("found " + word);
                    return true;
                }
            }
            System.out.println("Not-found " + word);
            return false;
        }

        if (dfs(row - 1, col, k + 1, word) || dfs(row + 1, col, k + 1, word) || dfs(row, col - 1, k + 1, word)
                || dfs(row, col + 1, k + 1, word)) {
            return true;
        }
        return false;
    }

}
