package com.github.regyl.lab1;

import com.github.regyl.lab1.annotation.SubstitutionSolver;
import com.github.regyl.lab1.solver.AbstractEquationSolver;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

@Slf4j
@Default
public class ProxySolver {
    
    private final AbstractEquationSolver equationSolver;
    
    @Inject
    public ProxySolver(@SubstitutionSolver AbstractEquationSolver equationSolver) {
        this.equationSolver = equationSolver;
    }
    
    public Pair<Double, Double> solve(String firstEquation, String secondEquation) {
         return equationSolver.solve(firstEquation, secondEquation);
    }
}
