package com.gaurav.tree;

/**
 * 
 * http://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
 * 
 * @author gkushwaha
 *
 */
public class TrieUsingArray {

    class TrieNode {
        TrieNode[] arr;
        boolean isEnd;

        // Initialize your data structure here.
        public TrieNode() {
            arr = new TrieNode[26];
        }
    }

    private final TrieNode root;

    public TrieUsingArray() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(final String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            final char c = word.charAt(i);
            final int index = c - 'a';
            if (p.arr[index] == null) {
                final TrieNode temp = new TrieNode();
                p.arr[index] = temp;
                p = temp;
            } else {
                p = p.arr[index];
            }
        }
        p.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(final String word) {
        final TrieNode p = searchNode(word);
        if (p == null) {
            return false;
        } else {
            if (p.isEnd) {
                return true;
            }
        }

        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(final String prefix) {
        final TrieNode p = searchNode(prefix);
        if (p == null) {
            return false;
        } else {
            return true;
        }
    }

    public TrieNode searchNode(final String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            final int index = c - 'a';
            if (p.arr[index] != null) {
                p = p.arr[index];
            } else {
                return null;
            }
        }

        if (p == root) {
            return null;
        }

        return p;
    }
}