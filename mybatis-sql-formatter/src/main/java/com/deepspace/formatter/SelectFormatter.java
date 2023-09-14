package com.deepspace.formatter;

import com.deepspace.SqlFormatter;

public class SelectFormatter implements SqlFormatter {

    @Override
    public String format(String input) {
        return input.replaceAll("(?i)select", "\n select");
    }
}
