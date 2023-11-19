package com.github.regyl;

import com.github.regyl.lab5.AppFive;
import com.github.regyl.lab5.dto.StationWayDto;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppFiveTest {
    
    private static final String DEFAULT_EXCEPTION_MESSAGE
            = "Поле специализация должно принимать одно из трёх значений: 0, 18 или 99";
    
    @Test
    public void shouldReturnConstraintOnWrongDigit() {
        int item = 19;
        StationWayDto dto = StationWayDto.builder()
                .specialization(item)
                .build();
        
        var validationConstraints = AppFive.validate(dto);
        
        assertThat(validationConstraints).hasSize(1);
        assertThat(validationConstraints.iterator().next().getMessage())
                .isEqualTo(DEFAULT_EXCEPTION_MESSAGE);
    }
    
    @Test
    public void shouldReturnEmptyConstraintArrayOnRightDigit() {
        int item = 18;
        StationWayDto dto = StationWayDto.builder()
                .specialization(item)
                .build();
        
        var validationConstraints = AppFive.validate(dto);
        
        assertThat(validationConstraints).isEmpty();
    }
}
