package com.deepspace;

import com.deepspace.formatter.AndFormatter;
import com.deepspace.formatter.FromFormatter;
import com.deepspace.formatter.InnerJoinFormatter;
import com.deepspace.formatter.LeftJoinFormatter;
import com.deepspace.formatter.LimitFormatter;
import com.deepspace.formatter.SelectFormatter;
import com.deepspace.formatter.UnionFormatter;
import com.deepspace.formatter.WhereFormatter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {

    private static final Set<SqlFormatter> SQL_FORMATTERS = new HashSet<>(Arrays.asList(
            new SelectFormatter(),
            new WhereFormatter(),
            new InnerJoinFormatter(),
            new LeftJoinFormatter(),
            new UnionFormatter(),
            new AndFormatter(),
            new FromFormatter(),
            new LimitFormatter()
    ));

    private static final String INPUT = """
            
            """;

    private static final String PARAMS = """
            
            """;

    public static void main( String[] args ) {
        String input = INPUT;
        String params = PARAMS;
        List<String> parameters = ParamsCleaner.beautify(params);
        for (String param : parameters) {
            input = input.replaceFirst("\\?", param);
        }

        for (SqlFormatter formatter : SQL_FORMATTERS) {
            input = formatter.format(input);
        }
        System.out.println(input);
    }
}
