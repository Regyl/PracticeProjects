package com.deepspace;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParamsCleaner {

    private static final String DELIMITER = ",";

    public static List<String> beautify(String params) {
        params = params.replaceAll("\\([A-Za-z]*\\)", "");
        String[] parameters = params.split(DELIMITER);
        return Arrays.stream(parameters)
                .map(String::trim)
                .map(item -> String.format("'%s'", item))
                .collect(Collectors.toList());
    }
}
