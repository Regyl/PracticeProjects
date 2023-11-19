package com.github.regyl.lab5.validator;

import com.github.regyl.lab5.annotation.IncludeElement;
import com.github.regyl.lab5.dto.StationWayDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class StationWayDtoValidator implements ConstraintValidator<IncludeElement, StationWayDto> {
    
    private static final List<Integer> ALLOWED_VALUES = Arrays.asList(0, 18, 99);
    
    @Override
    public boolean isValid(StationWayDto value, ConstraintValidatorContext context) {
        int specialization = value.getSpecialization();
        return ALLOWED_VALUES.contains(specialization);
    }
}
