package com.deepspace.formatter;

import com.deepspace.SqlFormatter;

public class LimitFormatter implements SqlFormatter {

    @Override
    public String format(String input) {
            return input.replaceAll("(?i)limit", "\n limit");
    }
}
