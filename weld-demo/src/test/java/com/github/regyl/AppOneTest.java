package com.github.regyl;

import com.github.regyl.lab1.AppOne;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppOneTest {
    
    @Test
    public void solveWhenYCanBeReduced() {
        String firstEquation = "2x-5y=10";
        String secondEquation = "3x+5y=30";
        
        Pair<Double, Double> result = AppOne.solve(firstEquation, secondEquation);
        
        assertThat(result.getLeft()).isEqualTo(Double.parseDouble("8"));
        assertThat(result.getRight()).isEqualTo(Double.parseDouble("1.2"));
    }
    
    @Test
    public void solveWhenVariablesCouldNotBeReduced() {
        String firstEquation = "x-2y=3";
        String secondEquation = "5x+y=4";
        
        Pair<Double, Double> result = AppOne.solve(firstEquation, secondEquation);
        
        assertThat(result.getLeft()).isEqualTo(Double.parseDouble("1"));
        assertThat(result.getRight()).isEqualTo(Double.parseDouble("-1"));
    }
    
    @Test
    public void solveWhenEquationResultIsLessThanZero() {
        String firstEquation = "2x+y=-1";
        String secondEquation = "5x-3y=-19";
        
        Pair<Double, Double> result = AppOne.solve(firstEquation, secondEquation);
        
        assertThat(result.getLeft()).isEqualTo(Double.parseDouble("-2"));
        assertThat(result.getRight()).isEqualTo(Double.parseDouble("3"));
    }
}
