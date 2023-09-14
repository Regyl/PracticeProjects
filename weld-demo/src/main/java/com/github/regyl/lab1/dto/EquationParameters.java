package com.github.regyl.lab1.dto;

import com.github.regyl.lab1.model.MathMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquationParameters {
    
    private double first;
    
    private double second;
    
    private double third;
    
    private double result;
    
    private MathMethod method;
}
