package com.github.regyl.lab2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Predicate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordSearchDto {
    
    private String text;
    
    private String target;
    
    private Predicate<String> predicate;
}
