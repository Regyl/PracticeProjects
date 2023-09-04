package com.deepspace.formatter;

import com.deepspace.SqlFormatter;

public class WhereFormatter implements SqlFormatter {

    @Override
    public String format(String input) {
        return input.replaceAll("(?i)where", "\n where");
    }
}
