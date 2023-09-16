package com.github.regyl;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    
    @Test
    public void testSummarySolver() {
        String firstEquation = "2x-5y=10";
        String secondEquation = "3x+5y=30";
        
        Pair<Double, Double> result = App.solve(firstEquation, secondEquation);
        
        assertThat(result.getLeft()).isEqualTo(Double.parseDouble("8"));
        assertThat(result.getRight()).isEqualTo(Double.parseDouble("1.2"));
    }
}
