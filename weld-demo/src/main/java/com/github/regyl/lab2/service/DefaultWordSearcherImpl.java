package com.github.regyl.lab2.service;

import com.github.regyl.lab2.api.WordSearcher;
import com.github.regyl.lab2.dto.WordSearchDto;
import jakarta.enterprise.inject.Default;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for search words in text.
 */
@Default
public class DefaultWordSearcherImpl implements WordSearcher {
    
    private static final String DELIMITER_SYMBOLS = "[\\.\\?\\!\\,\\:\\-]";
    
    private static final String DEFAULT_DELIMITER = " ";
    
    @Override
    public Collection<String> findWord(WordSearchDto searchDto) {
        String text = searchDto.getText();
        text = text.replaceAll(DELIMITER_SYMBOLS, DEFAULT_DELIMITER);
        List<String> words = Arrays.asList(text.split(DEFAULT_DELIMITER));
        
        return words.stream()
                .map(String::trim)
                .filter(item -> !StringUtils.isEmpty(item))
                .filter(searchDto.getPredicate())
                .collect(Collectors.toList());
    }
}
