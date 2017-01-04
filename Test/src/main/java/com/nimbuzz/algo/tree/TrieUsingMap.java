package com.nimbuzz.algo.tree;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TrieUsingMap {
    class TrieNode {
        char c;
        Map<Character, TrieNode> children = new ConcurrentHashMap<Character, TrieNode>();
        boolean isLeaf;

        public TrieNode() {
        }

        public TrieNode(final char c) {
            this.c = c;
        }
    }

    private final TrieNode root;

    public TrieUsingMap() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(final String word) {
        Map<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {
            final char c = word.charAt(i);

            TrieNode t;
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = new TrieNode(c);
                children.put(c, t);
            }

            children = t.children;

            // set leaf node
            if (i == word.length() - 1) {
                t.isLeaf = true;
            }
        }
    }

    // Returns if the word is in the trie.
    public boolean search(final String word) {
        final TrieNode t = searchNode(word);

        if (t != null && t.isLeaf) {
            return true;
        } else {
            return false;
        }
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(final String prefix) {
        if (searchNode(prefix) == null) {
            return false;
        } else {
            return true;
        }
    }

    public TrieNode searchNode(final String str) {
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for (int i = 0; i < str.length(); i++) {
            final char c = str.charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
                children = t.children;
            } else {
                return null;
            }
        }

        return t;
    }
}
