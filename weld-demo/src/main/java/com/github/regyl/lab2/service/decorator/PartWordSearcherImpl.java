package com.github.regyl.lab2.service.decorator;

import com.github.regyl.lab2.api.WordSearcher;
import com.github.regyl.lab2.dto.WordSearchDto;
import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * Decorator for {@link WordSearcher} which search words by part of word.
 */
@Slf4j
@Decorator
@Priority(10)
public class PartWordSearcherImpl implements WordSearcher {
    
    private final WordSearcher wordSearcher;
    
    @Inject
    public PartWordSearcherImpl(@Delegate WordSearcher wordSearcher) {
        this.wordSearcher = wordSearcher;
    }
    
    @Override
    public Collection<String> findWord(WordSearchDto searchDto) {
        log.info(this.getClass().getSimpleName());
        
        String target = searchDto.getTarget().toLowerCase();
        Predicate<String> predicate = (item) -> item.contains(target);
        
        searchDto.setPredicate(predicate);
        return wordSearcher.findWord(searchDto);
    }
}
