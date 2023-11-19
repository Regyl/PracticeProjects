package com.github.regyl.lab5.annotation;


import com.github.regyl.lab5.validator.StationWayDtoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {StationWayDtoValidator.class})
public @interface IncludeElement {
    
    String message() default "Поле специализация должно принимать одно из трёх значений: 0, 18 или 99";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
