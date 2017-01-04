package com.nimbuzz.algo.tree;

/**
 * http://www.programcreek.com/2014/06/leetcode-word-search-ii-java/
 * 
 * 
 * 
 * @author gkushwaha
 *
 */
public class WordSearchBoardGamesUsingTrie {

    private static final String[] dict = { "oath", "pea", "eat", "rain" };
    private static final char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
        { 'i', 'f', 'l', 'v' } };
    private final TrieUsingArray root;

    public WordSearchBoardGamesUsingTrie(final TrieUsingArray root) {
        this.root = root;
    }

    public static void main(final String args[]) {
        final WordSearchBoardGamesUsingTrie obj = new WordSearchBoardGamesUsingTrie(new TrieUsingArray());
        // building up dictionary
        final TrieUsingArray root = obj.getRoot();
        for (final String string : getDict()) {
            root.insert(string);
        }

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

    public TrieUsingArray getRoot() {
        return root;
    }

    private boolean findWord(final String word) {
        final char[][] board = getBoard();
        for (int row = 0; row < board.length; row++) {// rows
            for (int col = 0; col < board[0].length; col++) {// cols
                if (dfs(row, col, word, "")) {
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
     * @param word
     * @param temp
     * @param ith
     *            row
     * @param jth
     *            col in board
     */
    private boolean dfs(final int row, final int col, final String word, final String temp) {

        if (row < 0 || col < 0 || row >= getBoard().length || col >= getBoard()[0].length
                || temp.length() > word.length()) {
            return false;
        }
        // using temp val so that same does not get count twice
        final char val = getBoard()[row][col];
        final String temp2 = temp + val;

        if (temp2.contains("#") || !getRoot().startsWith(temp2)) {
            return false;
        }
        getBoard()[row][col] = '#';
        if (temp2.length() == word.length()) {
            if (getRoot().search(temp2) && word.equals(temp2)) {
                return true;
            }
        }

        if (dfs(row - 1, col, word, temp2) || dfs(row + 1, col, word, temp2) || dfs(row, col - 1, word, temp2)
                || dfs(row, col + 1, word, temp2)) {
            return true;
        }
        getBoard()[row][col] = val;
        return false;
    }

}
