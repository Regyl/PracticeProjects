package com.deepspace.formatter;

import com.deepspace.SqlFormatter;

public class UnionFormatter implements SqlFormatter {

    @Override
    public String format(String input) {
            return input.replaceAll("(?i)union", "\n\n union\n");
    }
}
