package com.github.regyl.lab1.solver;

import com.github.regyl.lab1.annotation.SubstitutionSolver;
import com.github.regyl.lab1.dto.EquationParameters;
import com.github.regyl.lab1.interceptor.LoggingInterceptor;
import jakarta.interceptor.Interceptors;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

@SubstitutionSolver
@Interceptors(LoggingInterceptor.class)
public class SubstitutionEquationSolverImpl extends AbstractEquationSolver {
    
    @Override
    protected Pair<Double, Double> solve(EquationParameters firstParams, EquationParameters secondParams) {
        double xParamAfterSubtraction = validate(firstParams, secondParams);
        double yAfterSubstitution = firstParams.getResult() / firstParams.getSecond() * secondParams.getSecond();
        double x = (secondParams.getResult() - (yAfterSubstitution)) / xParamAfterSubtraction;
        double y = solveUsingX(x, firstParams);
        
        return new ImmutablePair<>(x, y);
    }
    
    private double validate(EquationParameters firstParams, EquationParameters secondParams) {
        double xFromFirstEquation = secondParams.getSecond() * (-1) * firstParams.getFirst() / firstParams.getSecond();
        double xParamAfterSubtraction = xFromFirstEquation + secondParams.getFirst();
        if (xParamAfterSubtraction == 0.0f) {
            throw new IllegalArgumentException("Illegal input for substitution method");
        }
        
        return xParamAfterSubtraction;
    }
}
