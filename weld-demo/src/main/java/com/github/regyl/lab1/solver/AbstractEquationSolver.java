package com.github.regyl.lab1.solver;

import com.github.regyl.lab1.dto.EquationParameters;
import com.github.regyl.lab1.model.MathMethod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractEquationSolver {
    
//    private static final String EQUATION_MASK = "^[xXyY\\d\\/\\-\\+\\*\\=]{2,}$";
//    private static final String EQUATION_MASK = "^\\d*[Xx]{1}[\\/\\-\\+\\*]{1}\\d*[Yy]\\=\\d{1,}$";
    private static final Pattern EQUATION_PATTERN = Pattern.compile("^(\\d*)([xX]*)([\\/\\-\\+\\*])(\\d*)([yY]*)\\=(\\d+)$");
    
    /**
     * Same as {@link AbstractEquationSolver#solve0(String, String)}, but with validation.
     * <p>
     * Allowed examples: {@code 10x+y=5}
     *
     * @return {@link Map} where first - x, second - y
     */
    public Pair<Double, Double> solve(String firstEquation, String secondEquation) {
        EquationParameters firstParams = extractParameters(firstEquation);
        EquationParameters secondParams = extractParameters(secondEquation);
        
        return solve0(firstParams, secondParams);
    }
    
    protected abstract Pair<Double, Double> solve0(EquationParameters firstParams, EquationParameters secondParams);
    
    private EquationParameters extractParameters(String equation) {
        Matcher matcher = EQUATION_PATTERN.matcher(equation);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Equation contains wrong symbols for input: " + equation);
        }
        
        MathMethod method = MathMethod.byValue(matcher.group(3))
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Math method from equations %s cannot be acquired", equation)));
        return EquationParameters.builder()
                .first(Double.parseDouble(matcher.group(1)))
                .second(Double.parseDouble(matcher.group(4)))
                .method(method)
                .result(Double.parseDouble(matcher.group(6)))
                .build();
    }
}
