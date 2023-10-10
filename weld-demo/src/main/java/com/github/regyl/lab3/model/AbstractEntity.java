package com.github.regyl.lab3.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Abstract entity.
 */
@Getter
@RequiredArgsConstructor
public abstract class AbstractEntity {
    
    private final Long id;
}
