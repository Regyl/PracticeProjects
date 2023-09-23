package com.github.regyl.lab2.service.decorator;

import com.github.regyl.lab2.api.WordSearcher;
import com.github.regyl.lab2.dto.WordSearchDto;
import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

@Slf4j
@Decorator
@Priority(100)
public class WordSearcherParameterValidatorImpl implements WordSearcher {
    
    private final WordSearcher wordSearcher;
    
    @Inject
    public WordSearcherParameterValidatorImpl(@Delegate WordSearcher wordSearcher) {
        this.wordSearcher = wordSearcher;
    }
    
    @Override
    public Collection<String> findWord(WordSearchDto searchDto) {
        log.info(this.getClass().getSimpleName());
        
        if (searchDto == null) {
            throw new IllegalArgumentException("SearchDto is null");
        }
        if (searchDto.getPredicate() == null) {
            throw new IllegalArgumentException("Predicate is null");
        }
        if (StringUtils.isEmpty(searchDto.getTarget())) {
            throw new IllegalArgumentException("Target is empty");
        }
        
        return wordSearcher.findWord(searchDto);
    }
}
