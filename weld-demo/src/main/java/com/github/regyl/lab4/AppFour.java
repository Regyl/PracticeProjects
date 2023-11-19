package com.github.regyl.lab4;

import com.github.regyl.AbstractApp;
import com.github.regyl.lab4.dto.StationWayDtoAnnotation;
import com.github.regyl.lab4.dto.StationWayDtoXml;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public class AppFour extends AbstractApp {
    
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
            .getValidator();
    
    public static Set<ConstraintViolation<StationWayDtoXml>> validate(
            StationWayDtoXml dto) {
        return VALIDATOR.validate(dto);
    }
    
    public static Set<ConstraintViolation<StationWayDtoAnnotation>> validate(
            StationWayDtoAnnotation dto) {
        return VALIDATOR.validate(dto);
    }
}
