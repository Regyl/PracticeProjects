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
 * Decorator for {@link WordSearcher} which search words by full text.
 */
@Slf4j
@Decorator
@Priority(10)
public class FullTextWordSearcherImpl implements WordSearcher {
    
    private final WordSearcher wordSearcher;
    
    @Inject
    public FullTextWordSearcherImpl(@Delegate WordSearcher wordSearcher) {
        this.wordSearcher = wordSearcher;
    }
    
    @Override
    public Collection<String> findWord(WordSearchDto searchDto) {
        log.info(this.getClass().getSimpleName());
        
        String target = searchDto.getTarget();
        Predicate<String> predicate = target::equals;
        
        searchDto.setPredicate(predicate);
        return wordSearcher.findWord(searchDto);
    }
}
