package com.github.regyl.lab1.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum MathMethod {
    
    DIVISION("/"),
    MULTIPLICATION("*"),
    SUBTRACTION("-"),
    ADDITION("+");
    
    private final String value;
    
    public static Optional<MathMethod> byValue(String value) {
        if (StringUtils.isEmpty(value)) {
            return Optional.empty();
        }
        
        return Arrays.stream(MathMethod.values())
                .filter(item -> value.equals(item.getValue()))
                .findFirst();
    }
}
