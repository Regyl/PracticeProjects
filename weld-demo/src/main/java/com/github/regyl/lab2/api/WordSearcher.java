package com.github.regyl.lab2.api;

import com.github.regyl.lab2.dto.WordSearchDto;

import java.util.Collection;

/**
 * Service for search words in text.
 */
public interface WordSearcher {
    
    /**
     * Find all words matched to {@link WordSearchDto#target}.
     *
     * @param searchDto     dto with text and target word
     * @return              array with matched words
     */
    Collection<String> findWord(WordSearchDto searchDto);
}
