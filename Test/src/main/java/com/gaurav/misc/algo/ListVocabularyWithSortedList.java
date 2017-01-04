package com.gaurav.misc.algo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * https://www.toptal.com/java/the-trie-a-neglected-data-structure
 * 
 * @author gkushwaha
 *
 */
public class ListVocabularyWithSortedList {

    private final List<String> words = new ArrayList<String>();

    /**
     * Constructor that adds all the words and then sorts the underlying list
     */
    public ListVocabularyWithSortedList(final Collection<String> words) {
        this.words.addAll(words);
        Collections.sort(this.words);
    }

    public boolean add(final String word) {
        final int pos = Collections.binarySearch(words, word);
        // pos > 0 means the word is already in the list. Pos < 0 means elements not there and it should be added to
        // -(pos + 1) to keep list sorted. Insert only if it's not there yet
        if (pos < 0) {
            words.add(-(pos + 1), word);
            return true;
        }
        return false;
    }

    public boolean isPrefix(final String prefix) {
        int pos = Collections.binarySearch(words, prefix);
        if (pos >= 0) {
            // The prefix is a word. Check the following word, because we are looking
            // for words that are longer than the prefix
            if (pos + 1 < words.size()) {
                final String nextWord = words.get(pos + 1);
                return nextWord.startsWith(prefix);
            }
            return false;
        }

        // NEGATIVE MEANS THIS IS WHERE PREFIX COULD BE ADDED....ALL THE ELEMENTS STARTING WITH IT SHOULD BE NEXT IN
        // THIS SORTED LIST
        pos = -(pos + 1);

        // The prefix is not a word. Check where it would be inserted and get the next word.
        // If it starts with prefix, return true.

        // prefix could be added at the list of sorted dictionary
        if (pos == words.size()) {
            return false;
        }

        final String nextWord = words.get(pos);
        return nextWord.startsWith(prefix);
    }

    public boolean contains(final String word) {
        final int pos = Collections.binarySearch(words, word);
        return pos >= 0;
    }
}
