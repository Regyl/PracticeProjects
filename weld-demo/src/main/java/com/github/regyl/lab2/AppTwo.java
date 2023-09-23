package com.github.regyl.lab2;

import com.github.regyl.AbstractApp;
import com.github.regyl.lab2.api.WordSearcher;
import com.github.regyl.lab2.dto.WordSearchDto;

import java.util.Collection;

public class AppTwo extends AbstractApp {
    
    public static Collection<String> findWord(String text, String target) {
        WordSearcher wordSearcher = WELD_CONTAINER.instance()
                .select(WordSearcher.class)
                .get();
        WordSearchDto searchDto = new WordSearchDto(text, target, null);
        return wordSearcher.findWord(searchDto);
    }
}
