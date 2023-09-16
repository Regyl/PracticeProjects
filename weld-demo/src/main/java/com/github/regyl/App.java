package com.github.regyl;

import com.github.regyl.lab1.ProxySolver;
import org.apache.commons.lang3.tuple.Pair;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;


public class App {
    
    private static final Weld WELD;
    private static final WeldContainer WELD_CONTAINER;
    
    /**
     * Initialization of Weld's bean container.
     */
    static {
        WELD = new Weld();
        WELD_CONTAINER = WELD.initialize();
    }
    
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
