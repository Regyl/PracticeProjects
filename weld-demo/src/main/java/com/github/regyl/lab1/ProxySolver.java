package com.github.regyl.lab1;

import com.github.regyl.lab1.annotation.SummarySolver;
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
    public ProxySolver(@SummarySolver AbstractEquationSolver equationSolver) {
        this.equationSolver = equationSolver;
    }
    
    public void solve(String firstEquation, String secondEquation) {
        Pair<Double, Double> result = equationSolver.solve(firstEquation, secondEquation);
        
        log.info("First element: " + result.getLeft());
        log.info("Second element: " + result.getRight());
    }
}
