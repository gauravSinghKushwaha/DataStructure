package com.gaurav.misc.algo;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://www.toptal.com/java/the-trie-a-neglected-data-structure
 * 
 * @author gkushwaha
 *
 */
public class ListVocabularyUsingTreeSet extends TreeSet<String> {

    private static final long serialVersionUID = 7383230741365137755L;

    public ListVocabularyUsingTreeSet(final Collection<String> c) {
        super(c);
    }

    public boolean isPrefix(final String prefix) {
        String nextWord = ceiling(prefix);
        if (nextWord == null) {
            return false;
        }
        if (nextWord.equals(prefix)) {
            final Set<String> tail = tailSet(nextWord, false);
            if (tail.isEmpty()) {
                return false;
            }
            nextWord = tail.iterator().next();
        }
        return nextWord.startsWith(prefix);
    }

    /**
     * There is a mismatch between the parameter types of vocabulary and TreeSet, so force call to the upper-class
     * method
     */
    public boolean contains(final String word) {
        return super.contains(word);
    }
    
    
/**    public LowercaseTrieVocabulary getNode(String s) {
        LowercaseTrieVocabulary node = this;
        for (int i = 0; i < s.length(); i++) {
                int index = LOWERCASE.getIndex(s.charAt(i));
                LowercaseTrieVocabulary child = node.children[index];
                if (child == null) {
                        // There is no such word
                        return null;
                }
                node = child;
        }
        return node;
} */
}
