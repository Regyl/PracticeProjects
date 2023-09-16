package com.github.regyl.lab1.solver;

import com.github.regyl.lab1.annotation.SummarySolver;
import com.github.regyl.lab1.dto.EquationParameters;
import com.github.regyl.lab1.interceptor.LoggingInterceptor;
import com.github.regyl.lab1.util.NumberUtils;
import jakarta.interceptor.Interceptors;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

@SummarySolver
@Interceptors(LoggingInterceptor.class)
public class SummaryEquationSolverImpl extends AbstractEquationSolver {
    
    @Override
    protected Pair<Double, Double> solve(EquationParameters firstParams, EquationParameters secondParams) {
        int validationResult = isValid(firstParams, secondParams);
        if (validationResult == 0) {
            throw new IllegalArgumentException("Illegal input for summary equation solver!");
        }
        
        double x;
        double y;
        double summaryResult = firstParams.getResult() + secondParams.getResult();
        if (validationResult == 1) {
            double yParam = firstParams.getSecond() + secondParams.getSecond();
            y = summaryResult / yParam;
            x = (firstParams.getResult() - y * firstParams.getSecond()) / firstParams.getFirst();
        } else {
            double xParam = firstParams.getFirst() + secondParams.getFirst();
            x = summaryResult / xParam;
            y = solveUsingX(x, firstParams);
        }
        
        return new ImmutablePair<>(x, y);
    }
    
    private int isValid(EquationParameters firstParams, EquationParameters secondParams) {
        if (NumberUtils.isSumEqualsToZero(firstParams.getFirst(), secondParams.getFirst())) {
            return 1;
        } else if (NumberUtils.isSumEqualsToZero(firstParams.getSecond(), secondParams.getSecond())) {
            return 2;
        }
        
        return 0;
    }
}
