package com.github.regyl.lab1.solver;

import com.github.regyl.lab1.annotation.SubstitutionSolver;
import com.github.regyl.lab1.dto.EquationParameters;
import com.github.regyl.lab1.interceptor.LoggingInterceptor;
import jakarta.interceptor.Interceptors;
import org.apache.commons.lang3.tuple.Pair;

@SubstitutionSolver
@Interceptors(LoggingInterceptor.class)
public class SubstitutionEquationSolverImpl extends AbstractEquationSolver {
    
    @Override
    protected Pair<Double, Double> solve0(EquationParameters firstParams, EquationParameters secondParams) {
        return null;
    }
}
