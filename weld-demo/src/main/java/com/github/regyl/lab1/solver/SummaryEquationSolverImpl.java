package com.github.regyl.lab1.solver;

import com.github.regyl.lab1.annotation.SummarySolver;
import com.github.regyl.lab1.dto.EquationParameters;
import com.github.regyl.lab1.interceptor.LoggingInterceptor;
import jakarta.interceptor.Interceptors;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

@SummarySolver
@Interceptors(LoggingInterceptor.class)
public class SummaryEquationSolverImpl extends AbstractEquationSolver {
    
    @Override
    protected Pair<Double, Double> solve0(EquationParameters firstParams, EquationParameters secondParams) {
        if (validate(firstParams, secondParams)) {
            throw new IllegalArgumentException("Illegal input for summary equation solver!");
        }
        
        double first = firstParams.getFirst();
        double second = firstParams.getSecond();
        return new ImmutablePair<>(first, second);
    }
    
    private boolean validate(EquationParameters firstParams, EquationParameters secondParams) {
        return false;
    }
}
