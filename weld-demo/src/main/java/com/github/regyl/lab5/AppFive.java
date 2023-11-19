package com.github.regyl.lab5;

import com.github.regyl.AbstractApp;
import com.github.regyl.lab5.dto.StationWayDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public class AppFive extends AbstractApp {
    
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
            .getValidator();
    
    public static Set<ConstraintViolation<StationWayDto>> validate(StationWayDto dto) {
        return VALIDATOR.validate(dto);
    }
}
