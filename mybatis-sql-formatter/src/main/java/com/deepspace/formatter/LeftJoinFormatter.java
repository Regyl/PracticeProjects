package com.deepspace.formatter;

import com.deepspace.SqlFormatter;

public class LeftJoinFormatter implements SqlFormatter {

    @Override
    public String format(String input) {
            return input.replaceAll("(?i)left", "\n     left");
    }
}
