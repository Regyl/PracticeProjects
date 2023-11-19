package com.github.regyl.lab4.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationWayDtoAnnotation implements SpecializationContainer  {
    
    @Pattern(regexp = "0|18|99",
            message = "Поле специализация должно принимать одно из трёх значений: 0, 18 или 99")
    private String specialization;
}
