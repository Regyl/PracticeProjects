package com.github.regyl;

import com.github.regyl.lab4.AppFour;
import com.github.regyl.lab4.dto.StationWayDtoAnnotation;
import com.github.regyl.lab4.dto.StationWayDtoXml;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppFourTest {
    
    private static final String DEFAULT_EXCEPTION_MESSAGE
            = "Поле специализация должно принимать одно из трёх значений: 0, 18 или 99";
    
    public static final class AnnotationImplementation {
        
        @Test
        public void shouldReturnConstraintOnWrongValue() {
            StationWayDtoAnnotation dto = StationWayDtoAnnotation.builder()
                    .specialization("19")
                    .build();
            
            var validationExceptions = AppFour.validate(dto);
            
            assertThat(validationExceptions).hasSize(1);
            var constraint = validationExceptions.iterator().next();
            assertThat(constraint.getMessage()).isEqualTo(DEFAULT_EXCEPTION_MESSAGE);
        }
        
        @Test
        public void shouldReturnEmptyConstraintArrayOnZero() {
            StationWayDtoAnnotation dto = StationWayDtoAnnotation.builder()
                    .specialization("0")
                    .build();
            
            var validationExceptions = AppFour.validate(dto);
            
            assertThat(validationExceptions).isEmpty();
        }
        
        @Test
        public void shouldReturnEmptyConstraintArrayOnEighteen() {
            StationWayDtoAnnotation dto = StationWayDtoAnnotation.builder()
                    .specialization("18")
                    .build();
            
            var validationExceptions = AppFour.validate(dto);
            
            assertThat(validationExceptions).isEmpty();
        }
        
        @Test
        public void shouldReturnEmptyConstraintArrayOnNinetyNine() {
            StationWayDtoAnnotation dto = StationWayDtoAnnotation.builder()
                    .specialization("99")
                    .build();
            
            var validationExceptions = AppFour.validate(dto);
            
            assertThat(validationExceptions).isEmpty();
        }
    }
    
    public static final class XmlImplementation {
        @Test
        public void shouldReturnConstraintOnWrongValue() {
            StationWayDtoXml dto = StationWayDtoXml.builder()
                    .specialization("19")
                    .build();
            
            var validationExceptions = AppFour.validate(dto);
            
            assertThat(validationExceptions).hasSize(1);
            var constraint = validationExceptions.iterator().next();
            assertThat(constraint.getMessage()).isEqualTo(DEFAULT_EXCEPTION_MESSAGE);
        }
        
        @Test
        public void shouldReturnEmptyConstraintArrayOnZero() {
            StationWayDtoXml dto = StationWayDtoXml.builder()
                    .specialization("0")
                    .build();
            
            var validationExceptions = AppFour.validate(dto);
            
            assertThat(validationExceptions).isEmpty();
        }
        
        @Test
        public void shouldReturnEmptyConstraintArrayOnEighteen() {
            StationWayDtoXml dto = StationWayDtoXml.builder()
                    .specialization("18")
                    .build();
            
            var validationExceptions = AppFour.validate(dto);
            
            assertThat(validationExceptions).isEmpty();
        }
        
        @Test
        public void shouldReturnEmptyConstraintArrayOnNinetyNine() {
            StationWayDtoXml dto = StationWayDtoXml.builder()
                    .specialization("99")
                    .build();
            
            var validationExceptions = AppFour.validate(dto);
            
            assertThat(validationExceptions).isEmpty();
        }
    }
}
