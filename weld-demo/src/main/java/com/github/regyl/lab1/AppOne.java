package com.github.regyl.lab1;

import com.github.regyl.AbstractApp;
import org.apache.commons.lang3.tuple.Pair;


public class AppOne extends AbstractApp {
    
    /**
     * Solve equation's system of two unknown variables.
     *
     * @param firstEquation     first equation
     * @param secondEquation    second equation
     * @return                  variable values
     */
    public static Pair<Double, Double> solve(String firstEquation, String secondEquation) {
        ProxySolver proxySolver = WELD_CONTAINER.instance()
                .select(ProxySolver.class)
                .get();
        
        return proxySolver.solve(firstEquation, secondEquation);
    }
}
